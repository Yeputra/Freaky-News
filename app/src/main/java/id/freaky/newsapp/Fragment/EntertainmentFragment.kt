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
import id.freaky.newsapp.BuildConfig
import id.freaky.newsapp.API.APIClient
import id.freaky.newsapp.API.APIInterface
import id.freaky.newsapp.R
import id.freaky.newsapp.adapter.OtherAdapter
import id.freaky.newsapp.model.ArticlesItem
import id.freaky.newsapp.model.News
import org.jetbrains.anko.support.v4.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntertainmentFragment : Fragment() {

    val api_key: String = BuildConfig.api_key
    val country: String = "id"
    val category: String = "Entertainment"
    lateinit var adapter: OtherAdapter
    lateinit var rvEntertainment: RecyclerView
    lateinit var pbEntertainment: ProgressBar
    lateinit var swEntertainment: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvEntertainment = find(R.id.rv_entertainment)
        pbEntertainment = find(R.id.pb_entertainment)
        swEntertainment = find(R.id.sw_entertainment)

        rvEntertainment.layoutManager = LinearLayoutManager(activity)
        rvEntertainment.setHasFixedSize(true)

        hideViews()
        retrieveNews()
        swEntertainment.setOnRefreshListener {
            retrieveNews()
        }
    }

    fun onItemsLoadComplete() {
        swEntertainment.isRefreshing = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entertainment, container, false)
    }

    fun hideViews(){
        rvEntertainment.visibility = View.INVISIBLE
        pbEntertainment.visibility = View.VISIBLE
    }

    fun retrieveNews(){

        var apiServices = APIClient.client.create(APIInterface::class.java)

        val callEntertainment = apiServices.getEntertainment(country,category,api_key)
        callEntertainment.enqueue(object : Callback<News> {
            override fun onResponse(callEntertainment: Call<News>, response: Response<News>) {
                var listOfNews: List<ArticlesItem> = response.body()?.articles!!

                adapter = OtherAdapter(activity, listOfNews)
                rvEntertainment.setAdapter(adapter)

                rvEntertainment.visibility = View.VISIBLE
                pbEntertainment.visibility = View.INVISIBLE
                onItemsLoadComplete()
            }

            override fun onFailure(call: Call<News>?, t: Throwable?) {

            }
        })

    }


}
