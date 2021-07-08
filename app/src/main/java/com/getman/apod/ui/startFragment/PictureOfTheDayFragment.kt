package com.getman.apod.ui.startFragment

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.load
import com.getman.apod.R
import com.getman.apod.domain.PictureOfTheDayData

class PictureOfTheDayFragment : Fragment(R.layout.apod_fragment) {

    private val viewModel: PictureOfTheDayViewModel by viewModels()

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
            .observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                val explanation = serverResponseData.explanation
                if (url.isNullOrEmpty()) {
                    //showError("Сообщение, что ссылка пустая")
                    toast("Link is empty")
                } else {
                    view?.findViewById<ImageView>(R.id.image_view)?.load(url)
                    view?.findViewById<TextView>(R.id.explanation)?.text = explanation
                }
            }
        }
    }
//    is PictureOfTheDayData.Loading ->
//    {
//        //showLoading()
//    }
//    is PictureOfTheDayData.Error ->
//    {
//        //showError(data.error.message)
//        toast(data.error.message)
//    }


    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }
}