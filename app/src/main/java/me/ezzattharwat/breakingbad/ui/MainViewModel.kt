package me.ezzattharwat.breakingbad.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.data.repository.AppRepository
import me.ezzattharwat.breakingbad.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val _charactersMutableLiveData = MutableLiveData<Resource<List<CharactersResponseItem>>>()
    val charactersLiveData: LiveData<Resource<List<CharactersResponseItem>>> = _charactersMutableLiveData

    fun getCharactersList(rows: Int){

        _charactersMutableLiveData.value = Resource.loading(null)

        viewModelScope.launch {
         val charactersList =  appRepository.fetchCharactersFromRemoteSource(rows = rows)

            if (charactersList.data.isNullOrEmpty()){
                _charactersMutableLiveData.value = Resource.empty(null)
            } else {
                _charactersMutableLiveData.value = charactersList
            }
        }
    }
}