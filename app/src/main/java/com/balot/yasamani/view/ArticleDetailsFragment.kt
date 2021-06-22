package com.balot.yasamani.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.balot.yasamani.databinding.FragmentArticleDetailsBinding
import com.balot.yasamani.models.Article
import com.balot.yasamani.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ArticleDetailsFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        val arg = arguments?.getString(Constants.ARTICLE_ARGUMENT, "")
        if (arg?.isNotEmpty() == true) {
            try {
                val article = Gson().fromJson<Article>(arg, object : TypeToken<Article>() {}.type)
                binding.title.text = article.title
                binding.description.text = article.description
            } catch (e: Exception) {
                Log.d("arg_parse", "initView: ${e.message}")
            }
        }
    }
}