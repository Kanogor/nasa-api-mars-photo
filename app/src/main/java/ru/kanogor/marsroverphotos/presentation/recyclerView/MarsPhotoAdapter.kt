package ru.kanogor.marsroverphotos.presentation.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.kanogor.marsroverphotos.databinding.MarsPhotoItemBinding
import ru.kanogor.marsroverphotos.entity.Photos

class MarsPhotoAdapter(private val onClick: (Photos) -> Unit) :
    PagingDataAdapter<Photos, MarsPhotosListHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotosListHolder {
        val binding =
            MarsPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarsPhotosListHolder(binding)
    }

    override fun onBindViewHolder(holder: MarsPhotosListHolder, position: Int) {
        val item = getItem(position)
        val roverText = item?.rover?.name
        val cameraText = item?.camera?.name
        val solText = item?.sol
        val dateText = item?.earth_date
        with(holder.binding) {
            rover.text = "Rover: $roverText"
            camera.text = "Camera: $cameraText"
            sol.text = "Sol: $solText"
            date.text = "Date: $dateText"
            item?.let {
                Glide
                    .with(photo.context)
                    .load(it.img_src)
                    .into(photo)
            }
            root.setOnClickListener {
                item?.let {
                    onClick(item)
                }
            }
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Photos>() {

    override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean =
        oldItem.img_src == newItem.img_src

}

class MarsPhotosListHolder(val binding: MarsPhotoItemBinding) :
    RecyclerView.ViewHolder(binding.root)
