package com.example.assignmenttavant.ui.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.assignmenttavant.databinding.CharacterDetailFragmentBinding
import com.example.assignmenttavant.model.ProductModel
import com.example.assignmenttavant.utility.autoCleared
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var binding: CharacterDetailFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<ProductModel.ProductModelItem>("obj")?.let { bindCharacter(it) }

    }

    private fun bindCharacter(character: ProductModel.ProductModelItem) {

        binding.name.text = character.title
        binding.species.text = character.description
        binding.status.text = character.title
        binding.gender.text = character.title
        Glide.with(binding.root)
            .load(character.image)
            .transform(CircleCrop())
            .into(binding.imageP)
    }
}
