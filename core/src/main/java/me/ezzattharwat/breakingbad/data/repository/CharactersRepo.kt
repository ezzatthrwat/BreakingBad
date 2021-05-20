package me.ezzattharwat.breakingbad.data.repository

import me.ezzattharwat.breakingbad.core_utils.util.Resource
import me.ezzattharwat.breakingbad.domain.CharactersResponseItem

interface CharactersRepo {

    suspend fun requestCharacters(): Resource<List<CharactersResponseItem>>

}