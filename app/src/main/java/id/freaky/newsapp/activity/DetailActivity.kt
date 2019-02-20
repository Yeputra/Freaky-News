package id.freaky.newsapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import id.freaky.newsapp.R
import org.jetbrains.anko.find

class DetailActivity : AppCompatActivity() {

    private lateinit var image: String
    private lateinit var title: String
    private lateinit var author: String
    private lateinit var content: String
    private lateinit var ivNews: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvAuthor:TextView
    private lateinit var tvContent:TextView
    private lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        image = intent.getStringExtra("image")
        title = intent.getStringExtra("title")
        author = intent.getStringExtra("author")
        content = intent.getStringExtra("content")

        ivNews = find(R.id.iv_news_detail)
        tvTitle = find(R.id.tv_title_detail)
        tvAuthor = find(R.id.tv_author_detail)
        tvContent = find(R.id.tv_content_detail)
        btnBack = find(R.id.btn_back_toolbar)

        tvTitle.text = title
        tvAuthor.text = author
        tvContent.text = content

        val options = RequestOptions()
            .fitCenter()
            .placeholder(id.freaky.newsapp.R.drawable.placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()

        Glide.with(this)
            .setDefaultRequestOptions(options)
            .load(image)
            .into(ivNews)

        btnBack.setOnClickListener {
            onBackPressed()
        }

    }

}
