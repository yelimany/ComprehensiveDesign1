package com.example.flatload

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val fragmentCommunity by lazy { CommunityFragment() }
    private val fragmentInputWay by lazy { InputWayFragment() }
    private val fragmentMap by lazy { MapFragment() }
    private val fragmentAddRisk by lazy { AddRiskFragment() }
    private val fragmentRocalSearch by lazy{ RocalSearchFragment()}
//    var itemList = mutableListOf<ItemList>()
    //네이버
    private lateinit var naverMap: NaverMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val localAdapter = ListViewAdapter(itemList)
//        listView.adapter = localAdapter
        init()
    }
    override fun onCreateOptionsMenu(menu: Menu):Boolean{
        super.onCreateOptionsMenu(menu)
        getMenuInflater().inflate(R.menu.menu_top,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean{
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.five ->{
                changeFragment(fragmentRocalSearch)
            }
        }

        return true
    }
    private fun init() {
//        textView9.setOnClickListener {
//            val i = Intent(this,InputWayActivity::class.java)
//            startActivity(i)
//        }
//        testBtn.setOnClickListener {
//            val i = Intent(this,MapActivity::class.java)
//            startActivity(i)
//        }
//        testGetBtn.setOnClickListener{
//            val i = Intent(this,TestGetServerActivity::class.java)
//            startActivity(i)
//        }
        bnv_main.run {
            setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.first -> {
                        changeFragment(fragmentMap)
                    }
                    R.id.second -> {
                        changeFragment(fragmentInputWay)
                    }
                    R.id.third -> {
                        changeFragment(fragmentCommunity)
                    }
                    R.id.four -> {
                        changeFragment(fragmentAddRisk)
                    }
                }
                true
            }
            selectedItemId = R.id.first
        }
    }


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }

}