package id.freaky.newsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.freaky.newsapp.Fragment.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main
        )
        supportActionBar?.elevation=0F
        viewpager_main.adapter = PagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)
    }
}
