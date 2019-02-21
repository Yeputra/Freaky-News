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
import id.freaky.newsapp.activity.DetailActivity
import id.freaky.newsapp.model.ArticlesItem
import kotlinx.android.synthetic.main.news_list_item.view.*
import org.jetbrains.anko.startActivity

class HealthAdapter (var context: Context?, var list: List<ArticlesItem>) : RecyclerView.Adapter<HealthAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvTitle.text = list.get(position).title
        holder.tvAuthor.text = list.get(position).description

        val options = RequestOptions()
            .fitCenter()
            .placeholder(id.freaky.newsapp.R.drawable.placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()

        context?.let { Glide.with(it).setDefaultRequestOptions(options).load(list.get(position).urlToImage).into(holder.ivImage)}

        holder.itemView.setOnClickListener {
            val item = list[position]
            var image: String
            var title: String
            var author: String
            var content: String

            if (item.urlToImage != null){
                image = item.urlToImage
            }
            else{
                image = "null"
            }

            if (item.title != null){
                title = item.title
            }
            else{
                title = "No Title"
            }

            if (item.author != null){
                author = item.author
            }
            else{
                author = "No Author"
            }

            if (item.content != null){
                content = item.content
            }
            else{
                content = "No Content"
            }

            context?.startActivity<DetailActivity>("image" to image,
                "title" to title,
                "author" to author,
                "content" to content)

        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(context).inflate(id.freaky.newsapp.R.layout.news_list_item, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tv_title
        val tvAuthor = itemView.tv_author
        val ivImage = itemView.iv_image
    }
}