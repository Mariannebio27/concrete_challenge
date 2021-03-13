package com.mariannecunha.concretechallenge.presentation.repositorylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mariannecunha.concretechallenge.R
import com.mariannecunha.concretechallenge.domain.model.Repository
import de.hdodenhof.circleimageview.CircleImageView

class RepositoryListAdapter() : RecyclerView.Adapter<RepositoryListAdapter.RepositoryListViewHolder>() {

    private val repository = mutableListOf<Repository>()
    private val _repositoryLiveData = MutableLiveData<Repository>()
    val repositoryLiveData: LiveData<Repository> = _repositoryLiveData

    fun updateRepository(list: List<Repository>?) {

        this.repository.addAll(list ?: listOf())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryListViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.repository_list_item, parent, false)

        return RepositoryListViewHolder(
            _repositoryLiveData,
            itemView
        )
    }

    override fun getItemCount(): Int {
        return repository.count()
    }

    override fun onBindViewHolder(holder: RepositoryListViewHolder, position: Int) {
        holder.itemBind(repository[position])
    }

    class RepositoryListViewHolder(private val _repositoryLiveData: MutableLiveData<Repository>, itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val repoName = itemView.findViewById<TextView>(R.id.repository_name_text_view)
        private val ownerName = itemView.findViewById<TextView>(R.id.username_text_view)
        private val repoText = itemView.findViewById<TextView>(R.id.repository_description_text_view)
        private val stars = itemView.findViewById<TextView>(R.id.star_text_view)
        private val forks = itemView.findViewById<TextView>(R.id.fork_text_view)
        private val image = itemView.findViewById<CircleImageView>(R.id.owner_image_view)

        fun itemBind(repository: Repository) {

            repoName.text = repository.name
            repoText.text = repository.description
            stars.text = repository.stargazersCount.toString()
            forks.text = repository.forksCount.toString()
            ownerName.text = repository.owner.login.toString()

            Glide.with(image.context)
                .load(repository.owner.avatarUrl)
                .into(image)

            setUpGoToRepository(_repositoryLiveData, repository)
        }

        private fun setUpGoToRepository(_repositoryLiveData: MutableLiveData<Repository>, repository: Repository) {
            itemView.setOnClickListener {
                _repositoryLiveData.value = repository
            }
        }
    }
}
