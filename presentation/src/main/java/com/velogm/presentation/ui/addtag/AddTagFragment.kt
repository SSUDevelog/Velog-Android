package com.velogm.presentation.ui.addtag

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.velogm.core_ui.base.BindingFragment
import com.velogm.core_ui.fragment.toast
import com.velogm.core_ui.view.UiState
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentAddTagBinding
import com.velogm.presentation.model.TagModel
import com.velogm.presentation.ui.addtag.adapter.AddTagAdapter
import com.velogm.presentation.ui.addtag.adapter.PopularTagAdapter
import com.velogm.presentation.ui.addtag.dialog.DeleteDialogFragment
import com.velogm.presentation.ui.signin.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AddTagFragment : BindingFragment<FragmentAddTagBinding>(R.layout.fragment_add_tag) {

    private lateinit var myTagAdapter: AddTagAdapter
    private lateinit var popularTagAdapter: PopularTagAdapter
    private val viewModel by viewModels<AddTagViewModel>()
    private val parentViewModel by activityViewModels<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation()
        setAdapter()
        collectMyTagListData()
        collectPopularTagListData()
        addTag()
        collectEventData()
        //QA임시 코드
        popularTagAdapter.submitList(
            listOf<TagModel>
                (
                TagModel("알고리즘"),
                TagModel("JavaScript"),
                TagModel("TIL"),
                TagModel("Java"),
                TagModel("React"),
                TagModel("프로그래머스"),
                TagModel("코딩테스트"),
                TagModel("Spring"),
                TagModel("CSS")
            )
        )
    }

    private fun setNavigation() {
        binding.toolbarAddTag.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setAdapter() {
        myTagAdapter = AddTagAdapter(deleteTagClick = {
            val dialog = DeleteDialogFragment(
                deleteTag = {
                    viewModel.deleteTag(it.tag)
                }
            )
            dialog.show(childFragmentManager, "delete")
        })
        binding.rvAddTagList.adapter = myTagAdapter
        popularTagAdapter = PopularTagAdapter(tagClick = {
            viewModel.addTag(it.tag)
        })
        binding.rvAddTagPopularList.adapter = popularTagAdapter
    }


    private fun collectMyTagListData() {
        parentViewModel.tagListData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    myTagAdapter.submitList(it.data)
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun collectPopularTagListData() {
        viewModel.tagPopularListData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    popularTagAdapter.submitList(it.data)
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun addTag() {
        binding.etvAddTag.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                viewModel.addTag(binding.etvAddTag.text.toString())
                binding.etvAddTag.text?.clear()
                true
            } else {
                false
            }
        }
    }

    private fun collectEventData() {
        viewModel.eventData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    toast("태그가 추가 되었습니다.")
                    parentViewModel.getTag()
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

}
