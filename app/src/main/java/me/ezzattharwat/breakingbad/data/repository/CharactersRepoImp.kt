package me.ezzattharwat.breakingbad.data.repository



import me.ezzattharwat.breakingbad.data.model.CharactersResponse
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.data.source.remotedata.ApiService
import me.ezzattharwat.breakingbad.util.*
import me.ezzattharwat.breakingbad.util.Errors.NETWORK_ERROR
import me.ezzattharwat.breakingbad.util.Errors.NO_INTERNET_CONNECTION
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharactersRepoImp @Inject constructor(private val apiService: ApiService,
                                            private val networkConnection: NetworkConnection) : CharactersRepo {

    private var isRequestInProgress = false

    private var lastRequestedRows = 20


    override suspend fun requestCharacters() : Resource<List<CharactersResponseItem>> {
        if(isRequestInProgress) return Resource.loading(null)
        val resource = fetchCharacters()
        if (resource.status == Status.SUCCESS && (resource.data?.size!! % 10) == 0) {
            lastRequestedRows += 20
        }
        return resource
    }

    private suspend fun fetchCharacters(): Resource<List<CharactersResponseItem>> {

        if (!networkConnection.checkNetwork()) {
            return Resource.error(NO_INTERNET_CONNECTION, null)
        }

        isRequestInProgress = true

        return try{
            val response = apiService.fetchCharacters(lastRequestedRows)
            if (response.isSuccessful) {
                isRequestInProgress = false
                Resource.success(response.body()?.let { mapCharacter(it) })
            } else {
                isRequestInProgress = false
                Resource.error(NETWORK_ERROR, null)
            }
        } catch (e: IOException) {
            isRequestInProgress = false
            Resource.error(NETWORK_ERROR, null)
        }
    }

    private fun mapCharacter(Characters : CharactersResponse): List<CharactersResponseItem> {
        return Characters.map {
            with(it) {
                CharactersResponseItem(
                    appearance = appearance,
                    betterCallSaulAppearance = betterCallSaulAppearance,
                    birthday = if (RegexUtils.isValidBirthDate(birthday)) DateUtils.getLiveAge(birthday) else birthday,
                    category = category,
                    charId = charId,
                    img = img,
                    name = name,
                    nickname= nickname,
                    occupation= occupation,
                    portrayed = portrayed,
                    status = status,
                    birthDateAvailability= RegexUtils.isValidBirthDate(birthday),
                )
            }

        }
    }
}