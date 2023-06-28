package com.example.assignmenttavant.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.assignmenttavant.R
import com.example.assignmenttavant.databinding.ActivityMainBinding
import com.example.assignmenttavant.model.ProductModel
import com.example.assignmenttavant.network.ResultData
import com.example.assignmenttavant.ui.main.adapter.ProductAdapter
import com.example.assignmenttavant.ui.main.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "PeopleActivity"
@AndroidEntryPoint
class ProductActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private val mainViewModel: ProductViewModel by viewModels()
    private lateinit var repositoriesAdapter: ProductAdapter
    private lateinit var binding: ActivityMainBinding
    private val repositoryObserver = Observer<ResultData<ProductModel?>> { resultData ->
        when(resultData) {
            is ResultData.Loading -> {
                binding.progressIndicator.show()
            }
            is ResultData.Success -> {
                binding.progressIndicator.hide()
                val repositoriesModel = resultData.data
                repositoriesAdapter.submitList(repositoriesModel)
            }
            is ResultData.Failed -> {
                binding.progressIndicator.hide()
            }
            is ResultData.Exception -> {
                binding.progressIndicator.hide()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        repositoriesAdapter = ProductAdapter()
        binding.recycler_view_repositories.adapter = repositoriesAdapter

        binding.swipe_refresh.setOnRefreshListener(this)

        getDataAndSubscribeEvents()
    }

    private fun getDataAndSubscribeEvents() {
        val repositoriesListLiveData = mainViewModel.getRepositoriesProductList(since = "20")
        repositoriesListLiveData.observe(this, repositoryObserver)
    }

    override fun onRefresh() {
        binding.swipe_refresh.isRefreshing = false
        //  getDataAndSubscribeEvents()
    }
}