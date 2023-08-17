package com.velogm.presentation.ui.home

import android.os.Bundle
import android.view.View
import com.velogm.core_ui.base.BindingFragment
import com.velogm.presentation.R
import com.velogm.presentation.databinding.ItemFragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenHomeSlidePageFragment :
    BindingFragment<ItemFragmentHomeBinding>(R.layout.item_fragment_home_) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getString("data")
        binding.test.text = data ?: "Default Text"
    }

    companion object {
        fun newInstance(data: String): ScreenHomeSlidePageFragment {
            val fragment = ScreenHomeSlidePageFragment()
            val args = Bundle()
            args.putString("data", data)
            fragment.arguments = args
            return fragment
        }
    }
}

