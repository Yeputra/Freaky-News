package id.freaky.newsapp.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView

import id.freaky.newsapp.API.APIClient
import id.freaky.newsapp.API.APIInterface
import id.freaky.newsapp.BuildConfig
import id.freaky.newsapp.adapter.NewsAdapter
import id.freaky.newsapp.R
import id.freaky.newsapp.adapter.NewsAdapterHorizontal
import id.freaky.newsapp.model.ArticlesItem
import id.freaky.newsapp.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    companion object {

        val api_key: String = BuildConfig.api_key
        val country: String = "id"
        val category: String = "Business"
        lateinit var tvFeatured: TextView
        lateinit var tvPopular: TextView
        lateinit var Adapter: NewsAdapter
        lateinit var AdapterHorizontal: NewsAdapterHorizontal
        lateinit var rvHome:RecyclerView
        lateinit var rvHomeHorizontal:RecyclerView
        lateinit var prB:ProgressBar
        lateinit var sw:SwipeRefreshLayout

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        rvHomeHorizontal = rootView.findViewById(R.id.rv_home_horizontal)
        rvHome = rootView.findViewById(R.id.rv_home)
        tvFeatured = rootView.findViewById(R.id.tv_featured_home)
        tvPopular = rootView.findViewById(R.id.tv_popular_home)
        prB = rootView.findViewById(R.id.pb)
        sw = rootView.findViewById(R.id.swipe)

        rvHomeHorizontal.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvHomeHorizontal.setHasFixedSize(true)

        rvHome.layoutManager = LinearLayoutManager(activity)
        rvHome.setHasFixedSize(true)

        viewsHide(rvHome, rvHomeHorizontal)
        retrieveNews(rvHome, rvHomeHorizontal)

        sw.setOnRefreshListener {
            retrieveNews(rvHome, rvHomeHorizontal)
        }

        return rootView

    }

    fun viewsHide(rvHome: RecyclerView, rvHomeHorizontal: RecyclerView){
        rvHome.visibility = View.INVISIBLE
        rvHomeHorizontal.visibility = View.INVISIBLE
        tvFeatured.visibility = View.INVISIBLE
        tvPopular.visibility = View.INVISIBLE
        prB.visibility = View.VISIBLE
    }

    fun onItemsLoadComplete() {
        sw.isRefreshing = false
    }

    fun retrieveNews(rvHome: RecyclerView, rvHomeHorizontal: RecyclerView) {

        var apiServices =APIClient.client.create(APIInterface::class.java)

        val callFeatured = apiServices.getFeatured(country, api_key)

        val callForYou = apiServices.getForYou(country, category, api_key)

        callFeatured.enqueue(object : Callback<News> {
            override fun onResponse(callFeatured: Call<News>, response: Response<News>) {
                var listOfNews: List<ArticlesItem> = response.body()?.articles!!

                AdapterHorizontal = NewsAdapterHorizontal(activity, listOfNews)
                rvHomeHorizontal.setAdapter(AdapterHorizontal)

                rvHomeHorizontal.visibility = View.VISIBLE
                tvFeatured.visibility = View.VISIBLE
                tvPopular.visibility = View.VISIBLE
                prB.visibility = View.INVISIBLE

            }

            override fun onFailure(call: Call<News>?, t: Throwable?) {
            }
        })

        callForYou.enqueue(object : Callback<News> {
            override fun onResponse(callForYou: Call<News>, response: Response<News>) {
                var listOfNews: List<ArticlesItem> = response.body()?.articles!!

                Adapter = NewsAdapter(activity, listOfNews)
                rvHome.setAdapter(Adapter)

                rvHome.visibility = View.VISIBLE
                tvFeatured.visibility = View.VISIBLE
                tvPopular.visibility = View.VISIBLE
                prB.visibility = View.INVISIBLE

                onItemsLoadComplete()

            }

            override fun onFailure(call: Call<News>?, t: Throwable?) {
            }
        })

    }
}
