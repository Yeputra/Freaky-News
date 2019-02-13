package id.freaky.newsapp.model

import com.google.gson.annotations.SerializedName

data class News(@SerializedName("totalResults")
                var totalResults: Int = 0,
                @SerializedName("articles")
                var articles: List<ArticlesItem>?,
                @SerializedName("status")
                var status: String = "")