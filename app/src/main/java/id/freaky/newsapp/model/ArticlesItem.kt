package id.freaky.newsapp.model

import com.google.gson.annotations.SerializedName

data class ArticlesItem(@SerializedName("publishedAt")
                        var publishedAt: String = "",
                        @SerializedName("author")
                        var author: String = "",
                        @SerializedName("urlToImage")
                        var urlToImage: String = "",
                        @SerializedName("description")
                        var description: String = "",
                        @SerializedName("source")
                        var source: Source,
                        @SerializedName("title")
                        var title: String = "",
                        @SerializedName("url")
                        var url: String = "",
                        @SerializedName("content")
                        var content: String = "")