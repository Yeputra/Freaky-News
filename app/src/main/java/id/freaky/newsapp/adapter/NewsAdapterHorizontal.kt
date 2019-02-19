package id.freaky.newsapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import id.freaky.newsapp.model.ArticlesItem
import kotlinx.android.synthetic.main.news_list_horizontal_item.view.*

class NewsAdapterHorizontal (var context: Context?, var list: List<ArticlesItem>) : RecyclerView.Adapter<NewsAdapterHorizontal.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvTitle.text = list.get(position).title

        val options = RequestOptions()
            .centerCrop()
            .placeholder(id.freaky.newsapp.R.drawable.placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()

        context?.let { Glide.with(it).setDefaultRequestOptions(options).load(list.get(position).urlToImage).into(holder.ivImage)}
        holder.itemView.setOnClickListener {
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(context).inflate(id.freaky.newsapp.R.layout.news_list_horizontal_item, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tv_title_horizontal
        val ivImage = itemView.iv_news_horizontal
    }
}