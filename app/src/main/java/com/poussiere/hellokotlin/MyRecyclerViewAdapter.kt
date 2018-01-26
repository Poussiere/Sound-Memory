package com.poussiere.hellokotlin

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.poussiere.hellokotlin.data.Card
import com.poussiere.hellokotlin.utils.Song
import kotlinx.android.synthetic.main.recycler_element.view.*

/**
 * Created by poussiere on 21/01/18.
 */
class MyRecyclerViewAdapter (cardList: MutableList<Card>) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    //La liste des cartes récupérée dans le constructeur
    var myCardList : MutableList<Card> = cardList

    //Un int qui va servir à stocker temporairement l'index de la carte précédemment cliquée
    var previousIndex : Int=1
    var actualIndex : Int=1

    //Un boolean qui va indiquer si la carte cliquée est la première ou la deuxième
    var firstCard : Boolean = true

    //Un boolean qui va indiquer si un son est entrain d'être lu
    var songIsPlaying : Boolean = false

/*
    interface AdapterOnClickHandler {

        fun doSomethingFromActivityWhenClick(index: Int)
    }
    */


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

        var player:Song=Song(context)


       init {
           itemView?.setOnClickListener(this)
       }

        fun bindItems(card : Card) {

            if (card.checked){
                itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorChecked))
            }

            else if (card.discovered) {
                itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))

            }else {
                itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            }



        }

        override fun onClick(p0: View?) {



            // Le clique ne va réagir que si la carte cliquée n'a pas encore été découverte et que si la lecture d'un son n'est pas en cours
            if (!myCardList[adapterPosition].discovered && !songIsPlaying){

                when (firstCard){
                    true -> doWhenFirstClick()
                    false -> doWhenSecondClick()
                }
            } }
        

        fun doWhenFirstClick(){

       //  itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorChecked))
            songIsPlaying=true
            previousIndex=adapterPosition
            myCardList[previousIndex].checked=true;


            player.play(myCardList[previousIndex].song)
            player.mediaPlayer?.setOnCompletionListener(){
                songIsPlaying=false
            }

            firstCard=false
            notifyDataSetChanged()

        }

        fun doWhenSecondClick(){
            songIsPlaying=true
            actualIndex=adapterPosition

           var cardsAreSimilar : Boolean = false



            player.play(myCardList[actualIndex].song)
            player.mediaPlayer?.setOnCompletionListener(){
                songIsPlaying=false
            }

            if (myCardList[previousIndex].id == myCardList[actualIndex].id){
                cardsAreSimilar = true
            }



            if (cardsAreSimilar){

                myCardList[previousIndex].discovered=true
                myCardList[actualIndex].discovered=true}


            myCardList[previousIndex].checked=false
            firstCard=true

            notifyDataSetChanged()

        }
    }
}


