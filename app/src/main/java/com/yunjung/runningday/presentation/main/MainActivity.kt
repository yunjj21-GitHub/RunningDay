package com.yunjung.runningday.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.yunjung.runningday.R
import com.yunjung.runningday.base.BaseActivity
import com.yunjung.runningday.databinding.ActivityMainBinding
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

        setBackEvent()
    }

    /*
    * backEvent 설정
    * */
    private fun setBackEvent(){
        val onBackPressedDispatcher = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(System.currentTimeMillis() - backPressedTime < 2000){
                    exitApp()
                }else {
                    showAppExitToast()
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
}