package com.recinos.sowingo_android_challenge.main.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recinos.sowingo_android_challenge.main.ApiResponse
import com.recinos.sowingo_android_challenge.main.model.Product
import com.recinos.sowingo_android_challenge.main.model.responses.ProductsResponse
import com.recinos.sowingo_android_challenge.main.model.ui.ProductCardViewModel
import com.recinos.sowingo_android_challenge.main.repository.ProductsRepository
import com.recinos.sowingo_android_challenge.main.utils.*
import com.recinos.sowingo_android_challenge.main.view.MainViewActions
import com.recinos.sowingo_android_challenge.main.view.ShowError
import com.recinos.sowingo_android_challenge.main.view.ShowLoading
import com.recinos.sowingo_android_challenge.main.view.ShowProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface IMainViewModel {
    fun fetchProducts()
    fun updateFavorite(product: ProductCardViewModel, position: Int)
    fun performSearch(searchText: String?)
}

class MainViewModel(private val context: Context) : ViewModel(), KoinComponent, IMainViewModel {
    private val repository: ProductsRepository by inject()
    private var productsList: MutableList<Product> = mutableListOf()

    private val _productsLiveData = MutableLiveData<Event<MainViewActions>>()
    val productsLiveData: LiveData<Event<MainViewActions>> = _productsLiveData

    override fun fetchProducts() {
        _productsLiveData.postValue(Event(ShowLoading))
        if (isOnline(context)) {
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    val response = repository.getProducts()
                    _productsLiveData.postValue(handleFetchedProducts(response))
                }
            } catch (e: Exception) {
                e.message?.let {
                    _productsLiveData.postValue(Event(ShowError(it)))
                }
            }

        } else {
            _productsLiveData.postValue(Event(ShowError("No Internet connection")))
        }
    }

    override fun updateFavorite(product: ProductCardViewModel, position: Int) {
        _productsLiveData.postValue(Event(ShowLoading))
        if (isOnline(context)) {
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    val response = if (product.isFavorite == true) repository.deleteFavorite() else repository.setFavorite()
                    if (response.data?.favorite != null) {
                        val isFavorite = response.data.favorite
                        productsList = productsList.map {
                            if (it.id == product.id) {
                                it.isFavouriteProduct = isFavorite
                            }
                            it
                        }.toMutableList()
                        updateProductsLiveData(productsList)
                    }
                }
            } catch (e: Exception) {
                e.message?.let {
                    Log.e("MainViewModel", e.message ?: "Error adding favorite")
                }
            }

        } else {
            Log.e("MainViewModel", "No internet connection")
        }
    }

    private fun updateProductsLiveData(list: List<Product>) {
        val event = Event(ShowProducts(convertToViewModelProducts(list)))
        _productsLiveData.postValue(event)
    }

    override fun performSearch(searchText: String?) {
        //O(n*m) time, o(n) space complexity
        val properties = Product::class.members.map { it.name }.toSet()

        val searchResults = mutableMapOf<String, Int>()
        val withOcurrencesProduct = mutableListOf<Product>()

        for (product in productsList) {
            var matchesCount: Int
            for (prop in properties) {
                if (properties.contains(prop)) {
                    readStringProperty(product, prop)?.let {
                        matchesCount =
                            countSubstringMatches(it.lowercase(), searchText?.lowercase() ?: "")
                        if (matchesCount > 0) {
                            searchResults[product.id] = matchesCount
                        }
                    }
                }
            }

        }

        val sortedResults = searchResults.toList().sortedBy { (_, value) -> value }
        val productsMap = productsList.associateBy { it.id }

        for (sortedResult in sortedResults) {
            productsMap[sortedResult.first]?.let { withOcurrencesProduct.add(it) }
        }
        val filteredProductsList = convertToViewModelProducts(withOcurrencesProduct.toList())
        println("Filtered: ${filteredProductsList.size}")
        _productsLiveData.postValue(Event(ShowProducts(filteredProductsList)))
    }

    private fun convertToViewModelProducts(list: List<Product>): List<ProductCardViewModel> {
        return list.map {
            val listPrice = it.vendorInventory.first().listPrice
            val price = it.vendorInventory.first().price
            val badgeImageUrl = it.advertisingBadges?.badges?.firstOrNull()?.badgeImageUrl
            ProductCardViewModel(it.id, it.mainImage, it.name, price, listPrice, it.isFavouriteProduct, badgeImageUrl)
        }
    }

    private fun handleFetchedProducts(response: ApiResponse<ProductsResponse>): Event<MainViewActions> {
        when (response) {
            is ApiResponse.Failure -> {
                return Event(ShowError("response.message()"))
            }
            is ApiResponse.Success -> {
                response.data?.let {
                    productsList = it.hits
                    return Event(ShowProducts(data = convertToViewModelProducts(it.hits)))
                }
                return Event(ShowError("No products found..."))
            }
        }
    }
}