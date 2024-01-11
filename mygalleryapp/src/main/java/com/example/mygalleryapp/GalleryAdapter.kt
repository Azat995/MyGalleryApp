package com.example.mygalleryapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mygalleryapp.databinding.ItemGaleryBinding


class GalleryAdapter(private val listener: Listener) : ListAdapter<ImageModel, GalleryAdapter.ImageVH>(ImageDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        return ImageVH(ItemGaleryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ImageVH, position: Int) {
        holder.onBind(position)
    }

    inner class ImageVH(private val binding: ItemGaleryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int): Unit = with(binding) {
            val newImageModel: ImageModel = getItem(position)
            galleryImageView.load(newImageModel.url)

            root.setOnClickListener {
                listener.onImageClick(position, newImageModel)
            }
        }
    }

    class ImageDiffUtil : DiffUtil.ItemCallback<ImageModel>() {
        override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem.url == newItem.url
        }
    }

    interface Listener {
        fun onImageClick(position: Int, model: ImageModel)
    }
}

