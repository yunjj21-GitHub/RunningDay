package com.yunjung.runningday.presentation.record

import androidx.fragment.app.viewModels
import com.yunjung.runningday.R
import com.yunjung.runningday.base.BaseFragment
import com.yunjung.runningday.base.BaseViewModel
import com.yunjung.runningday.databinding.FragmentRecordBinding
import com.yunjung.runningday.presentation.tracking.TrackingFragment

class RecordFragment: BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {
    companion object {
        fun newInstance() = RecordFragment()
    }

    override val viewModel: BaseViewModel by viewModels()
    override fun initView() {}
}