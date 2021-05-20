package me.ezzattharwat.breakingbad.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.characters_list_item.view.*
import me.ezzattharwat.breakingbad.R
import me.ezzattharwat.breakingbad.domain.CharactersResponseItem
import me.ezzattharwat.breakingbad.util.*
import me.ezzattharwat.breakingbad.util.loadImage
import me.ezzattharwat.breakingbad.util.toGone
import me.ezzattharwat.breakingbad.util.toVisible

class CharactersAdapter(private val context: Context)
    : ListAdapter<CharactersResponseItem, CharactersAdapter.RecipeViewHolder>(
    CharactersDiffUtil()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.characters_list_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(character : CharactersResponseItem) {

            with(character){
                itemView.characterImg.loadImage(context, img)
                val characterName = "$name (${nickname})"
                itemView.characterNameTV.text = characterName
                itemView.characterAgeTV.text = birthday
                if (!character.birthDateAvailability) itemView.textClockTV.toGone() else itemView.textClockTV.toVisible()
            }

        }
    }



}

