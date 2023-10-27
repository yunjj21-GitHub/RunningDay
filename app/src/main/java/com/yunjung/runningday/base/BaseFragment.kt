package com.yunjung.runningday.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.yunjung.runningday.presentation.main.MainActivity
import com.yunjung.runningday.presentation.main.MainViewModel
import com.yunjung.runningday.util.extension.moveFragment
import com.yunjung.runningday.util.extension.popBackFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseFragment<B: ViewDataBinding>(
    @LayoutRes
    private val layoutRes: Int
): Fragment() {
    val TAG = this::class.java.simpleName

    protected lateinit var binding: B
    abstract val viewModel: BaseViewModel
    val activityViewModel: MainViewModel by activityViewModels()
    val activity: MainActivity by lazy { requireActivity() as MainActivity }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        initView()
    }

    abstract fun initView()
    open fun initObserve(){}

    fun popBackFragment(){
        CoroutineScope(Dispatchers.Main).launch {
            activity.popBackFragment()
        }
    }

    fun moveFragment(fragment: BaseFragment<*>){
        CoroutineScope(Dispatchers.Main).launch {
            activity.moveFragment(fragment)
        }
    }

    open fun backPress(){
        CoroutineScope(Dispatchers.Main).launch{
            popBackFragment()
        }
    }
}