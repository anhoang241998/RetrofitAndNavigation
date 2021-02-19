package com.annguyenhoang.testing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.annguyenhoang.testing.databinding.ItemRecyclerBinding
import com.annguyenhoang.testing.models.Comments

class RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Comments>() {

        override fun areItemsTheSame(oldItem: Comments, newItem: Comments): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comments, newItem: Comments): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var comments: List<Comments>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    private lateinit var binding: ItemRecyclerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        binding = ItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = comments[position]
        binding.apply {
            tvName.text = currentItem.name
            tvEmail.text = currentItem.email
        }
    }

    override fun getItemCount() = comments.size

    class RecyclerViewHolder(itemView: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(itemView.root) {
    }


}