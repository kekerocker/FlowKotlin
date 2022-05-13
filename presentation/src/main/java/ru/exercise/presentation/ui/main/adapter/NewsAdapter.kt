package ru.exercise.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.exercise.presentation.R
import ru.exercise.presentation.databinding.RecycleritemNewsBinding
import ru.exercise.presentation.model.NewsRecyclerModel

internal class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var model: List<NewsRecyclerModel> = emptyList()

    fun setData(model: List<NewsRecyclerModel>) {
        this.model = model
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(binding: RecycleritemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvTitle: TextView = binding.tvTitle
        val tvDescription: TextView = binding.tvDescription
        val ivImage: ImageView = binding.ivImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            RecycleritemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = model[position]
        with(holder) {
            tvTitle.text = currentItem.text
            tvDescription.text = currentItem.description
            ivImage.load(currentItem.imageUrl) {
                placeholder(R.drawable.placeholder)
            }
        }
    }

    override fun getItemCount(): Int = model.size
}