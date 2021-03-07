package me.ezzattharwat.breakingbad.data.repository


import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.util.Resource


interface CharactersRepo {

    suspend fun requestCharacters(): Resource<List<CharactersResponseItem>>

}