package com.pleum.thainewsapp.adpaters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.pleum.thainewsapp.R
import com.pleum.thainewsapp.databinding.ItemArticlePreviewBinding
import com.pleum.thainewsapp.models.Article
import javax.inject.Inject

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemArticlePreviewBinding ): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  article = differ.currentList[position]

        holder.binding.apply {

            Glide.with(this.root).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source?.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            this.root.setOnClickListener{
                onItemClickListener?.let { it(article) }
            }
        }


    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }


    private  var onItemClickListener: ((Article)-> Unit)? =null

    fun setOnItemClickListener(listener:(Article)-> Unit){
        onItemClickListener = listener
    }


}