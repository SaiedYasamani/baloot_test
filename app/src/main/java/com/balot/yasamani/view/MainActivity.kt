package com.balot.yasamani.view


import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import com.balot.yasamani.R
import com.balot.yasamani.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        initView()
    }

    private fun initView() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> selectHomePage()
                R.id.profile -> selectProfilePage()
            }
            return@OnNavigationItemSelectedListener true
        })
    }

    private fun selectHomePage() {
        findNavController(R.id.navHostFragmentContainer).navigate(R.id.homeFragment)
    }

    private fun selectProfilePage() {
        findNavController(R.id.navHostFragmentContainer).navigate(R.id.profile)
    }
}