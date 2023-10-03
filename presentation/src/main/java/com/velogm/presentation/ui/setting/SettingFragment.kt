package com.velogm.presentation.ui.setting

import android.os.Bundle
import android.view.View
import com.velogm.core_ui.base.BindingFragment
import com.velogm.presentation.R
import com.velogm.presentation.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BindingFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

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

            })
            dialog.show(childFragmentManager, "logout")
        }

    }

    private fun withdrawal() {
        binding.tvSettingWithdrawal.setOnClickListener {
            val dialog = WithdrawalDialogFragment(withdrawal = {

            })
            dialog.show(childFragmentManager, "withdrawal")
        }
    }

}