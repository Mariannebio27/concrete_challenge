package com.mariannecunha.concretechallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
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
    private lateinit var repositoryRecyclerView: RecyclerView
    private lateinit var layoutManager : LinearLayoutManager
    private lateinit var repositoryProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_list)

        setUpRecyclerView()
        setUpProgressBar()
        setUpObserver()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getRepositories()
    }

    private fun setUpRecyclerView() {
        repositoryRecyclerView = findViewById(R.id.repository_recycler_view)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
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

    private fun setUpProgressBar() {
        repositoryProgressBar = findViewById(R.id.repository_progress_bar)
    }

    private fun setUpObserver() {
        viewModel.productsLiveData.observe(this, Observer {
            repositoryProgressBar.visibility = View.GONE
            adapter.updateRepository(it)
        })
    }
}