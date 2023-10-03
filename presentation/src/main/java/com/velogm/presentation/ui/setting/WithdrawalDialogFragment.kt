package com.velogm.presentation.ui.setting

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.velogm.core_ui.base.BindingDialogFragment
import com.velogm.core_ui.context.dialogFragmentResize
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentWithdrawalDialogBinding

class WithdrawalDialogFragment(
    private val withdrawal: () -> Unit = { -> }
) :
    BindingDialogFragment<FragmentWithdrawalDialogBinding>(R.layout.fragment_withdrawal_dialog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCancelButtonClick()
        initDeleteButtonClick(withdrawal)
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this, 30.0f)
    }


    private fun initCancelButtonClick() {
        binding.btnWithdrawalNo.setOnClickListener {
            dismiss()
        }
    }

    private fun initDeleteButtonClick(deleteFollower: () -> Unit = { -> }) {
        binding.btnWithdrawalYes.setOnClickListener {
            deleteFollower.invoke()
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}
