package com.test.nymovie.moviereviewlist

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.test.nymovie.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieReviewFragment : DaggerFragment(), SearchView.OnQueryTextListener,
    OnMovieItemClickListener {

    override fun onItemClicked(title: String, reviewer: String) {
        viewModel.onItemClick(title, reviewer)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter?.filter?.filter(newText)
        return true
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieReviewsListViewModel

    private lateinit var recycleView: RecyclerView

    private lateinit var progressBar: ProgressBar

    private lateinit var errorView: TextView

    private var adapter: MovieReviewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MovieReviewsListViewModel::class.java)
        return inflater.inflate(R.layout.moview_reviews, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewManager = LinearLayoutManager(requireContext())
        recycleView = view.findViewById<RecyclerView>(R.id.recycle_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager

        }
        progressBar = view.findViewById(R.id.progressBar)
        errorView = view.findViewById(R.id.error_text)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loadingBarLiveData.observe(this, Observer {
            when (it) {
                UiSignal.LOADING_VISIBLE -> progressBar.visibility = View.VISIBLE
                UiSignal.LOADING_GONE -> progressBar.visibility = View.GONE
                UiSignal.ERROR_MSG -> errorView.visibility = View.VISIBLE
                UiSignal.NO_INTERNET -> networkGone()
                UiSignal.INTERNET_AVAILABLE -> networkAvailable()
                else -> progressBar.visibility = View.GONE
            }
        })

        viewModel.loadMovieReviews().observe(this, Observer {
            adapter = MovieReviewsAdapter(it, this)
            recycleView.adapter = adapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val menuItem = menu.findItem(R.id.app_bar_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = resources.getString(R.string.search_hint)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun networkAvailable() {
        view?.let {
            Snackbar.make(
                it,
                resources.getString(R.string.network_available),
                Snackbar.LENGTH_SHORT
            ).show()
        }

    }

    private fun networkGone() {
        view?.let {
            Snackbar.make(it, resources.getString(R.string.no_network), Snackbar.LENGTH_INDEFINITE)
                .show()
        }
    }

}