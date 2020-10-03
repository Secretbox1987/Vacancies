package com.example.mobile_ui.extentions

import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.show(v: Boolean) {
    visibility = if(v){
        View.VISIBLE
    }else{
        View.GONE
    }
}

fun View.showOrInvisible(v: Boolean) {
    visibility = if(v){
        View.VISIBLE
    }else{
        View.INVISIBLE
    }
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}
