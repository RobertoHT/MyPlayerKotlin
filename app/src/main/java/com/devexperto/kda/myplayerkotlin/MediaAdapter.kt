package com.devexperto.kda.myplayerkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devexperto.kda.myplayerkotlin.databinding.ViewMediaItemBinding

class MediaAdapter(private val items: List<MediaItem>, private val listener: (String) -> Unit) : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewMediaItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener(item.title) }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: ViewMediaItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(mediaItem: MediaItem) {
            with(binding) {
                mediaTitle.text = mediaItem.title
                mediaThumb.loadUrl(mediaItem.url)
                mediaVideoIndicator.visibility = when (mediaItem.type) {
                    Type.VIDEO -> View.VISIBLE
                    Type.PHOTO -> View.GONE
                }
            }
        }
    }
}