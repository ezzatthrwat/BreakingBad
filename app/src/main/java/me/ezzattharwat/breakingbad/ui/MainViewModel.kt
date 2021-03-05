package me.ezzattharwat.breakingbad.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.data.repository.AppRepository
import me.ezzattharwat.breakingbad.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {


    private val _charactersMutableLiveData = MutableLiveData<Resource<List<CharactersResponseItem>>>()
    var charactersLiveData: LiveData<Resource<List<CharactersResponseItem>>> = _charactersMutableLiveData


    fun getCharactersList(){

        _charactersMutableLiveData.value = Resource.loading(null)
        viewModelScope.launch {
            val resource = appRepository.fetchCharactersFromRemoteSource().second
            _charactersMutableLiveData.value =  resource
        }
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if ((visibleItemCount + lastVisibleItemPosition) >= totalItemCount) {
            viewModelScope.launch {
                _charactersMutableLiveData.value = appRepository.requestMore()
                }
        }
    }
}