package com.mariannecunha.concretechallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mariannecunha.concretechallenge.R
import com.mariannecunha.concretechallenge.domain.model.Repository
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestListActivity : AppCompatActivity() {

    private val viewModel by viewModel<PullRequestListViewModel>()
    private val adapter by inject<PullRequestListAdapter>()
    private var repository: Repository? = null
    private lateinit var pullRequestRecyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var pullRequestProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request_list)

        setUpRecyclerView()
        setUpProgressBar()
        setUpObserver()
        repository = intent.getParcelableExtra(REPOSITORY_KEY)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPullRequests(repository)
    }

    private fun setUpRecyclerView() {
        pullRequestRecyclerView = findViewById(R.id.pull_request_recycler_view)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        pullRequestRecyclerView.setHasFixedSize(true)
        pullRequestRecyclerView.layoutManager = layoutManager
        pullRequestRecyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        viewModel.pullsLiveData.observe(this, Observer {
            pullRequestProgressBar.visibility = View.GONE
            adapter.updatePullRequest(it)
        }
        )
    }

    private fun setUpProgressBar() {
        pullRequestProgressBar = findViewById(R.id.pull_request_progress_bar)
    }

    companion object {
        const val REPOSITORY_KEY = "REPOSITORY_KEY"
    }
}