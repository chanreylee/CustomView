package com.intretech.customview.ui.bezier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.intretech.customview.R
import kotlinx.android.synthetic.main.activity_bezier.*
import kotlinx.android.synthetic.main.fragment_bezier3.*

class BezierActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bezier)

        vp2_bezier.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 3

            override fun createFragment(position: Int): Fragment {
                return when(position) {
                    0 -> Bezier1A2Fragment()
                    1 -> Bezier3Fragment()
                    else -> BezierHeartFragment()
                }
            }
        }

        TabLayoutMediator(tabLayout_bezier, vp2_bezier) {tab, position ->
             when(position) {
                 0 -> tab.text = "二阶"
                 1 -> tab.text = "三阶"
                 else -> tab.text = "心型"
             }
        }.attach()   //必须加 ，绑定TabLayout和ViewPager
    }
}
