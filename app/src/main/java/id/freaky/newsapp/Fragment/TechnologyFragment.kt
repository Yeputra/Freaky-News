package id.freaky.newsapp.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import id.freaky.newsapp.BuildConfig
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import id.freaky.newsapp.API.APIClient
import id.freaky.newsapp.API.APIInterface
import id.freaky.newsapp.Presenter.TechnologyPresenter
import id.freaky.newsapp.R
import id.freaky.newsapp.View.TechnologyView
import id.freaky.newsapp.adapter.OtherAdapter
import id.freaky.newsapp.model.ArticlesItem
import id.freaky.newsapp.model.News
import org.jetbrains.anko.support.v4.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TechnologyFragment : Fragment(), TechnologyView {

    private lateinit var presenter: TechnologyPresenter
    private lateinit var adapter: OtherAdapter
    private lateinit var rvTech: RecyclerView
    private lateinit var pbTech: ProgressBar
    private lateinit var swTech: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvTech = find(R.id.rv_tech)
        pbTech = find(R.id.pb_tech)
        swTech = find(R.id.sw_tech)

        rvTech.layoutManager = LinearLayoutManager(activity)
        rvTech.setHasFixedSize(true)

        viewsHide()
        presenter = TechnologyPresenter(this)
        presenter.retrieveNews()
        swTech.setOnRefreshListener {
            presenter.retrieveNews()
        }
    }

    fun onItemsLoadComplete() {
        swTech.isRefreshing = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_technology, container, false)
    }

    fun viewsHide(){
        rvTech.visibility = View.INVISIBLE
        pbTech.visibility = View.VISIBLE
    }

    override fun showTechnologyList(data: List<ArticlesItem>) {

        adapter = OtherAdapter(activity, data)
        rvTech.setAdapter(adapter)

        rvTech.visibility = View.VISIBLE
        pbTech.visibility = View.INVISIBLE
        onItemsLoadComplete()

    }


}
