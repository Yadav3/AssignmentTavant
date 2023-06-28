package com.example.assignmenttavant.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmenttavant.R
import com.example.assignmenttavant.databinding.ListItemUserRepositoriesBinding
import com.example.assignmenttavant.model.DiffUtilProductModelItem
import com.example.assignmenttavant.model.ProductModel
import com.example.assignmenttavant.utility.hideIfEmptyText
import com.example.assignmenttavant.utility.loadImageFromUrl


class ProductAdapter: ListAdapter<ProductModel.ProductModelItem, ProductViewHolder>(
    DiffUtilProductModelItem()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user_repositories, parent, false)
//        return ProductViewHolder(itemView = itemView)
        val itemBinding = ListItemUserRepositoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ProductViewHolder(private val itemBinding: ListItemUserRepositoriesBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(item: ProductModel.ProductModelItem?) {
     //   val owner = item?.owner
        itemBinding.image_view_owner?.loadImageFromUrl(item?.image)
        itemBinding.text_view_owner_name.text = item?.title//?.substringBefore(delimiter = "/")
        itemBinding.text_view_repository_name.hideIfEmptyText(item?.description)
        itemBinding.text_view_repository_description.hideIfEmptyText(item?.title)
    }
}