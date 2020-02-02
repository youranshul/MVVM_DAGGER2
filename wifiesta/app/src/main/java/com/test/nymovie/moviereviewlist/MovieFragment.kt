package com.test.nymovie.moviereviewlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.nymovie.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieReviewsListViewModel

    private lateinit var recycleView: RecyclerView

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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadMovieReviews().observe(this, Observer {
            adapter = MovieReviewsAdapter(it)
            recycleView.adapter = adapter
        })
    }
}