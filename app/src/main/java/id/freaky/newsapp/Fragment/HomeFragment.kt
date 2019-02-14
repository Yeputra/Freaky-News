package id.freaky.newsapp.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import id.freaky.newsapp.API.APIClient
import id.freaky.newsapp.API.APIInterface
import id.freaky.newsapp.BuildConfig
import id.freaky.newsapp.adapter.NewsAdapter
import id.freaky.newsapp.R
import id.freaky.newsapp.model.ArticlesItem
import id.freaky.newsapp.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    companion object {

        val api_key: String = BuildConfig.api_key
        val country: String = "id"
        lateinit var Adapter: NewsAdapter
        lateinit var rvHome:RecyclerView
        lateinit var pb:ProgressBar

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        rvHome = rootView.findViewById(R.id.rv_home)
        pb = rootView.findViewById(R.id.pb)

        rvHome.layoutManager = LinearLayoutManager(activity)
        rvHome.setHasFixedSize(true)

        pb.visibility = View.VISIBLE
        retrieveNews(rvHome)

        return rootView

    }

    fun retrieveNews(rvHome: RecyclerView) {


        var apiServices =APIClient.client.create(APIInterface::class.java)

        val call = apiServices.getHeadlines(country, api_key)

        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                var listOfNews: List<ArticlesItem> = response.body()?.articles!!
                Adapter = NewsAdapter(activity, listOfNews)
                rvHome.setAdapter(Adapter)
                pb.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<News>?, t: Throwable?) {
            }
        })

    }
}
