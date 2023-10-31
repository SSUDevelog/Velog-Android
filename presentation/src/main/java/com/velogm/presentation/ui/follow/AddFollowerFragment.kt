package com.velogm.presentation.ui.follow

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.velogm.core_ui.base.BindingFragment
import com.velogm.core_ui.context.hideKeyboard
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentAddFollowerBinding
import com.velogm.presentation.util.Follow.FOLLOWER_LIST
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AddFollowerFragment :
    BindingFragment<FragmentAddFollowerBinding>(R.layout.fragment_add_follower) {

    private val addFollowerViewModel by viewModels<AddFollowerViewModel>()
    private val followViewModel by viewModels<FollowViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initClickEventListeners()
        initEditText()
        addFollower()
        removeText()
        collectInputResult()
        collectEventData()
        collectDeleteFollower()
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
                addFollowerViewModel.getInputFollower(binding.etAddFollowerSearch.text.toString())
                binding.etAddFollowerSearch.text?.clear()
                requireActivity().hideKeyboard(binding.root)
                true
            } else {
                false
            }
        }
    }

    private fun addFollower() {
        binding.tvAddFollowerLabel.setOnClickListener {
            if (binding.tvAddFollowerLabel.text == getString(R.string.tv_add_follower_button)) {
                addFollowerViewModel.addFollower(binding.tvAddFollowerName.text.toString())
            } else {
                val dialog = DeleteFollowerDialogFragment(deleteFollower = {
                    followViewModel.deleteFollower(binding.tvAddFollowerName.text.toString())
                })
                dialog.show(childFragmentManager, "delete")
            }
        }
    }

    private fun removeText() {
        with(binding) {
            etAddFollowerSearch.doAfterTextChanged { text ->
                if (text?.length == 0) {
                    ivAddFollowerCancel.visibility = View.INVISIBLE
                } else {
                    ivAddFollowerCancel.visibility = View.VISIBLE
                    ivAddFollowerCancel.setOnClickListener { etAddFollowerSearch.text.clear() }
                }
            }
        }
    }

    private fun collectInputResult() {
        addFollowerViewModel.getInputFollower.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Loading -> Unit

                is UiState.Success -> {
                    with(binding) {
                        addFollower = it.data
                        val list: ArrayList<String>? =
                            requireArguments().getStringArrayList(FOLLOWER_LIST)

                        layoutAddFollower.visibility =
                            if (it.data.validate) View.VISIBLE else View.GONE

                        if (list != null) {
                            val isFollowing = it.data.userName in list
                            binding.tvAddFollowerLabel.text =
                                if (isFollowing) getString(R.string.tv_item_rv_follow_cancel) else getString(
                                    R.string.tv_add_follower_button
                                )
                        }

                        layoutAddFollowerEmpty.visibility =
                            if (!it.data.validate) View.VISIBLE else View.GONE
                    }
                }

                else -> binding.layoutAddFollowerEmpty.visibility = View.VISIBLE
            }
        }.launchIn(lifecycleScope)
    }

    private fun collectEventData() {
        addFollowerViewModel.eventData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> binding.tvAddFollowerLabel.text =
                    getString(R.string.tv_item_rv_follow_cancel)

                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun collectDeleteFollower() {
        followViewModel.deleteFollower.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> binding.tvAddFollowerLabel.text =
                    getString(R.string.tv_add_follower_button)

                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }
}


