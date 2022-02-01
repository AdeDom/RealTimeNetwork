package com.adedom.realtimenetwork

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.adedom.realtimenetwork.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var networkConnectionLiveData: NetworkConnectionLiveData

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openMainFragment()
        }

        networkConnectionLiveData.observe(this) { isInternetAvailable ->
            if (isInternetAvailable) {
                val popupNetwork = findViewById(R.id.rootPopupNetwork) as? RelativeLayout
                binding.root.removeView(popupNetwork)
            } else {
                val viewInflate =
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE) as? LayoutInflater
                viewInflate?.inflate(R.layout.popup_networking, binding.root)
            }
        }
    }

    fun openMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.frameLayout.id, MainFragment())
            .commit()
    }

    fun openSecondFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.frameLayout.id, SecondFragment())
            .commit()
    }
}