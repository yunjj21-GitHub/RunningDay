package com.yunjung.runningday.presentation.shealth

import androidx.fragment.app.viewModels
import com.yunjung.runningday.R
import com.yunjung.runningday.base.BaseFragment
import com.yunjung.runningday.base.BaseViewModel
import com.yunjung.runningday.databinding.FragmentShealthBinding

class SHealthFragment: BaseFragment<FragmentShealthBinding>(R.layout.fragment_shealth) {
    companion object {
        fun newInstance() = SHealthFragment()
    }

    override val viewModel: BaseViewModel by viewModels()
    override fun initView() {}
}