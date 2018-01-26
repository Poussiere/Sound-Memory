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
class MyRecyclerViewAdapter (cardList: MutableList<Card>) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    //La liste des cartes récupérée dans le constructeur
    var myCardList : MutableList<Card> = cardList

    //Un int qui va servir à stocker temporairement l'index de la carte précédemment cliquée
    var tempIndex : Int=1

    //Un boolean qui va indiquer si la carte cliquée est la première ou la deuxième
    var firstCard : Boolean = true



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {

        var layoutView= LayoutInflater.from(parent?.getContext()).inflate(R.layout.recycler_element, null, false);
        var viewHolder = MyViewHolder(layoutView);
        return viewHolder;
    }

    override fun getItemCount(): Int {
        return 20;
    }

    //To avoid the blink when recyclerview is refreshing. 
    @Override
public long getItemId(int position) {
    return myCardList.get(position).getId();
}
    
    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {

        var card = myCardList[position]
        holder?.bindItems(card);
    }

    fun updateCardsList (cardList : MutableList<Card>) {
    myCardList=cardList
    }


    inner class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


       init {
           itemView?.setOnClickListener(this)
       }

        fun bindItems(card : Card) {

            //similar to getContext in java
            var context = itemView.context;

            if (card.discovered) {
                itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))

            }else {
                itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            }



        }

        override fun onClick(p0: View?) {
            var context = itemView.context;

        if (firstCard){
            itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
            tempIndex=adapterPosition
        }
        }
        
        // Cette méthode sera lancée
        fun doWhenFirstClick(){
         var context = itemView.context; 
         itemView.song_card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
          /* stocker l'index de l'objet card dans un Int temporaire. Agir sur cet objet (ajouter une variable peut être si 
            discovered est pas suffisante)pour modifier le recyclerivew dans son ensemble en l'updatant. Ainsi, pas 
            besoin de bidouille pour accéder à une vue depuis une autre vue. 
        Faire un notifyDataSetChage après chaque clique dans la GameActivity. 
        */
        }
       
        fun doWhenSecondClick(){
        
            
        }
    }
}


