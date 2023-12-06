package com.yunjung.runningday.util.extension

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import com.yunjung.runningday.R
import com.yunjung.runningday.base.BaseFragment
import com.yunjung.runningday.util.RDLog

private fun FragmentActivity?.available(block: () -> Unit){
    this?.run{
        if(!isFinishing && !isDestroyed){
            if(lifecycle.currentState == Lifecycle.State.RESUMED) {
                block()
            }
        }
    }
}

fun FragmentActivity.initFragment(fragment: BaseFragment<*>){
    supportFragmentManager.commit(true) {
        setReorderingAllowed(true)
        add(R.id.fragment_container_view, fragment, fragment.javaClass.simpleName)
    }
}

fun FragmentActivity.addFragment(fragment: BaseFragment<*>) = available {
    with(supportFragmentManager){
        commit(true) {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, fragment, fragment.TAG)
            addToBackStack(fragment.TAG)
        }
    }
}

// 화면 이동
// 같은 화면이 있으면 위에서 부터 그 화면까지 종료, 팝업 웹뷰 제외
fun FragmentActivity.moveFragment(fragment: BaseFragment<*>) = available{
    with(supportFragmentManager){
        if(fragments.find{it.javaClass.simpleName == fragment.TAG} != null){
            fragments.findLast {
                popBackFragment()
                it.javaClass.simpleName == fragment.TAG
            }
        }

        commit(true) {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, fragment, fragment.TAG)
            addToBackStack(fragment.TAG)
        }
    }
}

fun FragmentActivity.popBackFragment() = available{
    if(supportFragmentManager.backStackEntryCount > 0){
        supportFragmentManager.popBackStack()
    }
}

fun FragmentActivity.clearAllFragments() = available{
    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}

inline fun <reified T: BaseFragment<*>> FragmentActivity.findFragment()
        = supportFragmentManager.fragments.findLast { it is T }?.let { it as T }

fun printBackStackList(fm: FragmentManager){
    fm.backStackEntryCount.let { cnt ->
        if(cnt > 0){
            RDLog.d("print backstack count")
            for(i in fm.backStackEntryCount - 1 downTo 0){
                RDLog.d("$i - ${fm.getBackStackEntryAt(i)}")
            }
        }
    }
}