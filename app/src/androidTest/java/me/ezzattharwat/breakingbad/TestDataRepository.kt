package me.ezzattharwat.breakingbad


import me.ezzattharwat.breakingbad.TestUtil.dataStatus
import me.ezzattharwat.breakingbad.TestUtil.initData
import me.ezzattharwat.breakingbad.domain.CharactersResponseItem
import me.ezzattharwat.breakingbad.core_utils.util.Errors.NETWORK_ERROR
import me.ezzattharwat.breakingbad.core_utils.util.Resource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TestDataRepository @Inject constructor() :
    me.ezzattharwat.breakingbad.data.repository.CharactersRepo {

    override suspend fun requestCharacters(): me.ezzattharwat.breakingbad.core_utils.util.Resource<List<CharactersResponseItem>> {

        return when(dataStatus) {
            DataStatus.Success -> {
              me.ezzattharwat.breakingbad.core_utils.util.Resource.success(initData())
            }
            DataStatus.Fail -> {
                me.ezzattharwat.breakingbad.core_utils.util.Resource.error(NETWORK_ERROR,null)
            }
            DataStatus.EmptyResponse -> {
                me.ezzattharwat.breakingbad.core_utils.util.Resource.success(me.ezzattharwat.breakingbad.domain.CharactersResponse()) }
            }
        }


}
