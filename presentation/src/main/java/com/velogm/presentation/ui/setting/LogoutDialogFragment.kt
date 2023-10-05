package com.velogm.presentation.ui.setting

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.velogm.core_ui.base.BindingDialogFragment
import com.velogm.core_ui.context.dialogFragmentResize
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentLogoutDialogBinding

class LogoutDialogFragment(
    private val logout: () -> Unit = { -> }
) : BindingDialogFragment<FragmentLogoutDialogBinding>(R.layout.fragment_logout_dialog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCancelButtonClick()
        initDeleteButtonClick(logout)
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this, 30.0f)
    }


    private fun initCancelButtonClick() {
        binding.btnLogoutNo.setOnClickListener {
            dismiss()
        }
    }

    private fun initDeleteButtonClick(deleteFollower: () -> Unit = { -> }) {
        binding.btnLogoutYes.setOnClickListener {
            deleteFollower.invoke()
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}