package com.mariannecunha.concretechallenge.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mariannecunha.concretechallenge.R
import com.mariannecunha.concretechallenge.presentation.pullrequest.PullRequestFragment
import com.mariannecunha.concretechallenge.presentation.repositorylist.RepositoryListFragment
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : AppCompatActivity() {

    private val repositoryListFragment by lazy { RepositoryListFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        setUpObserver()
        changeFragment(repositoryListFragment)
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun setUpObserver() {
        repositoryListFragment.getRepositoryClickLiveData().observe(
            this,
            Observer {
                changeFragment(PullRequestFragment.newInstance(it))
            }
        )
    }
}
