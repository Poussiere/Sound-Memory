package com.poussiere.hellokotlin

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.poussiere.hellokotlin.objects.Card
import kotlinx.android.synthetic.main.recycler_element.view.*


/*Copyright (C) <2018>  <Nicolas BOUTIN>
        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.
        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.
        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/
        contact us : von.linnasta@gmail.com
*/

class MyRecyclerViewAdapter(cardList: MutableList<Card>, clickHandler: AdapterOnClickHandler, screenWidth: Int) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    //La liste des cartes récupérée dans le constructeur
    var myCardList: MutableList<Card> = cardList
    var screen = screenWidth
    var myClickHandler: AdapterOnClickHandler = clickHandler


    interface AdapterOnClickHandler {

        fun doSomethingFromActivityWhenClick(index: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {

        var layoutView = LayoutInflater.from(parent?.getContext()).inflate(R.layout.recycler_element, null, false);
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

    fun updateCardsList(cardList: MutableList<Card>) {
        myCardList = cardList
    }


    inner class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        //similar to getContext in java
        var context = itemView?.context;


        init {
            itemView?.setOnClickListener(this)
        }


        fun bindItems(card: Card) {

            // La hauteur des cellules est égale à leur largeur. Pour obtenir leur largeur, on divise par 4 la largeur de l'écran en pixel car il y a 4 cases par ligne
            //Cette largeur d'écran a été calcuée dans la GameActivity et transmise via le constructeur
            var layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screen / 4)
            itemView.song_card_image.setLayoutParams(layoutParams)

            itemView.song_card_image.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

            if (card.discovered || card.discovered2) {
                if (card.discovered) itemView.song_card_image.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
                if (card.discovered2) itemView.song_card_image.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPlayer2))
            } else if (card.checked) {
                itemView.song_card_image.setBackgroundColor(ContextCompat.getColor(context, R.color.colorChecked))
            } else {
                itemView.song_card_image.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            }


        }

        override fun onClick(p0: View?) {


            myClickHandler.doSomethingFromActivityWhenClick(adapterPosition)


        }

    }

}



