package id.freaky.newsapp.model

import com.google.gson.annotations.SerializedName

data class Source(@SerializedName("name")
                  var name: String = "",
                  @SerializedName("id")
                  var id: String = "")