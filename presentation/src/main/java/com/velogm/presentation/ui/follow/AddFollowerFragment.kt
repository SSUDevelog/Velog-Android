package com.velogm.presentation.ui.follow

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.velogm.core_ui.base.BindingFragment
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentAddFollowerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFollowerFragment :
    BindingFragment<FragmentAddFollowerBinding>(R.layout.fragment_add_follower) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initClickEventListeners()

    }

    private fun initClickEventListeners() {
        with(binding) {
            ivAddFollowerBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }


}


