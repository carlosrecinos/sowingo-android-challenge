package com.recinos.sowingo_android_challenge.main.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recinos.sowingo_android_challenge.main.ApiResponse
import com.recinos.sowingo_android_challenge.main.model.Product
import com.recinos.sowingo_android_challenge.main.model.responses.ProductsResponse
import com.recinos.sowingo_android_challenge.main.repository.ProductsRepository
import com.recinos.sowingo_android_challenge.main.view.MainViewActions
import com.recinos.sowingo_android_challenge.main.utils.Event
import com.recinos.sowingo_android_challenge.main.utils.isOnline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface IMainViewModel {
    fun fetchProducts()

    fun performSearch(searchText: String?)
}

class MainViewModel(private val context: Context): ViewModel(), KoinComponent, IMainViewModel {
    private val repository: ProductsRepository by inject()
    var productsList: List<Product> = listOf()

    private val _productsResponse = MutableLiveData<Event<MainViewActions>>()
    val productsResponse: LiveData<Event<MainViewActions>> = _productsResponse

    override fun fetchProducts() {
        _productsResponse.postValue(Event(MainViewActions.ShowLoading))
        if(isOnline(context)) {
            try{
                GlobalScope.launch(Dispatchers.IO) {
                    val response = repository.getProducts()
                    _productsResponse.postValue(handleFetchedProducts(response))
                }
            }
            catch(e: Exception){
                e.message?.let {
                    _productsResponse.postValue(Event(MainViewActions.ShowError(it)))
                }
            }

        }
        else{
            _productsResponse.postValue(Event(MainViewActions.ShowError("No Internet connection")))
        }
    }

    override fun performSearch(text: String?) {
        for (product in productsList) {
            for (prop in Product::class.members) {
                println("${prop.name} = ")
            }
        }
    }

    private fun handleFetchedProducts(response: ApiResponse<ProductsResponse>): Event<MainViewActions> {
        when (response) {
            is ApiResponse.Failure -> {
                return Event(MainViewActions.ShowError("response.message()"))
            }
            is ApiResponse.Success -> {
                response.data?.let {
                    productsList = it.hits
                    return Event(MainViewActions.ShowProducts(data = it.hits))
                }
                return Event(MainViewActions.ShowError("No products found..."))
            }
        }
    }
}