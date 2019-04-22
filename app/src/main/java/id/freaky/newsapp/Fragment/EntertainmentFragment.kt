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
import id.freaky.newsapp.Presenter.EntertainmentPresenter
import id.freaky.newsapp.R
import id.freaky.newsapp.View.EntertainmentView
import id.freaky.newsapp.adapter.OtherAdapter
import id.freaky.newsapp.model.ArticlesItem
import org.jetbrains.anko.support.v4.find

class EntertainmentFragment : Fragment(), EntertainmentView {

    private lateinit var presenter: EntertainmentPresenter
    private lateinit var adapter: OtherAdapter
    private lateinit var rvEntertainment: RecyclerView
    private lateinit var pbEntertainment: ProgressBar
    private lateinit var swEntertainment: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvEntertainment = find(R.id.rv_entertainment)
        pbEntertainment = find(R.id.pb_entertainment)
        swEntertainment = find(R.id.sw_entertainment)

        presenter = EntertainmentPresenter(this)

        rvEntertainment.layoutManager = LinearLayoutManager(activity)
        rvEntertainment.setHasFixedSize(true)

        hideViews()
        presenter.retrieveNews()
        swEntertainment.setOnRefreshListener {
            presenter.retrieveNews()
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

    override fun showEntertainmentList(data: List<ArticlesItem>) {

        adapter = OtherAdapter(activity, data)
        rvEntertainment.setAdapter(adapter)

        rvEntertainment.visibility = View.VISIBLE
        pbEntertainment.visibility = View.INVISIBLE
        onItemsLoadComplete()

    }

}
