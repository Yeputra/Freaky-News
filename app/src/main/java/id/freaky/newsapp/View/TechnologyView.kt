package id.freaky.newsapp.View

import id.freaky.newsapp.model.ArticlesItem

interface TechnologyView {
    fun showTechnologyList(data: List<ArticlesItem>)
}