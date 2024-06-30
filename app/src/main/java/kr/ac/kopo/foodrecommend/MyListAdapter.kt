package kr.ac.kopo.foodrecommend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyListAdapter(private val itemList: List<String>) :
    RecyclerView.Adapter<MyListAdapter.MyListViewHolder>() {

    class MyListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTextView: TextView = itemView.findViewById(R.id.itemTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_my_list, parent, false)
        return MyListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemTextView.text = currentItem
    }

    override fun getItemCount() = itemList.size
}
