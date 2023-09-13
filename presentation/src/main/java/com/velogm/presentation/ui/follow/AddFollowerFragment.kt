package com.velogm.presentation.ui.follow

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
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
        addFolower()
        colloectInputResult()
        collectEventData()
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
        binding.etAddFollowerSearch.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                viewModel.getInputFollower(binding.etAddFollowerSearch.text.toString())
                binding.etAddFollowerSearch.text?.clear()
                true
            } else {
                false
            }
        }
    }

    private fun addFolower() {
        binding.tvAddFolllowerLabel.setOnClickListener {
            viewModel.addFollower(binding.tvAddFollowerName.text.toString())
        }
    }

    private fun collectEventData() {
        viewModel.eventData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    Timber.d("follow success")
                }

                else -> {}
            }
        }
    }
}


