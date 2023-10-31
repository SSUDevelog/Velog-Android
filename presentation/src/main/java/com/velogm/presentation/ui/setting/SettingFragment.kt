package com.velogm.presentation.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.velogm.core_ui.base.BindingFragment
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentSettingBinding
import com.velogm.presentation.ui.signin.SignViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BindingFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val viewModel by activityViewModels<SignViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        logout()
        withdrawal()
    }

    private fun logout() {
        binding.tvSettingLogout.setOnClickListener {
            val dialog = LogoutDialogFragment(logout = {
                viewModel.postLogout()
            })
            dialog.show(childFragmentManager, "logout")
        }

    }

    private fun withdrawal() {
        binding.tvSettingWithdrawal.setOnClickListener {
            val dialog = WithdrawalDialogFragment(withdrawal = {
                viewModel.postWithdrawal()
            })
            dialog.show(childFragmentManager, "withdrawal")
        }
    }
}