package com.velogm.presentation.ui.addtag

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.velogm.core_ui.base.BindingFragment
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentAddTagBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTagFragment : BindingFragment<FragmentAddTagBinding>(R.layout.fragment_add_tag) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation()
    }

    private fun setNavigation() {
        binding.toolbarAddTag.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}