package com.salma.app.myjepara

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salma.app.myjepara.databinding.ItemCardviewTourBinding

class ListTourAdapter (private val listTour: ArrayList<Tour>) : RecyclerView.Adapter<ListTourAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemCardviewTourBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, placeName,location, facilities,description, heighlight, photo) = listTour[position]
        holder.binding.tvItemName.text = name
        holder.binding.tvLocation.text = location
        holder.binding.tvSorotan.text = heighlight
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailTourActivity::class.java).apply {
                putExtra("tour", listTour[holder.adapterPosition])
            }
            context.startActivity(intent)
            onItemClickCallBack.onItemClicked(listTour[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listTour.size

    class ListViewHolder (var binding: ItemCardviewTourBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallBack {
        fun onItemClicked(data: Tour)
    }
}