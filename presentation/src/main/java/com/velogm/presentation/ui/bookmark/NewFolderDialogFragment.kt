package com.velogm.presentation.ui.bookmark

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.velogm.core_ui.base.BindingDialogFragment
import com.velogm.core_ui.context.dialogFragmentResize
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentNewFolderDialogBinding

class NewFolderDialogFragment(
    private val newFolder: () -> Unit = { -> }
) :
    BindingDialogFragment<FragmentNewFolderDialogBinding>(R.layout.fragment_new_folder_dialog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCancelButtonClick()
        initOkButtonClick(newFolder)
        initEditTextClick()
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this, 30.0f)
    }


    private fun initCancelButtonClick() {
        binding.btnDialogNewFolderNo.setOnClickListener {
            dismiss()
        }
    }

    private fun initEditTextClick() {
        binding.btnDialogNewFolderNo.setOnClickListener {
            dismiss()
        }
    }

    private fun initOkButtonClick(newFolder: () -> Unit = { -> }) {
        binding.btnDialogNewFolderYes.setOnClickListener {
            newFolder.invoke()
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}

