package com.mariannecunha.concretechallenge.presentation.pullrequest

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
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
    private val pullRequestRecyclerView: RecyclerView by lazy { findViewById(R.id.pull_request_recycler_view) }
    private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) }
    private val pullRequestProgressBar: ProgressBar by lazy { findViewById(R.id.pull_request_progress_bar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request_list)

        setUpRecyclerView()
        setUpObserver()
        repository = intent.getParcelableExtra(REPOSITORY_KEY)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPullRequests(repository)
    }

    private fun setUpRecyclerView() {
        pullRequestRecyclerView.setHasFixedSize(true)
        pullRequestRecyclerView.layoutManager = layoutManager
        pullRequestRecyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        viewModel.pullsLiveData.observe(
            this,
            Observer {
                pullRequestProgressBar.visibility = View.GONE
                adapter.updatePullRequest(it)
            }
        )
    }

    companion object {
        const val REPOSITORY_KEY = "REPOSITORY_KEY"
    }
}
