package com.intretech.customview

import android.content.Intent
import android.os.Bundle
import com.intretech.customview.databinding.ActivityMainBinding
import com.intretech.customview.ui.*
import com.intretech.customview.ui.base.BaseBindingActivity
import com.intretech.customview.ui.bezier.BezierActivity
import com.intretech.customview.ui.widget.TitleBarTestActivity

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    
    override fun initData(savedInstanceState: Bundle?) {
        setListener()
    }

    private fun setListener() {
        
        binding.btnDrawGraph.setOnClickListener {
            val intent = Intent(this, GraphActivity::class.java)
            startActivity(intent)
        }

        binding.btnCanvasOperate.setOnClickListener {
            val intent = Intent(this, CanvasActivity::class.java)
            startActivity(intent)
        }

        binding.btnDrawPictTxt.setOnClickListener {
            val intent = Intent(this, PictTxtActivity::class.java)
            startActivity(intent)
        }

        binding.btnPathBasic.setOnClickListener {
            val intent = Intent(this, PathActivity::class.java)
            startActivity(intent)
        }

        binding.btnCharts.setOnClickListener {
            val intent = Intent(this, ChartActivity::class.java)
            startActivity(intent)
        }

        binding.btnBezier.setOnClickListener {
            val intent = Intent(this, BezierActivity::class.java)
            startActivity(intent)
        }

        binding.btnTitleBarTest.setOnClickListener {
            val intent = Intent(this, TitleBarTestActivity::class.java)
            startActivity(intent)
        }

        binding.btnSpringProgressTest.setOnClickListener {
            val intent = Intent(this, SpringProgressActivity::class.java)
            startActivity(intent)
        }
    }
}
