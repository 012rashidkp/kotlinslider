package com.example.kotlinimageslider.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kotlinimageslider.Model.BannerItem
import com.example.kotlinimageslider.R
import com.smarteist.autoimageslider.SliderViewAdapter

class BannerAdapter(private val context: Context,private val items:List<BannerItem>,private val listener:BannerAdapter.BannerClickListener):SliderViewAdapter<BannerAdapter.MyViewHolder>() {


    override fun getCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): BannerAdapter.MyViewHolder {
     val view=LayoutInflater.from(context).inflate(R.layout.banner_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerAdapter.MyViewHolder?, position: Int) {
      val datas=items[position]
        if (holder != null) {
            Glide.with(context).load(datas.bannerImage).into(holder.bannerimage)
        }
        else{
            if (holder != null) {
                Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.bannerimage)
            }

        }
        if (holder != null) {
            holder.itemView.setOnClickListener{
                listener.BannerClick(datas.id,datas.bannerName)
            }
        }


    }
    class MyViewHolder(itemview:View) :SliderViewAdapter.ViewHolder(itemview) {
     val bannerimage=itemview.findViewById<ImageView>(R.id.slider_img)
    }

    interface BannerClickListener {
        fun BannerClick(id: Int?,title:String?)
    }


}