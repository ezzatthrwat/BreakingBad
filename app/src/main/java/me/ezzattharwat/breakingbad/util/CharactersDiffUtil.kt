package me.ezzattharwat.breakingbad.util

import androidx.recyclerview.widget.DiffUtil
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem

class CharactersDiffUtil  : DiffUtil.ItemCallback<CharactersResponseItem> () {

    override fun areItemsTheSame(oldItem: CharactersResponseItem, newItem: CharactersResponseItem): Boolean {
        return oldItem.charId == newItem.charId
    }

    override fun areContentsTheSame(oldItem: CharactersResponseItem, newItem: CharactersResponseItem): Boolean {
        return oldItem == newItem
    }

}