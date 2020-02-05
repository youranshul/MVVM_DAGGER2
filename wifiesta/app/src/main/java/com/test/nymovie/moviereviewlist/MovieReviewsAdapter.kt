package com.test.nymovie.moviereviewlist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.nymovie.R
import com.test.nymovie.moviereviewlist.MovieReviewsAdapter.MovieReviewViewHolder

class MovieReviewsAdapter(private val movieReviews: List<MovieReview>) :
    RecyclerView.Adapter<MovieReviewViewHolder>(), Filterable {

    private val localMovieReviewsList = ArrayList(movieReviews)

    override fun getFilter(): Filter {
        return reviewsFilter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return MovieReviewViewHolder(view)
    }

    override fun getItemCount(): Int = movieReviews.size

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        holder.bind(movieReviews[position])
    }

    inner class MovieReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imageView = itemView.findViewById<ImageView>(R.id.avtar)
        private val titleView = itemView.findViewById<TextView>(R.id.title)
        private val reviewerView = itemView.findViewById<TextView>(R.id.byline)
        private val pickView = itemView.findViewById<ImageView>(R.id.pick)
        private val headlineView = itemView.findViewById<TextView>(R.id.headline)

        fun bind(review: MovieReview) {
            titleView.text = review.title
            reviewerView.text = review.byline
            headlineView.text = review.headline
            pickView.visibility = when (review.isPicked) {
                true -> View.VISIBLE
                false -> View.INVISIBLE
            }

            Glide.with(itemView.context).load(review.imageUrl).centerCrop().placeholder(
                ColorDrawable(Color.GRAY)
            ).into(imageView)
        }
    }

    private val reviewsFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = ArrayList<MovieReview>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(localMovieReviewsList)
            } else {
                val pattern = constraint.toString().trim()
                localMovieReviewsList.forEach {
                    if (it.title.startsWith(pattern, true)) {
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.let {
                (movieReviews as ArrayList).clear()
                movieReviews.addAll(it.values as ArrayList<MovieReview>)
                notifyDataSetChanged()
            }
        }

    }
}


