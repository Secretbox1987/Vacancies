package com.example.mobile_ui.vacancy_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.popov.mobile_ui.R
import com.example.mobile_ui.Navigation
import com.example.mobile_ui.extentions.gone
import com.example.mobile_ui.extentions.visible
import com.example.presentation.VacancyListViewModel
import com.example.presentation.model.VacancyView
import com.example.presentation.state.ResourceState
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class VacancyListFragment : DaggerFragment() {

    val progress: ProgressBar? by lazy {
        view?.findViewById(R.id.progress)
    }
    val rv: RecyclerView? by lazy {
        view?.findViewById(R.id.rv)
    }

    var rootView: View? = null

    var adapter: VacancyAdapter? = null

    var navigation: Navigation? = null

    var mIsLoading = false

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    var viewModel: VacancyListViewModel? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, viewModelFactory).get(VacancyListViewModel::class.java)
        navigation = Navigation(activity)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, avedInstanceState: Bundle?
    ): View? {
        if(rootView == null){
            rootView = inflater.inflate(R.layout.fragment_vacancy_list, container, false)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        observe()
    }

    private fun initRV() = rv?.run {
        val lM = LinearLayoutManager(context)
        layoutManager = lM
        this@VacancyListFragment.adapter = VacancyAdapter(
            itemClick = { vacancy ->
                navigation?.goToVacancyDetail(vacancy as VacancyView)
            },
            refresh = {
                this@VacancyListFragment.adapter?.hideError()
                viewModel?.fetchVacancyList()
            }
        )
        adapter = this@VacancyListFragment.adapter
        addItemDecoration(DividerItemDecorator())
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (mIsLoading) return
                val visibleItemCount: Int = lM.childCount
                val totalItemCount: Int = lM.itemCount
                val pastVisibleItems: Int = lM.findFirstVisibleItemPosition()
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    mIsLoading = true
                    this@VacancyListFragment.adapter?.showPageLoader()
                    viewModel?.fetchVacancyList()
                }
            }
        })
    }



    private fun observe(){
        viewModel?.getVacancyList()?.observe(viewLifecycleOwner, Observer {
            val resource = it ?: return@Observer
            val status = resource.status
            when (status) {
                ResourceState.LOADING -> {
                    val vacancyList = adapter?.vacancyList ?: mutableListOf()
                    if(vacancyList.isEmpty()){
                        progress?.visible()
                    }
                }
                ResourceState.SUCCESS -> {
                    adapter?.hidePageLoader()
                    progress?.gone()
                    val data = resource.data ?: mutableListOf()
                    adapter?.insertData(data)
                    if(data.isNotEmpty()){
                        mIsLoading = false
                    }
                }
                ResourceState.ERROR -> {
                    adapter?.hidePageLoader()
                    progress?.gone()
                    adapter?.showError(resource.message ?: "")
                }
            }
        })
    }
}