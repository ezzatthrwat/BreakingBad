package me.ezzattharwat.breakingbad.data.source.remotedata

import me.ezzattharwat.breakingbad.data.model.CharactersResponse
import me.ezzattharwat.breakingbad.utils.Constant.CHARACTERS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(CHARACTERS_ENDPOINT)
    suspend fun fetchCharacters(@Query("limit") rows: Int) : Response<CharactersResponse>

}