package com.poussiere.hellokotlin

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.poussiere.hellokotlin.data.Card
import kotlinx.android.synthetic.main.recycler_element.view.*

/**
 * Created by poussiere on 21/01/18.
 */
class MyRecyclerViewAdapter (cardList: MutableList<Card>, clickHandler : AdapterOnClickHandler, screenWidth: Int) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    //La liste des cartes récupérée dans le constructeur
    var myCardList : MutableList<Card> = cardList
    var screen = screenWidth

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


        
        fun bindItems(card : Card) {

            // La hauteur des cellules est égale à leur largeur. Pour obtenir leur largeur, on divise par 4 la largeur de l'écran en pixel car il y a 4 cases par ligne
            //Cette largeur d'écran a été calcuée dans la GameActivity et transmise via le constructeur
            var layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screen/4)
            itemView.song_card_image.setLayoutParams(layoutParams)

            itemView.song_card_image.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
          
            if (card.discovered) {
                itemView.song_card_image.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))

            }
            else if (card.checked){
                itemView.song_card_image.setBackgroundColor(ContextCompat.getColor(context, R.color.colorChecked))
            }
            else {
                itemView.song_card_image.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            }



        }

        override fun onClick(p0: View?) {

            
        myClickHandler.doSomethingFromActivityWhenClick(adapterPosition)


        }

        }

    }



