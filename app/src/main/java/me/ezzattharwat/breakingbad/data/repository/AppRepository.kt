package me.ezzattharwat.breakingbad.data.repository

import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.utils.Resource


interface AppRepository {

    suspend fun fetchCharactersFromRemoteSource(rows: Int): Resource<List<CharactersResponseItem>>

}