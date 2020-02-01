package com.test.nymovie.moviereviewlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.test.nymovie.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieFragment : DaggerFragment() {
    private var testbtn: Button? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieReviewsListViewModel


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
        testbtn = view.findViewById<Button>(R.id.test)
        testbtn?.setOnClickListener {
            val result = viewModel.isAllOk()
            Log.d(MovieFragment::class.java.name, "yes: $result")
        }
    }
}