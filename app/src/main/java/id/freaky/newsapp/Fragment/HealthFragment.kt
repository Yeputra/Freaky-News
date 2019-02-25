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
import id.freaky.newsapp.model.ArticlesItem
import id.freaky.newsapp.model.News
import org.jetbrains.anko.support.v4.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import id.freaky.newsapp.adapter.OtherAdapter

class HealthFragment : Fragment() {

    val api_key: String = BuildConfig.api_key
    val country: String = "id"
    val category: String = "Health"
    lateinit var adapter: OtherAdapter
    lateinit var rvHealth: RecyclerView
    lateinit var pb: ProgressBar
    lateinit var sw: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        rvHealth = find(R.id.rv_health)
        pb = find(R.id.pb)
        sw = find(R.id.sw_health)

        rvHealth.layoutManager = LinearLayoutManager(activity)
        rvHealth.setHasFixedSize(true)

        viewsHide()
        retrieveNews()
        sw.setOnRefreshListener {
            retrieveNews()
        }
    }

    fun onItemsLoadComplete() {
        sw.isRefreshing = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false)
    }

    fun viewsHide(){
        rvHealth.visibility = View.INVISIBLE
        pb.visibility = View.VISIBLE
    }

    fun retrieveNews() {

        var apiServices = APIClient.client.create(APIInterface::class.java)

        val callHealth = apiServices.getForYou(country,category,api_key
        )
        callHealth.enqueue(object : Callback<News> {
            override fun onResponse(callHealth: Call<News>, response: Response<News>) {
                var listOfNews: List<ArticlesItem> = response.body()?.articles!!

                adapter = OtherAdapter(activity, listOfNews)
                rvHealth.setAdapter(adapter)

                rvHealth.visibility = View.VISIBLE
                pb.visibility = View.INVISIBLE
                onItemsLoadComplete()
            }

            override fun onFailure(call: Call<News>?, t: Throwable?) {
            }
        })

    }


}