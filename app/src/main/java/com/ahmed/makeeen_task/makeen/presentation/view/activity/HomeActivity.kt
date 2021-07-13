package com.ahmed.makeeen_task.makeen.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmed.makeeen_task.R
import com.ahmed.makeeen_task.makeen.data.model.response.ProductItem
import com.ahmed.makeeen_task.makeen.presentation.view.adapter.FooterLoadingAdapter
import com.ahmed.makeeen_task.makeen.presentation.view.adapter.HomeItemsAdapter
import com.ahmed.makeeen_task.makeen.presentation.viewmodel.MakeenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    val TAG = "Looog"

    private lateinit var adapter: HomeItemsAdapter
    private lateinit var concatAdapter: ConcatAdapter
    private var footerAdapter = FooterLoadingAdapter()

    @Inject
    lateinit var viewModel : MakeenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        observeScreenState()
        initRecyclerView()

        viewModel.getMakeenItems()


    }


    private fun onScrollToEnd(offSet: Int) {
        //load next page
        viewModel.getMakeenItems(offSet)
    }

    private fun initRecyclerView() {
        adapter = HomeItemsAdapter(ArrayList() , ::onScrollToEnd)
        concatAdapter = ConcatAdapter(adapter, footerAdapter)
        rvForecast.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
        rvForecast.adapter = concatAdapter
    }

    private fun observeScreenState() {
        viewModel.screenState.observe(this , Observer {
            onScreenStateChanged(it)
        })
    }

    private fun onScreenStateChanged(state: MakeenViewModel.ScreenState?) {
        when (state) {
            is MakeenViewModel.ScreenState.Loading -> footerAdapter.setLoading()
            is MakeenViewModel.ScreenState.SuccessAPIResponse -> handleSuccess(state.data)
            is MakeenViewModel.ScreenState.ErrorLoadingFromApi -> handleErrorLoadingFromApi(state.error)
        }
    }

    private fun handleErrorLoadingFromApi(error: Throwable) {
        footerAdapter.showError{
            viewModel.getMakeenItems(adapter.data.size)
        }
    }

    private fun handleSuccess(data: List<ProductItem>) {
        footerAdapter.setLoading(false)
        adapter.setList(data)
    }


}