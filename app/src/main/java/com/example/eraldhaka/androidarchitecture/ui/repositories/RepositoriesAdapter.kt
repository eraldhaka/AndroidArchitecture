package com.example.eraldhaka.androidarchitecture.ui.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eraldhaka.androidarchitecture.databinding.RepositoryItemBinding
import com.example.eraldhaka.androidarchitecture.domain.RepositoryModel

class RepositoriesAdapter(private val clickListener: RepositoryClickListener) : ListAdapter<RepositoryModel, RepositoriesAdapter.ViewHolder>(
    RepositoryDiffCallback()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(clickListener,item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: RepositoryItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: RepositoryClickListener, item: RepositoryModel) {
            binding.repository = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RepositoryItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

// checks to update adapter only if there are changes
class RepositoryDiffCallback : DiffUtil.ItemCallback<RepositoryModel>() {
    override fun areItemsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean {
        return oldItem == newItem
    }
}

class RepositoryClickListener(val clickListener: (RepositoryModel) -> Unit) {
    fun onClick(repos: RepositoryModel) = clickListener(repos)
}