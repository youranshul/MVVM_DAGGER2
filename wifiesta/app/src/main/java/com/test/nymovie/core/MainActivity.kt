package com.test.nymovie.core

import android.os.Bundle
import android.util.Log
import com.test.nymovie.R
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // setTitle()
    }

    /*private fun setTitle() {
        title = resources.getString(R.string.screenTitle)
        supportFragmentManager.addOnBackStackChangedListener {

            val count = supportFragmentManager.backStackEntryCount
            Log.i("Ansh", "count :$count")
            if (count == 0) {
                title = resources.getString(R.string.screenTitle)
            }

        }
    }*/
}
