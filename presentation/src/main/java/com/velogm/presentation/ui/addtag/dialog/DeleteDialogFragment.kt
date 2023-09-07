package com.velogm.presentation.ui.addtag.dialog

import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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

    override fun onResume() {
        super.onResume()
        setDialogSize()
    }

    private fun setDialogSize() {
        val dpToPixel = Resources.getSystem().displayMetrics.density
        val dialogHorizontalMarginInDp = 30
        val dialogHorizontalMarginInPixels =
            (dpToPixel * dialogHorizontalMarginInDp + 0.5f).toInt() // 반올림 처리
        val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
        dialog?.window?.setLayout(
            deviceWidth - 2 * dialogHorizontalMarginInPixels,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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

