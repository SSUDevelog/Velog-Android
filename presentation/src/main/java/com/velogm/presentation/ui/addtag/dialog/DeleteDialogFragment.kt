package com.velogm.presentation.ui.addtag.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.velogm.core_ui.base.BindingDialogFragment
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentDeleteTagDialogBinding

class DeleteDialogFragment(
    private val deleteTag: () -> Unit = { -> }
) :
    BindingDialogFragment<FragmentDeleteTagDialogBinding>(R.layout.fragment_delete_tag_dialog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCancelButtonClick()
        initDeleteButtonClick(deleteTag)
    }


    private fun initCancelButtonClick() {
        binding.btnDeleteTagCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initDeleteButtonClick(deleteTag: () -> Unit = { -> }) {
        binding.btnDeleteTagDelete.setOnClickListener {
            deleteTag.invoke()
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}

