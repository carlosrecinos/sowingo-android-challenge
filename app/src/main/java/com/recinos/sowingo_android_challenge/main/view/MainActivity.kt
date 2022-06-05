package com.recinos.sowingo_android_challenge.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.recinos.sowingo_android_challenge.R
import com.recinos.sowingo_android_challenge.main.utils.Event
import com.recinos.sowingo_android_challenge.main.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchProducts()

        viewModel.productsResponse.observe(this) {
            handleProductEvent(it)
        }
    }

    private fun handleProductEvent(event: Event<MainViewActions>) {
        event.peek().let { action ->
            when (action) {
                is MainViewActions.ShowError -> {
                    Log.d("MainActivity", "Show Error")
                }
                is MainViewActions.ShowLoading -> {
                    Log.d("MainActivity", "Show Loading")
                }
                is MainViewActions.ShowProducts -> {
                    Log.d("MainActivity", "Show Data")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.products_menu, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView?
        searchView?.let {
            it.queryHint = getString(R.string.menu_search_text)
            it.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    viewModel.performSearch(p0)
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    viewModel.performSearch(p0)
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}