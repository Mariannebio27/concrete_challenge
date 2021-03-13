package com.mariannecunha.concretechallenge.presentation.pullrequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mariannecunha.concretechallenge.R
import com.mariannecunha.concretechallenge.domain.model.Repository
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PullRequestFragment : Fragment() {

    private val viewModel by viewModel<PullRequestListViewModel>()
    private val adapter by inject<PullRequestListAdapter>()
    private var repository: Repository? = null
    private val pullRequestRecyclerView by lazy { view?.findViewById<RecyclerView>(R.id.pull_request_recycler_view) }
    private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) }
    private val pullRequestProgressBar by lazy { view?.findViewById<ProgressBar>(R.id.pull_request_progress_bar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            repository = it.getParcelable(REPOSITORY_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pull_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpObserver()
        viewModel.getPullRequests(repository)
    }

    private fun setUpRecyclerView() {
        pullRequestRecyclerView?.setHasFixedSize(true)
        pullRequestRecyclerView?.layoutManager = layoutManager
        pullRequestRecyclerView?.adapter = adapter
    }

    private fun setUpObserver() {
        viewModel.pullsLiveData.observe(
            viewLifecycleOwner,
            Observer {
                pullRequestProgressBar?.visibility = View.GONE
                adapter.updatePullRequest(it)
            }
        )
    }

    companion object {
        private const val REPOSITORY_KEY = "REPOSITORY_KEY"

        fun newInstance(repository: Repository) =
            PullRequestFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(REPOSITORY_KEY, repository)
                }
            }
    }
}
