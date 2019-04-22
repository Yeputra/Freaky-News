package id.freaky.newsapp.Presenter

import id.freaky.newsapp.API.APIClient
import id.freaky.newsapp.API.APIInterface
import id.freaky.newsapp.BuildConfig
import id.freaky.newsapp.View.HealthView
import id.freaky.newsapp.model.ArticlesItem
import id.freaky.newsapp.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HealthPresenter (private val view : HealthView)
{
    private val api_key: String = BuildConfig.api_key
    private val country: String = "id"
    private val category: String = "Health"

    fun retrieveNews(){

        var apiServices = APIClient.client.create(APIInterface::class.java)

        val callEntertainment = apiServices.getEntertainment(country,category,api_key)
        callEntertainment.enqueue(object : Callback<News> {
            override fun onResponse(callEntertainment: Call<News>, response: Response<News>) {
                var listOfNews: List<ArticlesItem>? = response.body()?.articles
                listOfNews?.let { view.showHealthList(it) }

            }

            override fun onFailure(call: Call<News>?, t: Throwable?) {

            }
        })

    }
}