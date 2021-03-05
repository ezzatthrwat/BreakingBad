package me.ezzattharwat.breakingbad.data.repository

import me.ezzattharwat.breakingbad.data.model.CharactersResponse
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.data.source.remotedata.ApiService
import me.ezzattharwat.breakingbad.utils.Resource
import javax.inject.Inject


class AppRepositoryImp @Inject constructor(private val apiService: ApiService) : AppRepository {
    override suspend fun fetchCharactersFromRemoteSource(rows: Int): Resource<List<CharactersResponseItem>> {
        return try {
            val response = apiService.fetchCharacters(rows)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Response Body() Equal Null", null)
            } else {
                Resource.error("Calling not Successful", null)
            }
        } catch (e: Exception){
            Resource.error(e.toString(), null)
        }
    }


}