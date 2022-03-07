package com.example.kotlinimageslider.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinimageslider.Model.NavigationItems
import com.example.kotlinimageslider.R

class NavigationAdapter(private val context: Context,private val items:List<NavigationItems>,private val listener:NavigationAdapter.NavItemClickListener):RecyclerView.Adapter<NavigationAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): NavigationAdapter.MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_navigation,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: NavigationAdapter.MyViewHolder, position: Int) {
       val datas=items[position]
        holder.itemname.text=datas.itemName
        Glide.with(context).load(datas.itemImage).into(holder.icon)

        holder.itemView.setOnClickListener{
            listener.ItemClick(datas.itemID,datas.itemName)
        }

    }

    override fun getItemCount(): Int {
      return items.size
    }
    class MyViewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {
        val icon=itemview.findViewById<ImageView>(R.id.ivNavigation)
        val itemname=itemview.findViewById<TextView>(R.id.tvNavigationName)

    }
    interface NavItemClickListener {
        fun ItemClick(id: Int,title:String?)
    }
}