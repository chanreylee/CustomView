package com.intretech.customview.view.picture

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message

/**
 * @PackageName:com.intretech.customview.view.picture
 * @DESC:
 * @Author: YQ16685 Chanrey Lee
 * @Date 2020/10/7 - 16:55
 */
class FFFF {
    private var mhandle: Handler? = null

    @SuppressLint("HandlerLeak")
    fun dd() {
        mhandle = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
            }
        }
        mhandle = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
            }
        }
    }
}
