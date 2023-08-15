package com.velogm.presentation.ui.home

import android.os.Bundle
import android.view.View
import com.velogm.core_ui.base.BindingFragment
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("home")
    }

}