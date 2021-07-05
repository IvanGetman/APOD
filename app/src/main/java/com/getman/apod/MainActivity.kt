package com.getman.apod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.getman.apod.ui.startFragment.PictureOfTheDayFragment

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }

}