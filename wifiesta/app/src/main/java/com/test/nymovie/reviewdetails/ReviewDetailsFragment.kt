package com.test.nymovie.reviewdetails

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.test.nymovie.R
import com.test.nymovie.core.BaseDaggerFragment
import com.test.nymovie.core.MainActivity
import com.test.nymovie.moviereviewlist.ReviewQueryData
import com.test.nymovie.moviereviewlist.UiSignal
import javax.inject.Inject

class ReviewDetailsFragment : BaseDaggerFragment() {
    override fun getUiSignalLiveData(): MutableLiveData<UiSignal> {
        return viewModel.provideUiSignalLiveData()
    }

    override fun getLayout(): Int {
        return R.layout.review_details_container
    }

    companion object {
         const val TITLE = "movieTitle"
         const val REVIEWER = "reviewer"

        fun newInstance(data: ReviewQueryData): Fragment {
            val fragment = ReviewDetailsFragment()
            val bundle = Bundle()
            bundle.putString(TITLE, data.title)
            bundle.putString(REVIEWER, data.reviewer)
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ReviewDetailsViewModel

    private lateinit var image: ImageView
    private lateinit var headlineTV: TextView
    private lateinit var criticsPickTv: TextView
    private lateinit var ratingTv: TextView
    private lateinit var summaryTv: TextView
    private lateinit var readMoreTv: TextView

    private lateinit var mainView: ConstraintLayout

    override fun onViewCreationCompleted(view: View) {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ReviewDetailsViewModel::class.java)

        image = view.findViewById(R.id.reviewBigImage)
        headlineTV = view.findViewById(R.id.headline)
        criticsPickTv = view.findViewById(R.id.criticsPick)
        ratingTv = view.findViewById(R.id.rating)
        summaryTv = view.findViewById(R.id.shortDesc)
        readMoreTv = view.findViewById(R.id.readMore)
        mainView = view.findViewById(R.id.constraintLayout)

        readMoreTv.setOnClickListener {
            viewModel.openWeb()
        }
        fetchReview()
    }

    private fun fetchReview() {
        val bundle = arguments
        bundle?.let {
            val title = it[TITLE] as String
            (requireActivity() as MainActivity).title = title
            val reviewer = it[REVIEWER] as String
            viewModel.loadReviewDetails(title, reviewer).observe(this, Observer { data ->
                mainView.visibility = View.VISIBLE
                headlineTV.text = data.headline
                summaryTv.text = data.summary
                handleRatingView(data)
                loadImage(data.imageUrl)
                handleCriticsView(data.pick)
            })
        }
    }


    private fun handleRatingView(data: ReviewDetails) {
        ratingTv.text = when (data.rating.isBlank()) {
            true -> "NA"
            false -> data.rating
        }
    }

    private fun handleCriticsView(isPicked: Boolean) {
        criticsPickTv.visibility = when (isPicked) {
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }
    }

    private fun loadImage(imageUrl: String) {
        Glide.with(requireActivity()).load(imageUrl).centerCrop().placeholder(
            ColorDrawable(Color.GRAY)
        ).into(image)
    }
}
