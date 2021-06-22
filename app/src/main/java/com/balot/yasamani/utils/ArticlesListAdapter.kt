package com.balot.yasamani.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.balot.yasamani.R
import com.balot.yasamani.databinding.ItemArticleBinding
import com.balot.yasamani.models.Article
import com.google.gson.Gson

class ArticlesListAdapter(val data: MutableList<Article>) :
    RecyclerView.Adapter<ArticlesListAdapter.VH>() {
    class VH(val itemArticleBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(itemArticleBinding.root) {
        fun bind(data: MutableList<Article>, position: Int) {
            itemArticleBinding.articleTitle.text = data.get(position).title ?: ""
            itemArticleBinding.articleTitle.setOnClickListener {
                itemArticleBinding.root.findNavController().navigate(
                    R.id.articleDetailsFragment,
                    bundleOf(Pair(Constants.ARTICLE_ARGUMENT, Gson().toJson(data.get(position))))
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemArticleBinding.inflate(inflater, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(data, position)
    }

    override fun getItemCount(): Int = data.size
}