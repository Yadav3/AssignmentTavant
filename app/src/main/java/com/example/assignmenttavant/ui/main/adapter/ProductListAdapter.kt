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

class ProductListAdapter(private val listener: CharacterItemListener): ListAdapter<ProductModel.ProductModelItem, PeopleListViewHolder>(
    DiffUtilProductModelItem()
) {
    interface CharacterItemListener {
        fun onClickedCharacter(peopleId: ProductModel.ProductModelItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleListViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user_repositories, parent, false)
//        return PeopleListViewHolder(itemView,listener)
        val itemBinding = ListItemUserRepositoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleListViewHolder(itemBinding,listener)
    }

    override fun onBindViewHolder(holder: PeopleListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PeopleListViewHolder(private val itemBinding: ListItemUserRepositoriesBinding,
                       private val listener: ProductListAdapter.CharacterItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {
    private lateinit var obj: ProductModel.ProductModelItem
    init {
        itemBinding.text_view_owner_name.setOnClickListener(this)
    }
    fun bind(item: ProductModel.ProductModelItem?) {
        this.obj = item!!
        itemBinding.image_view_owner?.loadImageFromUrl(item?.image)
        itemBinding.text_view_owner_name.text = item?.title
        itemBinding.text_view_repository_name.hideIfEmptyText(item?.description)
        itemBinding.text_view_repository_description.hideIfEmptyText(item?.title)
    }
    override fun onClick(v: View?) {

        obj?.let { listener.onClickedCharacter(obj) }
    }
}