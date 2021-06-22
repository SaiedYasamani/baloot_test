package com.balot.yasamani.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.balot.yasamani.R
import com.balot.yasamani.databinding.FragmentSplashBinding
import java.util.*


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.appVersion.text = requireContext().packageManager.getPackageInfo(requireContext().packageName,0).versionName
        Timer().schedule(object :TimerTask(){
            override fun run() {
                goToHomeFragment()
            }

        },3000)
    }

    private fun goToHomeFragment() {
        findNavController().navigate(R.id.homeFragment)
    }
}