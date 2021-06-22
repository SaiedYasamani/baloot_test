package com.balot.yasamani.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.balot.yasamani.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        Glide.with(requireContext()).load(Uri.parse("https://avatars.githubusercontent.com/u/23740341?v=4")).into(binding.profile)
        binding.gitHub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/SaiedYasamani")
            startActivity(intent)
        }
        binding.aboutMe.setOnClickListener{
        }
    }
}