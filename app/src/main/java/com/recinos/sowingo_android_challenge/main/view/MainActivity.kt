package com.recinos.sowingo_android_challenge.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.recinos.sowingo_android_challenge.R
import com.recinos.sowingo_android_challenge.main.model.ui.ProductCardViewModel
import com.recinos.sowingo_android_challenge.main.utils.Event
import com.recinos.sowingo_android_challenge.main.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()

    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var productsAdapter: ProductsAdapter
    lateinit var swipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchProducts()

        viewModel.productsLiveData.observe(this) {
            handleProductEvent(it)
        }

        toolbar = findViewById(R.id.main_toolbar)
        recyclerView = findViewById(R.id.products_recycler_view)
        swipeContainer = findViewById(R.id.products_swipe_container)
        swipeContainer.setOnRefreshListener {
            viewModel.fetchProducts()
        }
        setupRecyclerView()
        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayShowTitleEnabled(true);
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        productsAdapter = ProductsAdapter(this, listOf(), object : OnClickListener {
            override fun onClick(product: ProductCardViewModel, i: Int) {
                viewModel.updateFavorite(product, i)
            }

        })
        recyclerView.adapter = productsAdapter
    }

    private fun updateProductsList(list: List<ProductCardViewModel>) {
        productsAdapter.setElements(list)
    }

    private fun handleProductEvent(event: Event<MainViewActions>) {
        event.peek().let { action ->
            when (action) {
                is ShowLoading -> {
                    Log.d("MainActivity", "Show Loading")
                    swipeContainer.isRefreshing = true
                }
                is ShowError -> {
                    Log.d("MainActivity", "Show Error")
                    swipeContainer.isRefreshing = false
                }
                is ShowProducts -> {
                    updateProductsList(action.data)
                    swipeContainer.isRefreshing = false
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