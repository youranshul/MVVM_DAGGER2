package com.test.nymovie.moviereviewlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.nymovie.R
import com.test.nymovie.core.BaseDaggerFragment
import javax.inject.Inject

class MovieReviewFragment : BaseDaggerFragment(), SearchView.OnQueryTextListener,
    OnMovieItemClickListener {

    override fun getLayout(): Int {
        return R.layout.moview_reviews
    }

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


    private var adapter: MovieReviewsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreationCompleted(view: View) {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MovieReviewsListViewModel::class.java)
        val viewManager = LinearLayoutManager(requireContext())
        recycleView = view.findViewById<RecyclerView>(R.id.recycle_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager

        }
    }

    override fun getUiSignalLiveData(): MutableLiveData<UiSignal> {
        return viewModel.provideUiSignalLiveData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadMovieReviews().observe(this, Observer {
            adapter = MovieReviewsAdapter(it, this)
            recycleView.adapter = adapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val menuItem = menu.findItem(R.id.app_bar_search)
        val searchView = menuItem.actionView as SearchView
        searchView.apply {
            queryHint = resources.getString(R.string.search_hint)
        }
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }
}