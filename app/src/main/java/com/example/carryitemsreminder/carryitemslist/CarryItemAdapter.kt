package com.example.carryitemsreminder.carryitemslist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.carryitemsreminder.R
import com.example.carryitemsreminder.database.CarryItemEntity
import kotlinx.android.synthetic.main.item.view.*

class CarryItemAdapter (private val items:MutableList<CarryItemEntity>,
                        private val listener: EventListener) : RecyclerView.Adapter<CarryItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder
    {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item, parent, false)
        )
    }

    fun addItem(item:CarryItemEntity){
        items.add(item)
        notifyItemInserted(items.size-1)
    }

    fun deleteItem(){
        items.removeAll { item ->
            item.carryItemStatus
        }
        notifyDataSetChanged()
    }

    fun setItems(newItems: List<CarryItemEntity>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun getAllCheckedItems(): List<CarryItemEntity> {
        return items.filter {
            it.carryItemStatus
        }
    }

    private fun toggleStrikeThrough(tvItemTitle:TextView, isChecked:Boolean)
    {
        if(isChecked) {
            tvItemTitle.paintFlags = tvItemTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else {
            tvItemTitle.paintFlags = tvItemTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int)
    {
        val curItem = items[position]
        holder.itemView.apply {
            tvItemTitle.text = curItem.carryItem
            cbDone.isChecked = curItem.carryItemStatus
            toggleStrikeThrough(tvItemTitle, curItem.carryItemStatus)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvItemTitle, isChecked)
                curItem.carryItemStatus = !curItem.carryItemStatus
                listener.onCheckedStateChanged(curItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

interface EventListener {
    fun onCheckedStateChanged(item: CarryItemEntity)
}