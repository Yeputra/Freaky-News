package id.freaky.newsapp.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.freaky.newsapp.Fragment.PagerAdapter
import id.freaky.newsapp.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabs_main.setSelectedTabIndicatorColor(Color.parseColor("#273483"))
        tabs_main.setTabTextColors(Color.parseColor("#41000000"), Color.parseColor("#273483"))

        supportActionBar?.elevation=0F
        supportActionBar?.title = "news"
        viewpager_main.adapter = PagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)

        viewpager_main.offscreenPageLimit = 4

    }

}
