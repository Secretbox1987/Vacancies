package com.example.presentation.model

import android.os.Parcel
import android.os.Parcelable

data class VacancyView(
    val id: String?,
    val created_at: String?,
    val company: String?,
    val title: String?,
    val description: String?,
    var how_to_apply: String?,
    override var type: Int = ItemType.VACANCY.type
): Type(), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(created_at)
        parcel.writeString(company)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(how_to_apply)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VacancyView> {
        override fun createFromParcel(parcel: Parcel): VacancyView {
            return VacancyView(parcel)
        }

        override fun newArray(size: Int): Array<VacancyView?> {
            return arrayOfNulls(size)
        }
    }
}