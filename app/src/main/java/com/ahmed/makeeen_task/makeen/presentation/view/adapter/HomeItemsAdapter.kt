package com.ahmed.makeeen_task.makeen.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.makeeen_task.R
import com.ahmed.makeeen_task.base.MEDIA_BASE_URL
import com.ahmed.makeeen_task.makeen.data.model.response.ProductItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recycler_item.view.*

class HomeItemsAdapter(
    val data: ArrayList<ProductItem>,
    val function: (size: Int) -> Unit = {}
) : RecyclerView.Adapter<HomeItemsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])

        if (position > data.size - 4) {
            function(data.size)
        }
    }

    fun setList(list: List<ProductItem>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    class ViewHolder constructor(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: ProductItem) {
            bindThmbnail(model.img)
            view.tvItemTitle.text = model.title_name
            view.tvItemDescription.text = model.bio
        }

        private fun bindThmbnail(thumbnail: String?) {
            thumbnail ?: return
            Glide.with(view)
                .load(MEDIA_BASE_URL + thumbnail)
                .placeholder(R.drawable.bg_radius_dark)
                .into(view.ivPoster)
        }

    }
}