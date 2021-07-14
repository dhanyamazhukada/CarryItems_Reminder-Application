package com.example.carryitemsreminder

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.item.view.*
import java.io.File


class ItemAdapter (private val items:MutableList<Item>)
: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()
{
        class ItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder // will create viewholder
    {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.item, parent, false)
        )
    }

    fun addItem(item:Item){
        items.add(item)
        notifyItemInserted(items.size-1)
/*        try {

            val itemsList: List<ItemBluePrint> = listOf(
                ItemBluePrint(listOf(item.title)))


            val gson = Gson()
            val jsonItemsList: String = gson.toJson(itemsList)
            File("itemsList.json").writeText(", ")
            File("itemsList.json").writeText(jsonItemsList)
        }
        catch(e:Exception)
        {
            Log.i("dhanya", "Write exception")
        }*/

    }

    fun deleteItem(){
        items.removeAll { item ->
            item.isChecked
        }
        notifyDataSetChanged()
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
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) // bind data from our itemslist to the views of our list
    {
        val curItem = items[position]
        holder.itemView.apply {
            tvItemTitle.text = curItem.title
            cbDone.isChecked = curItem.isChecked
            toggleStrikeThrough(tvItemTitle, curItem.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvItemTitle, isChecked)
                curItem.isChecked = !curItem.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}