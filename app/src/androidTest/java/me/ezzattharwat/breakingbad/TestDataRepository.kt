package me.ezzattharwat.breakingbad


import me.ezzattharwat.breakingbad.TestUtil.dataStatus
import me.ezzattharwat.breakingbad.TestUtil.initData
import me.ezzattharwat.breakingbad.data.model.CharactersResponse
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.data.repository.CharactersRepo
import me.ezzattharwat.breakingbad.util.Errors.NETWORK_ERROR
import me.ezzattharwat.breakingbad.util.Resource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TestDataRepository @Inject constructor() : CharactersRepo {

    override suspend fun requestCharacters(): Resource<List<CharactersResponseItem>> {

        return when(dataStatus) {
            DataStatus.Success -> {
              Resource.success(initData())
            }
            DataStatus.Fail -> {
                Resource.error(NETWORK_ERROR,null)
            }
            DataStatus.EmptyResponse -> {
                Resource.success(CharactersResponse()) }
            }
        }


}
