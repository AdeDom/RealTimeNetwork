package com.adedom.realtimenetwork

import android.os.Bundle
import com.adedom.realtimenetwork.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            openMainFragment()
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