package me.ezzattharwat.breakingbad.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import me.ezzattharwat.breakingbad.R
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import me.ezzattharwat.breakingbad.utils.*
import timber.log.Timber
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
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
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                mainViewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
            }
        })

    }

    private fun observeOnCharactersList(resource: Resource<List<CharactersResponseItem>>){

        when (resource.status) {
            Status.LOADING -> {
                charactersPB.toVisible()
            }
            Status.PAGINATING_LOADING -> {
                charactersBottomPB.toVisible()
            }
            Status.SUCCESS -> {
                charactersPB.toGone()
                charactersBottomPB.toGone()
                resource.data?.let {
                    charactersAdapter.setData(it)
                }

            }
            Status.EMPTY -> {
                charactersPB.toGone()
                emptyTV.toVisible()
            }
            Status.ERROR -> {
                charactersPB.toGone()
                resource.message?.let { showToast(this, it) }
            }
        }
    }

    private fun observeViewModel() {
        observe(mainViewModel.charactersLiveData, ::observeOnCharactersList)
    }
}
