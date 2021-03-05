package me.ezzattharwat.breakingbad.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.characters_list_item.view.*
import me.ezzattharwat.breakingbad.R
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.utils.CharactersDiffUtil

class CharactersAdapter(private val context: Context) : RecyclerView.Adapter<CharactersAdapter.RecipeViewHolder>() {

    private var characters: List<CharactersResponseItem> = listOf()

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

        fun bind(item : CharactersResponseItem) {

            Glide.with(context).load(item.img).diskCacheStrategy(DiskCacheStrategy.ALL).into(itemView.characterImg)

            val characterName = "${item.name} (${item.nickname})"
            itemView.characterNameTV.text = characterName

            itemView.characterAgeTV.text = item.birthday

        }
    }
}