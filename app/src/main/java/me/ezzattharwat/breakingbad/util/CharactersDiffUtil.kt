package me.ezzattharwat.breakingbad.util

import androidx.recyclerview.widget.DiffUtil
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem

class CharactersDiffUtil constructor(
    private val oldList: List<CharactersResponseItem>,
    private val newList: List<CharactersResponseItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].charId == newList[newItemPosition].charId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].equals(newList[newItemPosition].charId)
    }

    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }

    fun getNewItems(): List<CharactersResponseItem> = newList

}