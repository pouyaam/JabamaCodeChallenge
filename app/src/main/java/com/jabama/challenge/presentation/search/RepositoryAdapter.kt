package com.jabama.challenge.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jabama.challenge.data.search.model.GitHubResponse
import com.jabama.challenge.github.databinding.ItemRepositoryBinding

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    private val repositories = mutableListOf<GitHubResponse.Repository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    override fun getItemCount(): Int = repositories.size

    fun submitList(repoList: List<GitHubResponse.Repository>) {
        repositories.clear()
        repositories.addAll(repoList)
        notifyDataSetChanged()
    }

    class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: GitHubResponse.Repository) {
            binding.repoName.text = repository.name
            binding.repoFullName.text = repository.fullName
            binding.repoDescription.text = repository.description ?: "No description available"
            binding.ownerUsername.text = repository.owner.login
            binding.stargazersCount.text = repository.stargazersCount.toString()

            // Load avatar image (using a library like Glide or Coil)
            Glide.with(itemView.context)
                .load(repository.owner.avatarUrl)
                .circleCrop()  // To make the image circular
                .into(binding.ownerAvatar)
        }
    }

}