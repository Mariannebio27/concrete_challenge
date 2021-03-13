package com.mariannecunha.concretechallenge.presentation.repositorylist

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
import com.mariannecunha.concretechallenge.extension.setupScroll
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryListFragment : Fragment() {

    private val viewModel by viewModel<RepositoryListViewModel>()
    private val adapter by inject<RepositoryListAdapter>()
    private val repositoryRecyclerView by lazy { view?.findViewById<RecyclerView>(R.id.repository_recycler_view) }
    private val layoutManager: LinearLayoutManager by lazy { LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) }
    private val repositoryProgressBar by lazy { view?.findViewById<ProgressBar>(R.id.repository_progress_bar) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpObserver()
        viewModel.getRepositories()
    }

    fun getRepositoryClickLiveData() = adapter.repositoryLiveData

    private fun setUpRecyclerView() {
        repositoryRecyclerView?.setHasFixedSize(true)
        repositoryRecyclerView?.layoutManager = layoutManager
        repositoryRecyclerView?.adapter = adapter
        repositoryRecyclerView?.setupScroll(
            layoutManager = layoutManager,
            methodToInvokeAtEnd = {
                repositoryProgressBar?.visibility = View.VISIBLE
                viewModel.getRepositories()
            }
        )
    }

    private fun setUpObserver() {
        viewModel.productsLiveData.observe(
            viewLifecycleOwner,
            Observer {
                repositoryProgressBar?.visibility = View.GONE
                adapter.updateRepository(it)
            }
        )
    }

    companion object {
        fun newInstance() =
            RepositoryListFragment()
    }
}
