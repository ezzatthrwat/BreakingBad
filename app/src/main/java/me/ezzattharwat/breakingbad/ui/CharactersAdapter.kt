package me.ezzattharwat.breakingbad.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.characters_list_item.view.*
import me.ezzattharwat.breakingbad.R
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.util.*

class CharactersAdapter(private val context: Context) : RecyclerView.Adapter<CharactersAdapter.RecipeViewHolder>() {

    private var characters: List<CharactersResponseItem> = emptyList()

    fun setData(newCharacters: List<CharactersResponseItem>){
        val diffCallback = CharactersDiffUtil(characters, newCharacters)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        characters = diffCallback.getNewItems()
        diffResult.dispatchUpdatesTo(this)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.characters_list_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

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