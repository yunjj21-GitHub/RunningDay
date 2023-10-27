package com.yunjung.runningday.presentation.tracking

import androidx.fragment.app.viewModels
import com.yunjung.runningday.R
import com.yunjung.runningday.base.BaseFragment
import com.yunjung.runningday.base.BaseViewModel
import com.yunjung.runningday.databinding.FragmentTrackingBinding
import com.yunjung.runningday.presentation.record.RecordFragment
import com.yunjung.runningday.presentation.shealth.SHealthFragment
import com.yunjung.runningday.util.extension.addFragment
import com.yunjung.runningday.util.extension.moveFragment

class TrackingFragment: BaseFragment<FragmentTrackingBinding>(R.layout.fragment_tracking) {
    companion object{
        fun newInstance() = TrackingFragment()
    }

    override val viewModel: BaseViewModel by viewModels()
    override fun initView() {
        binding.goToRecordBtn.setOnClickListener {
            activity.addFragment(RecordFragment())
        }

        binding.goToShealthBtn.setOnClickListener {
            activity.addFragment(SHealthFragment())
        }
    }
}