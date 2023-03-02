package com.intretech.customview.ui.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.dylanc.viewbinding.base.ActivityBinding
import com.dylanc.viewbinding.base.ActivityBindingDelegate

/**
 *
 *
 * @author: YQ16685 Chanrey Lee
 * @date 2022/9/2 - 20:06
 **/
abstract class BaseBindingActivity<VB: ViewBinding> : AppCompatActivity(),  ActivityBinding<VB> by ActivityBindingDelegate() {

    var mActivity: Activity? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        mActivity = this
        super.onCreate(savedInstanceState)
        setContentViewWithBinding()
        initData(savedInstanceState)
    }

    abstract fun initData(savedInstanceState: Bundle?)
}

