package com.poussiere.hellokotlin

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_element.view.*

/**
 * Created by poussiere on 21/01/18.
 */
class MyRecyclerViewAdapter : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    var myCardList=ArrayList<Boolean>(20)



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {

        var layoutView= LayoutInflater.from(parent?.getContext()).inflate(R.layout.recycler_element, null, false);
        var viewHolder = MyViewHolder(layoutView);
        return viewHolder;
    }

    override fun getItemCount(): Int {
        return 20;
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {

        var cardIsBlue = myCardList[position]
        holder?.bindItems(cardIsBlue);
    }

    fun setCardsList (cardList : ArrayList<Boolean>) {
    myCardList=cardList
    }


    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


       init {
           itemView?.setOnClickListener(this)
       }

        fun bindItems(cardIsBlue : Boolean) {

            //similar to getContext in java
            var context = itemView.context;

            if (cardIsBlue) {
                itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))

            }else {
                itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
            }



        }

        override fun onClick(p0: View?) {

        }
    }
}


