package me.ezzattharwat.breakingbad.data.repository

import me.ezzattharwat.breakingbad.data.model.CharactersResponse
import me.ezzattharwat.breakingbad.utils.Resource


interface AppRepository {

    suspend fun fetchCharactersFromRemoteSource(): Resource<CharactersResponse>

}