package com.example.kotlinimageslider

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinimageslider.Adapter.BannerAdapter
import com.example.kotlinimageslider.Adapter.NavigationAdapter
import com.example.kotlinimageslider.Model.BannerItem
import com.example.kotlinimageslider.Model.NavigationItems
import com.google.android.material.snackbar.Snackbar
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class MainActivity : AppCompatActivity() {
    lateinit var sliderView: SliderView
    var datas=ArrayList<BannerItem>()
    lateinit var linearLayout: LinearLayout
    lateinit var toolbar:Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var drawerLayout: DrawerLayout
    lateinit var DrawerBtn:ImageButton
    lateinit var progressBar: ProgressBar
   var navdatas=ArrayList<NavigationItems>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sliderView=findViewById(R.id.viewpager)
        linearLayout=findViewById(R.id.snackbarlayout)
        DrawerBtn=findViewById(R.id.drawer_btn)
        recyclerView=findViewById(R.id.nav_list)
        drawerLayout=findViewById(R.id.drawerlayout)
        progressBar=findViewById(R.id.loadingbar)
        recyclerView!!.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.hasFixedSize()
        sliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = resources.getColor(R.color.new_color, this.theme)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.reddish_pink)
        }

   LoadBanners()
   LoadNavItems()
        DrawerBtn.setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
            LoadingItems()
        }

    }

    private fun LoadingItems() {
        val handler=Handler()
        handler.postDelayed({
            progressBar.visibility=View.GONE
            recyclerView.visibility=View.VISIBLE
        },3000)
    }

    private fun LoadNavItems() {
        navdatas.add(NavigationItems(1,"Account",R.drawable.ic_baseline_account_box_24))
        navdatas.add(NavigationItems(2,"Shopping Cart",R.drawable.ic_baseline_shopping_cart_24))
        navdatas.add(NavigationItems(3,"contact",R.drawable.ic_baseline_call_24))
        navdatas.add(NavigationItems(4,"User Profile",R.drawable.ic_baseline_person_outline_24))
        val adapter=NavigationAdapter(this@MainActivity,navdatas, object :NavigationAdapter.NavItemClickListener{
        
           override fun ItemClick(id: Int, title: String?) {
               DisplayMessage("your item is : $title")
               drawerLayout.closeDrawers()

           }

       })
        recyclerView.adapter=adapter
    }

    private fun LoadBanners() {

        datas.clear()
        datas.add(BannerItem(1,"burger adds",R.drawable.ad_burger))
        datas.add(BannerItem(2,"ads1",R.drawable.ads_1))
        datas.add(BannerItem(3,"offers",R.drawable.bk_deal))
        datas.add(BannerItem(4,"kfc ads",R.drawable.kfc))
        datas.add(BannerItem(5,"chicking ads",R.drawable.hotchick))
        val adapter=BannerAdapter(this,datas,object :BannerAdapter.BannerClickListener{
            override fun BannerClick(id: Int?, name: String?) {
                DisplayMessage("your item is : $name and your item id : $id")
            }

        })
        sliderView.setSliderAdapter(adapter)
    }

    private fun DisplayMessage(messages: String?) {
        if (messages != null) {
            Snackbar.make(linearLayout,messages, Snackbar.LENGTH_LONG).show()
        }
    }


}