package me.ezzattharwat.breakingbad.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.data.repository.CharactersRepoImp
import me.ezzattharwat.breakingbad.util.Errors.NETWORK_ERROR
import me.ezzattharwat.breakingbad.util.MainCoroutineRule
import me.ezzattharwat.breakingbad.util.Resource
import me.ezzattharwat.breakingbad.util.TestModelsGenerator
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {


    // Subject under test
    private lateinit var mainViewModel: MainViewModel

    // Use a fake UseCase to be injected into the viewModel
    private val charactersRepoImp: CharactersRepoImp = mockk()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()


    @Test
    fun `get characters List`() {

        val characterModel = testModelsGenerator.generateCharacters()

        //1- Mock calls
        coEvery { charactersRepoImp.requestCharacters() } returns Resource.success(characterModel)


        //2-Call
        mainViewModel = MainViewModel(charactersRepoImp)
        mainViewModel.getCharactersList()

        //active observer for livedata
        mainViewModel.charactersLiveData.observeForever { }

        //3-verify
        val isEmptyList = mainViewModel.charactersLiveData.value?.data?.isNullOrEmpty()
        assertEquals(characterModel, mainViewModel.charactersLiveData.value?.data)
        assertEquals(false,isEmptyList)
    }

    @Test
    fun `get Empty Character List`(){
        val characterModel = testModelsGenerator.generateCharactersModelWithEmptyList()

        coEvery { charactersRepoImp.requestCharacters() } returns Resource.success(characterModel)

        //2-Call
        mainViewModel = MainViewModel(charactersRepoImp)
        mainViewModel.getCharactersList()

        //active observer for livedata
        mainViewModel.charactersLiveData.observeForever { }

        //3-verify
        val isEmptyList = mainViewModel.charactersLiveData.value?.data?.isNullOrEmpty()
        assertEquals(characterModel, mainViewModel.charactersLiveData.value?.data)
        assertEquals(true,isEmptyList)
    }

    @Test
    fun `get Character Error`(){
        val error: Resource<List<CharactersResponseItem>> = Resource.error(NETWORK_ERROR, null)

        //1- Mock calls
        coEvery { charactersRepoImp.requestCharacters() } returns error

        //2-Call
        mainViewModel = MainViewModel(charactersRepoImp)
        mainViewModel.getCharactersList()

        //active observer for livedata
        mainViewModel.charactersLiveData.observeForever { }

        //3-verify
        assertEquals(NETWORK_ERROR, mainViewModel.charactersLiveData.value?.message)
    }

}