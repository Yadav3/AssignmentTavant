package com.example.assignmenttavant.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

class RoomsModel : ArrayList<RoomsModel.RoomsModelItem>(){

    @Parcelize
    data class RoomsModelItem(
        val createdAt: String?,
        val isOccupied: String?,
        val maxOccupancy: String?,
        val id: String?

    ): Parcelable

}

class DiffUtilRoomModelItem: DiffUtil.ItemCallback<RoomsModel.RoomsModelItem>() {
    override fun areItemsTheSame(
        oldItem: RoomsModel.RoomsModelItem,
        newItem: RoomsModel.RoomsModelItem
    ): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(
        oldItem: RoomsModel.RoomsModelItem,
        newItem: RoomsModel.RoomsModelItem
    ): Boolean {
        return newItem == oldItem
    }
}