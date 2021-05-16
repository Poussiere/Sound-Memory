package com.poussiere.hellokotlin.views

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.poussiere.hellokotlin.R
import com.poussiere.hellokotlin.model.Card

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

class MyRecyclerViewAdapter(cardList: MutableList<Card>, spanCount: Int, clickHandler: AdapterOnClickHandler, screenWidth: Int) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    var myCardList: MutableList<Card> = cardList
    var screen = screenWidth
    var myClickHandler: AdapterOnClickHandler = clickHandler
    var sCount = spanCount


    interface AdapterOnClickHandler {

        fun doSomethingFromActivityWhenClick(index: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_element, null, false)
        return MyViewHolder(layoutView, sCount)
    }

    override fun getItemCount(): Int {
        return myCardList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val card = myCardList[position]
        holder.bindItems(card)
    }

    fun updateCardsList(cardList: MutableList<Card>) {
        myCardList = cardList
    }

    inner class MyViewHolder(itemView: View, spanCount: Int) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val context = itemView.context
        var sCount = spanCount

        init {
            itemView.setOnClickListener(this)
        }

        private val songCardImage: View = itemView.findViewById(R.id.song_card_image)

        fun bindItems(card: Card) {

            // La hauteur des cellules est égale à leur largeur. Pour obtenir leur largeur, on divise par 4 la largeur de l'écran en pixel car il y a 4 cases par ligne
            //Cette largeur d'écran a été calculée dans la GameBoardActivity et transmise via le constructeur
            val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screen / sCount)
            songCardImage.layoutParams = layoutParams
            songCardImage.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)

            if (card.discovered || card.discovered2) {
                if (card.discovered) songCardImage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
                if (card.discovered2) songCardImage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPlayer2))
            } else if (card.checked) {
                songCardImage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorChecked))
            } else {
                songCardImage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            }
        }

        override fun onClick(p0: View?) {
            myClickHandler.doSomethingFromActivityWhenClick(layoutPosition)
        }
    }
}



