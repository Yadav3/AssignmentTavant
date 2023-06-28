package com.example.assignmenttavant.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmenttavant.R
import com.example.assignmenttavant.databinding.ProductFragmentBinding
import com.example.assignmenttavant.model.ProductModel
import com.example.assignmenttavant.network.ResultData
import com.example.assignmenttavant.ui.main.adapter.ProductListAdapter
import com.example.assignmenttavant.ui.main.viewmodel.ProductViewModel
import com.example.assignmenttavant.utility.autoCleared

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() , ProductListAdapter.CharacterItemListener{

    private var binding: ProductFragmentBinding by autoCleared()


    private val viewModel: ProductViewModel by viewModels()
    private lateinit var adapter: ProductListAdapter

    private val repositoryObserver = Observer<ResultData<ProductModel?>> { resultData ->
        when(resultData) {
            is ResultData.Loading -> {
                //  progress_bar.show()
                binding.progressBar.visibility = View.VISIBLE
            }
            is ResultData.Success -> {
//                progressIndicator.hide()
                binding.progressBar.visibility = View.GONE
                val repositoriesModel = resultData.data
                adapter.submitList(repositoriesModel)
            }
            is ResultData.Failed -> {
//                progressIndicator.hide()
                binding.progressBar.visibility = View.GONE
            }
            is ResultData.Exception -> {
//                progressIndicator.hide()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

//        swipe_refresh.setOnRefreshListener(this)

       /* binding.testClick.setOnClickListener(View.OnClickListener {
            findNavController().navigate(
                R.id.action_charactersFragment_to_characterDetailFragment,
                bundleOf("id" to 3)//characterId
            )
        })*/

        getDataAndSubscribeEvents()
    }

    private fun getDataAndSubscribeEvents() {
        val repositoriesListLiveData = viewModel.getRepositoriesProductList(since = "20")
        repositoriesListLiveData.observe(viewLifecycleOwner, repositoryObserver)
    }


    override fun onClickedCharacter(characterId: ProductModel.ProductModelItem) {
        val bundle = Bundle()
        bundle.putParcelable("obj", characterId)
        findNavController().navigate(
            R.id.action_charactersFragment_to_characterDetailFragment,
            bundle
        )
    }

    private fun setupRecyclerView() {
        adapter = ProductListAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }
}
