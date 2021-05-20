package me.ezzattharwat.breakingbad.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import me.ezzattharwat.breakingbad.R
import me.ezzattharwat.breakingbad.domain.CharactersResponseItem
import me.ezzattharwat.breakingbad.util.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    @Inject
    lateinit var charactersAdapter: CharactersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeViewModel()
        setupRecyclerView()

        mainViewModel.getCharactersList()
    }


    private fun setupRecyclerView(){

        val layoutManager = GridLayoutManager(this, 2)
        charactersRV.layoutManager = layoutManager
        charactersRV.setHasFixedSize(true)
        charactersRV.adapter = charactersAdapter

        charactersRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    mainViewModel.fetchMoreCharacters(
                            visibleItemCount = layoutManager.childCount,
                            lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition(),
                            totalItemCount = layoutManager.itemCount)
                }
            }
        })

    }

    private fun observeOnCharactersList(resource : me.ezzattharwat.breakingbad.core_utils.util.Resource<List<CharactersResponseItem>>){

        when (resource.status) {
            me.ezzattharwat.breakingbad.core_utils.util.Status.LOADING -> {
                charactersRV.adapter?.let {
                    if (it.itemCount == 0 ) {
                        charactersPB.toVisible()
                    }
                }
            }
            me.ezzattharwat.breakingbad.core_utils.util.Status.SUCCESS -> {
                charactersPB.toGone()
                resource.data?.let {
                    if (it.isNotEmpty()) {
                        charactersRV.toVisible()
                        charactersAdapter.submitList(it)
                    }else {
                        emptyTV.toVisible()
                        charactersRV.toGone()
                    }
                }

            }
            me.ezzattharwat.breakingbad.core_utils.util.Status.ERROR -> {
                charactersPB.toGone()
                charactersRV.toGone()
                resource.message?.let {
                    homeRoot.showSnackbar(
                        it,
                        Snackbar.LENGTH_INDEFINITE) {
                        mainViewModel.getCharactersList()
                    }
                }


            }
        }
    }

    private fun observeViewModel() {
        observe(mainViewModel.charactersLiveData, ::observeOnCharactersList)
    }
}
