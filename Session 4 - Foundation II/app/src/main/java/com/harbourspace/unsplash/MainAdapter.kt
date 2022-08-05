package com.harbourspace.unsplash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_main.view.iv_preview

class MainAdapter(private val images: List<Int>, val onClick: (Int) -> Unit):
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image = itemView.iv_preview as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainViewHolder(inflater.inflate(R.layout.item_main, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val image = images[position]
        holder.image.setImageResource(image)
        holder.image.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}