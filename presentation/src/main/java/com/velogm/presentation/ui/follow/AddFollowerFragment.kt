package com.velogm.presentation.ui.follow

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.velogm.core_ui.base.BindingFragment
import com.velogm.core_ui.fragment.toast
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentAddFollowerBinding
import com.velogm.presentation.util.Debouncer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class AddFollowerFragment :
    BindingFragment<FragmentAddFollowerBinding>(R.layout.fragment_add_follower) {

    private val viewModel by viewModels<AddFollowerViewModel>()
    private val searchDebouncer = Debouncer<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initClickEventListeners()
        initEditText()
        colloectInputResult()
    }

    private fun colloectInputResult() {
        viewModel.getInputFollower.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> {
                    toast("Loading")
                }

                is UiState.Success -> {
                    with(binding) {
                        addFollower = it.data
                        layoutAddFollower.visibility =
                            if (it.data.validate) View.VISIBLE else View.GONE
                        layoutAddFollowerEmpty.visibility =
                            if (!it.data.validate) View.VISIBLE else View.GONE
                    }
                }

                else -> {
                    binding.layoutAddFollowerEmpty.visibility = View.VISIBLE
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun initClickEventListeners() {
        with(binding) {
            ivAddFollowerBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun initEditText() {
        Timber.d("검색 editText 설정")
        binding.etAddFollowerSearch.doAfterTextChanged { text ->
            Timber.d("검색 editText 텍스트 수정됨 / 키워드 = ${binding.etAddFollowerSearch.text}")
            val keyword: String? =
                if (binding.etAddFollowerSearch.text.toString() != "") binding.etAddFollowerSearch.text.toString() else null
            viewModel.getInputFollower(keyword)
            searchDebouncer.setDelay(text.toString(), 1000L) {}
        }
    }
}


