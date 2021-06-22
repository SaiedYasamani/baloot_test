package com.balot.yasamani.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.balot.yasamani.databinding.FragmentHomeBinding
import com.balot.yasamani.models.Result
import com.balot.yasamani.utils.ArticlesListAdapter
import com.balot.yasamani.viewModels.HomeViewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ArticlesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initView()
        subscribeData()
        getData()
        return binding.root
    }

    private fun getData() {
        viewModel.getArticles("apple")
    }

    private fun subscribeData() {
        viewModel.getArticleLiveData().observe(viewLifecycleOwner, {
            when {
                it is Result.Success -> {
                    adapter.data.clear()
                    adapter.data.addAll((it.data?: listOf()))
                    adapter.notifyDataSetChanged()
                }
                it is Result.Failure -> {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                }
                it is Result.Error -> {
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initView() {
        binding.articles.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.articles.setHasFixedSize(true)
        adapter = ArticlesListAdapter(mutableListOf())
        binding.articles.adapter = adapter
    }

}