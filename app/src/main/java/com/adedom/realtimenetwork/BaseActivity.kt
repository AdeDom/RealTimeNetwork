package com.adedom.realtimenetwork

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var networkConnectionLiveData: NetworkConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        networkConnectionLiveData.observe(this) { isInternetAvailable ->
            if (isInternetAvailable) {
                val viewGroup = findViewById(android.R.id.content) as? ViewGroup
                val popupNetwork = findViewById(R.id.rootPopupNetwork) as? RelativeLayout
                viewGroup?.removeView(popupNetwork)
            } else {
                val viewGroup = findViewById(android.R.id.content) as? ViewGroup
                val viewInflate =
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE) as? LayoutInflater
                viewInflate?.inflate(R.layout.popup_networking, viewGroup)
            }
        }
    }
}