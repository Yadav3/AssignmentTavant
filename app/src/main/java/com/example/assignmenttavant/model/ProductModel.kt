package com.example.assignmenttavant.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

class ProductModel : ArrayList<ProductModel.ProductModelItem>(){

    @Parcelize
    data class ProductModelItem(
        val image: String?,
        val id: String?,
        val title: String?,
        val description: String?,

    ):Parcelable

}

class DiffUtilProductModelItem: DiffUtil.ItemCallback<ProductModel.ProductModelItem>() {
    override fun areItemsTheSame(
        oldItem: ProductModel.ProductModelItem,
        newItem: ProductModel.ProductModelItem
    ): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(
        oldItem: ProductModel.ProductModelItem,
        newItem: ProductModel.ProductModelItem
    ): Boolean {
        return newItem == oldItem
    }
}