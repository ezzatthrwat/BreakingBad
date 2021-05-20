package me.ezzattharwat.breakingbad.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.ezzattharwat.breakingbad.NetworkConnection
import me.ezzattharwat.breakingbad.core_utils.util.Errors
import me.ezzattharwat.breakingbad.domain.CharactersResponseItem
import me.ezzattharwat.breakingbad.core_utils.util.Resource
import me.ezzattharwat.breakingbad.data.repository.CharactersRepo
import me.ezzattharwat.breakingbad.util.wrapEspressoIdlingResource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val charactersRepo: CharactersRepo) : ViewModel() {

    @Inject
    lateinit var networkConnection: NetworkConnection

    private val _charactersMutableLiveData = MutableLiveData<Resource<List<CharactersResponseItem>>>()
    val charactersLiveData: LiveData<Resource<List<CharactersResponseItem>>> = _charactersMutableLiveData


    fun getCharactersList(){
        if (!networkConnection.checkNetwork()) {
            _charactersMutableLiveData.value = Resource.error(Errors.NO_INTERNET_CONNECTION, null)
        } else {
            _charactersMutableLiveData.value = Resource.loading(null)
            viewModelScope.launch {
                wrapEspressoIdlingResource {
                    val resource = charactersRepo.requestCharacters()
                    _charactersMutableLiveData.value = resource
                }
            }
        }
    }

    fun fetchMoreCharacters(visibleItemCount: Int,
                            lastVisibleItemPosition: Int,
                            totalItemCount: Int) {

        if ((visibleItemCount + lastVisibleItemPosition)  >= totalItemCount && lastVisibleItemPosition >= 0 ) {
            viewModelScope.launch {
                val resource = charactersRepo.requestCharacters()
                    _charactersMutableLiveData.value = resource
            }
        }
    }
}