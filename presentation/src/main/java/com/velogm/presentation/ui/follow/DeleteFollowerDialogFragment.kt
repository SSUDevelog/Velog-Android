package com.velogm.presentation.ui.follow

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.velogm.core_ui.base.BindingDialogFragment
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentDeleteFollowDialogBinding

class DeleteFollowerDialogFragment(
    private val deleteFollower: () -> Unit = { -> }
) :
    BindingDialogFragment<FragmentDeleteFollowDialogBinding>(R.layout.fragment_delete_follow_dialog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCancelButtonClick()
        initDeleteButtonClick(deleteFollower)
    }


    private fun initCancelButtonClick() {
        binding.btnDialogFollowNo.setOnClickListener {
            dismiss()
        }
    }

    private fun initDeleteButtonClick(deleteFollower: () -> Unit = { -> }) {
        binding.btnDialogFollowYes.setOnClickListener {
            deleteFollower.invoke()
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}

