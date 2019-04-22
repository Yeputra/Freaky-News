package id.freaky.newsapp.View

import id.freaky.newsapp.model.ArticlesItem

interface HealthView {
    fun showHealthList(data: List<ArticlesItem>)
}