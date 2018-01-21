package com.poussiere.hellokotlin

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by poussiere on 21/01/18.
 */
class MyRecyclerViewAdapter : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {

    }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {

    }


    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {



        override fun onClick(p0: View?) {

        }
    }
}


