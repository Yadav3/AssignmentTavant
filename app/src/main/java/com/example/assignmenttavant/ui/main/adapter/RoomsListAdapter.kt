package com.example.assignmenttavant.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmenttavant.R
import com.example.assignmenttavant.model.DiffUtilRoomModelItem
import com.example.assignmenttavant.model.RoomsModel
import com.example.assignmenttavant.utility.hideIfEmptyText

class RoomsListAdapter(private val listener: CharacterItemListener): ListAdapter<RoomsModel.RoomsModelItem, RoomsListViewHolder>(
    DiffUtilRoomModelItem()
) {
    interface CharacterItemListener {
        fun onClickedCharacter(peopleId: RoomsModel.RoomsModelItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user_repositories, parent, false)
        return RoomsListViewHolder(itemView,listener)
    }

    override fun onBindViewHolder(holder: RoomsListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RoomsListViewHolder(itemView: View,
                       private val listener: RoomsListAdapter.CharacterItemListener
) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    private lateinit var obj: RoomsModel.RoomsModelItem
    init {
        itemView.setOnClickListener(this)
    }
    fun bind(item: RoomsModel.RoomsModelItem?) {
        this.obj = item!!
       // itemView.image_view_owner?.loadImageFromUrl(item?.avatar)
        itemView.text_view_owner_name.text = item?.maxOccupancy
        itemView.text_view_repository_name.hideIfEmptyText(item?.createdAt)
        itemView.text_view_repository_description.hideIfEmptyText(item?.isOccupied)
    }
    override fun onClick(v: View?) {

        obj?.let { listener.onClickedCharacter(obj) }
    }
}