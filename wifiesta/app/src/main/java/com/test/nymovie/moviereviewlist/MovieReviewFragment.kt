package com.test.nymovie.moviereviewlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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

class MovieReviewFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieReviewsListViewModel

    private lateinit var recycleView: RecyclerView

    private lateinit var progressBar: ProgressBar

    private lateinit var errorView: TextView

    private var adapter: MovieReviewsAdapter? = null

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
            adapter = MovieReviewsAdapter(it)
            recycleView.adapter = adapter
        })
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