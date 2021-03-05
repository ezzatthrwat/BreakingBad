package me.ezzattharwat.breakingbad.data.repository

import kotlinx.coroutines.flow.Flow
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.utils.Resource


interface AppRepository {

    suspend fun fetchCharactersFromRemoteSource(): Pair<Boolean, Resource<List<CharactersResponseItem>>>
    suspend fun requestMore(): Resource<List<CharactersResponseItem>>


}