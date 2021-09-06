package com.app.telefric.makeen.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.telefric.makeen.data.model.response.MakeenResponse
import com.app.telefric.makeen.data.model.response.ProductItem
import com.app.telefric.makeen.domain.entity.MakeenParams
import com.app.telefric.makeen.domain.interactor.GetMakeenUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MakeenViewModel @Inject constructor(
    private val refreshForecastUseCase: GetMakeenUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val screenState by lazy { MutableLiveData<ScreenState>() }

    var offset: Int = -1
    fun getMakeenItems( offset: Int = 0) {
        if (this.offset == offset) return
        this.offset = offset

        screenState.value = ScreenState.Loading
        val params = MakeenParams(page = offset / MakeenParams.ITEMS_PER_PAGE)
        refreshForecastUseCase.fetchMakeen(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleSuccessResponse(it)
            }, {
                handleErrorResponse(it)
            }).addTo(compositeDisposable)
    }


    fun handleErrorResponse(it: Throwable) {
        offset = -1
        screenState.value = ScreenState.ErrorLoadingFromApi(it)
    }

    private fun handleSuccessResponse(it: MakeenResponse) {
        screenState.value = ScreenState.SuccessAPIResponse(it.responseData.dataList)
    }

    sealed class ScreenState {
        object Loading : ScreenState()
        class SuccessAPIResponse(val data: List<ProductItem>) : ScreenState()
        class ErrorLoadingFromApi(val error: Throwable) : ScreenState()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}