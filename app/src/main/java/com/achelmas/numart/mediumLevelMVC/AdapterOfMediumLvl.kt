package com.achelmas.numart.mediumLevelMVC

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.achelmas.numart.GameActivity
import com.achelmas.numart.R

class AdapterOfMediumLvl(var activity: Activity, var mediumLvlList: ArrayList<ModelOfMediumLvl>) : RecyclerView.Adapter<AdapterOfMediumLvl.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view: View = LayoutInflater.from(activity).inflate(R.layout.levels_card_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = mediumLvlList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model: ModelOfMediumLvl = mediumLvlList.get(position)
        holder.targetOfStar.text = model.target
        holder.targetOfTitle.text = "${model.targetNumber}. ${activity.resources.getString(R.string.our_target)} ${model.target}"
    }

    inner class MyViewHolder(i: View) : RecyclerView.ViewHolder(i) , View.OnClickListener {
        var targetOfStar: TextView
        var targetOfTitle: TextView
        var levelButton: CardView

        init {
            targetOfStar = i.findViewById(R.id.levelsCardItem_targetOfStarId)
            targetOfTitle = i.findViewById(R.id.levelsCardItem_targetOfTitleId)
            levelButton = i.findViewById(R.id.levelsCardItem_buttonId)

            levelButton.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            var intent = Intent(activity , GameActivity::class.java)
            //The position of each target
            var position: Int = getLayoutPosition()
            var modelOfMediumLvl = mediumLvlList.get(position)

            intent.putExtra("Target" , modelOfMediumLvl.target)
            intent.putExtra("Number1", modelOfMediumLvl.number1)
            intent.putExtra("Number2" , modelOfMediumLvl.number2)
            intent.putExtra("Number3" , modelOfMediumLvl.number3)
            intent.putExtra("Number4" , modelOfMediumLvl.number4)
            intent.putExtra("Number5" , modelOfMediumLvl.number5)

            activity.startActivity(intent)
        }
    }
}