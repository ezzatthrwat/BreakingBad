package me.ezzattharwat.breakingbad.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.characters_list_item.view.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import me.ezzattharwat.breakingbad.data.model.CharactersResponse
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.data.source.remotedata.ApiService
import me.ezzattharwat.breakingbad.utils.*
import javax.inject.Inject


class AppRepositoryImp @Inject constructor(private val apiService: ApiService) : AppRepository {

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    private var lastRequestedRows = 10


    override suspend fun fetchCharactersFromRemoteSource() : Pair<Boolean, Resource<List<CharactersResponseItem>>> {

        isRequestInProgress = true
        var requestIsSuccessful = false
        var resource: Resource<List<CharactersResponseItem>>

        try {
            val response = apiService.fetchCharacters(lastRequestedRows)
            if (response.isSuccessful) {
                resource = response.body()?.let {
                        return@let Resource.success(it)
                    } ?: Resource.error("Response Body() Equal Null", null)

                requestIsSuccessful = true

            } else {
                resource = Resource.error("Calling not Successful", null)
            }
        } catch (e: Exception){
            resource = Resource.error(e.toString(), null)
        }

        isRequestInProgress = false
        return Pair(requestIsSuccessful, resource)
    }

    override suspend fun requestMore() : Resource<List<CharactersResponseItem>>{
        if (isRequestInProgress) return Resource.paginatingLoading(null)
        val (requestIsSuccessful, recourse) = fetchCharactersFromRemoteSource()
        if (requestIsSuccessful) {
            lastRequestedRows += 10
        }
        return recourse
    }

}