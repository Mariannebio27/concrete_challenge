package com.mariannecunha.concretechallenge.presentation

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mariannecunha.concretechallenge.R
import com.mariannecunha.concretechallenge.model.PullRequest
import com.mariannecunha.concretechallenge.model.Repository

class PullRequestListAdapter() : RecyclerView.Adapter<PullRequestListAdapter.PullRequestListViewHolder>() {

    private val pullRequests = mutableListOf<PullRequest>()

    fun updatePullRequest(pullRequests: List<PullRequest>?) {
        if (this.pullRequests.isNotEmpty()) {
            this.pullRequests.clear()
        }

        this.pullRequests.addAll(pullRequests ?: listOf())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestListViewHolder {
        val itemView : View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pull_request_list_item, parent, false)

        return PullRequestListAdapter.PullRequestListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pullRequests.count()

    }

    override fun onBindViewHolder(holder: PullRequestListViewHolder, position: Int) {
        holder.itemBind(pullRequests[position])
    }


    class PullRequestListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        private val name = itemView.findViewById<TextView>(R.id.pull_request_name_text_view)
        private val body = itemView.findViewById<TextView>(R.id.pull_request_description_text_view)
        private val image = itemView.findViewById<ImageView>(R.id.pull_request_image_view)
        private val date = itemView.findViewById<TextView>(R.id.pull_request_date_text_view)
        private val username = itemView.findViewById<TextView>(R.id.pull_request_username_text_view)


        fun itemBind(pullRequest: PullRequest) {

            name.text = pullRequest.title
            body.text = pullRequest.body
            date.text = pullRequest.date.toString()
            username.text = pullRequest.user.login


            Glide.with(image.context)
                .load(pullRequest.user.imageUrl)
                .into(image)

            setUpGoToRepository(pullRequest)

        }

        private fun setUpGoToRepository(pullRequest: PullRequest) {
            itemView.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pullRequest.url));
                itemView.context.startActivity(browserIntent);
            }

        }
    }

}