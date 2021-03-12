package com.mariannecunha.concretechallenge.presentation.repository

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mariannecunha.concretechallenge.R
import com.mariannecunha.concretechallenge.extension.setupScroll
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryListActivity : AppCompatActivity() {

    private val viewModel by viewModel<RepositoryListViewModel>()
    private val adapter by inject<RepositoryListAdapter>()
    private val repositoryRecyclerView: RecyclerView by lazy { findViewById(R.id.repository_recycler_view) }
    private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) }
    private val repositoryProgressBar: ProgressBar by lazy { findViewById(R.id.repository_progress_bar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_list)

        setUpRecyclerView()
        setUpObserver()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getRepositories()
    }

    private fun setUpRecyclerView() {
        repositoryRecyclerView.setHasFixedSize(true)
        repositoryRecyclerView.layoutManager = layoutManager
        repositoryRecyclerView.adapter = adapter
        repositoryRecyclerView.setupScroll(
            layoutManager = layoutManager,
            methodToInvokeAtEnd = {
                repositoryProgressBar.visibility = View.VISIBLE
                viewModel.getRepositories()
            }
        )
    }

    private fun setUpObserver() {
        viewModel.productsLiveData.observe(
            this,
            Observer {
                repositoryProgressBar.visibility = View.GONE
                adapter.updateRepository(it)
            }
        )
    }
}
