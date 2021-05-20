package me.ezzattharwat.breakingbad.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.ezzattharwat.breakingbad.NetworkConnection
import me.ezzattharwat.breakingbad.domain.CharactersResponseItem
import me.ezzattharwat.breakingbad.core_utils.util.Errors.NETWORK_ERROR
import me.ezzattharwat.breakingbad.util.MainCoroutineRule
import me.ezzattharwat.breakingbad.core_utils.util.Resource
import me.ezzattharwat.breakingbad.data.repository.CharactersRepo
import me.ezzattharwat.breakingbad.util.TestModelsGenerator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {


    // Subject under test
    private lateinit var mainViewModel: MainViewModel

    // Use a fake UseCase to be injected into the viewModel
    private val charactersRepo: CharactersRepo = mockk()


    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    @Before
    fun setup(){
        mainViewModel = MainViewModel(charactersRepo)
        mainViewModel.charactersLiveData.observeForever { }
    }

    @Test
    fun `get characters List`() {

        // arrange
        val characterModel = testModelsGenerator.generateCharacters()
        coEvery{ charactersRepo.requestCharacters() } returns me.ezzattharwat.breakingbad.core_utils.util.Resource.success(characterModel)

        // act
        mainViewModel.getCharactersList()

        // assert
        val isEmptyList = mainViewModel.charactersLiveData.value?.data?.isNullOrEmpty()
        assertEquals(characterModel, mainViewModel.charactersLiveData.value?.data)
        assertEquals(false,isEmptyList)
    }

    @Test
    fun `get Empty Character List`(){

        // arrange
        val characterModel = testModelsGenerator.generateCharactersModelWithEmptyList()
        coEvery { charactersRepo.requestCharacters() } returns me.ezzattharwat.breakingbad.core_utils.util.Resource.success(characterModel)

        // act
        mainViewModel.getCharactersList()

        //assert
        val isEmptyList = mainViewModel.charactersLiveData.value?.data?.isNullOrEmpty()
        assertEquals(characterModel, mainViewModel.charactersLiveData.value?.data)
        assertEquals(true,isEmptyList)
    }

    @Test
    fun `get Character Error`(){

        // arrange
        val error: me.ezzattharwat.breakingbad.core_utils.util.Resource<List<CharactersResponseItem>> = me.ezzattharwat.breakingbad.core_utils.util.Resource.error(NETWORK_ERROR, null)
        coEvery { charactersRepo.requestCharacters() } returns error

        // act
        mainViewModel.getCharactersList()

        //assert
        assertEquals(NETWORK_ERROR, mainViewModel.charactersLiveData.value?.message)
    }

}