package com.yunjung.runningday.presentation.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.whenResumed
import com.yunjung.runningday.R
import com.yunjung.runningday.base.BaseActivity
import com.yunjung.runningday.base.BaseFragment
import com.yunjung.runningday.databinding.ActivityMainBinding
import com.yunjung.runningday.presentation.tracking.TrackingFragment
import com.yunjung.runningday.util.RunningDayLog
import com.yunjung.runningday.util.extension.initFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity() {
    val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        initFragment()
        setBackPressedEvent()
    }

    /*
    * backPressedEvent 설정
    * */
    private fun setBackPressedEvent(){
        val onBackPressedDispatcher = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val visibleFragment = supportFragmentManager.fragments.lastOrNull { it.isVisible }
                RunningDayLog.d("${visibleFragment?.javaClass?.simpleName} back pressed")

                if(visibleFragment is TrackingFragment) {
                    if(System.currentTimeMillis() - backPressedTime < 2000){
                        exitApp()
                    }else showAppExitToast()
                }else if(visibleFragment is BaseFragment<*>){
                    visibleFragment.backPress()
                }
            }
        }
        this.onBackPressedDispatcher.addCallback(this, onBackPressedDispatcher)
    }

    private var backPressedTime = 0L
    private fun showAppExitToast(){
        backPressedTime = System.currentTimeMillis()
        Toast.makeText(this, "\'뒤로\'버튼을 한번 더 누르면 종료됩니다.",
            Toast.LENGTH_SHORT).show()
    }

    /*
    * 앱 종료
    * */
    private fun exitApp(){
        CoroutineScope(Dispatchers.Default).launch {
            delay(200)
            withContext(Dispatchers.Main){
                finishAndRemoveTask()
            }
        }
    }

    /*
    * fragment 초기화
    * */
    private fun initFragment(){
        TrackingFragment.newInstance().let { fragment ->
            CoroutineScope(Dispatchers.Main).launch {
                whenResumed {
                    initFragment(fragment)
                }
            }
        }
    }
}