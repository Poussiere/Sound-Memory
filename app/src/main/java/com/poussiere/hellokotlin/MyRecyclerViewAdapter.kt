package com.poussiere.hellokotlin

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.poussiere.hellokotlin.data.Card
import kotlinx.android.synthetic.main.recycler_element.view.*

/**
 * Created by poussiere on 21/01/18.
 */
class MyRecyclerViewAdapter (cardList: MutableList<Card>, clickHandler : AdapterOnClickHandler, screenHeight : Int) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    //La liste des cartes récupérée dans le constructeur
    var myCardList : MutableList<Card> = cardList

    var myClickHandler : AdapterOnClickHandler = clickHandler


    interface AdapterOnClickHandler {

        fun doSomethingFromActivityWhenClick(index: Int)
    }



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {

        var layoutView= LayoutInflater.from(parent?.getContext()).inflate(R.layout.recycler_element, null, false);
        var viewHolder = MyViewHolder(layoutView);
        return viewHolder;
    }

    override fun getItemCount(): Int {
        return 20;
    }

    //To avoid the blink when recyclerview is refreshed

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {

        var card = myCardList[position]
        holder?.bindItems(card);
        
   
       

        
    }

    fun updateCardsList (cardList : MutableList<Card>) {
    myCardList=cardList
    }


    inner class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        //similar to getContext in java
        var context = itemView?.context;

       init {
           itemView?.setOnClickListener(this)
       }
        
        //Make recyclerview fit screen size
        LayoutParams params = song_card_image.getLayoutParams();
        
        params.height = screenHeight/5 // because there are 5 rows. 
        params.width = LayoutParams.MATCH_PARENT
        song_card_image.setLayoutParams(params)
        
        
        fun bindItems(card : Card) {



            if (card.discovered) {
                itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))

            }
            else if (card.checked){
                itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorChecked))
            }
            else {
                itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            }



        }

        override fun onClick(p0: View?) {

        myClickHandler.doSomethingFromActivityWhenClick(adapterPosition)

        }

        }

    }



