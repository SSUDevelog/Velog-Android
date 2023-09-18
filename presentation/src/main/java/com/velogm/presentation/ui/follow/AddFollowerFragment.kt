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
import com.velogm.presentation.util.Follow.FOLLOWER_LIST
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class AddFollowerFragment :
    BindingFragment<FragmentAddFollowerBinding>(R.layout.fragment_add_follower) {

    private val viewModel by viewModels<AddFollowerViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initClickEventListeners()
        initEditText()
        addFollower()
        collectInputResult()
        collectEventData()
    }

    private fun collectInputResult() {
        viewModel.getInputFollower.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> {
                    toast("Loading")
                }

                is UiState.Success -> {
                    with(binding) {
                        addFollower = it.data
                        val list: ArrayList<String>? =
                            requireArguments().getStringArrayList(FOLLOWER_LIST)

                        layoutAddFollower.visibility =
                            if (it.data.validate) View.VISIBLE else View.GONE

                        if (list != null) {
                            val isFollowing = it.data.userName in list
                            binding.tvAddFollowerLabel.text = if (isFollowing) "팔로우 취소" else "팔로우"
                        }

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
            if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                viewModel.getInputFollower(binding.etAddFollowerSearch.text.toString())
                binding.etAddFollowerSearch.text?.clear()
                true
            } else {
                false
            }
        }
    }

    private fun addFollower() {
        binding.tvAddFollowerLabel.setOnClickListener {
            viewModel.addFollower(binding.tvAddFollowerName.text.toString())
        }
    }

    private fun collectEventData() {
        viewModel.eventData.observe(viewLifecycleOwner) {
            binding.tvAddFollowerLabel.text = "팔로우 취소"
        }
    }
}


