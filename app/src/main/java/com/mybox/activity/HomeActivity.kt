package com.mybox.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.OnClickListener
import android.widget.RelativeLayout
import android.widget.TextView

import com.mybox.R
import com.mybox.ui.fragment.NewRecommendFragment
import com.mybox.ui.fragment.Rec1Fragment
import com.mybox.ui.fragment.Rec2Fragment
import com.mybox.ui.fragment.Rec3Fragment


class HomeActivity : AppCompatActivity(), OnClickListener {

    private var fm: FragmentManager? = null
    private var mHomeFragment: NewRecommendFragment? = null
    private var mPondFragment: Rec1Fragment? = null
    private var mMessageFragment: Rec2Fragment? = null
    private var mMineFragment: Rec3Fragment? = null
    private var mCurrent: Fragment? = null

    private var mHomeLayout: RelativeLayout? = null
    private var mPondLayout: RelativeLayout? = null
    private var mMessageLayout: RelativeLayout? = null
    private var mMineLayout: RelativeLayout? = null
    private var mHomeView: TextView? = null
    private var mPondView: TextView? = null
    private var mMessageView: TextView? = null
    private var mMineView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()

        mHomeFragment = NewRecommendFragment()
        fm = supportFragmentManager
        val fragmentTransaction = fm!!.beginTransaction()
        fragmentTransaction.replace(R.id.content_layout, mHomeFragment)
        fragmentTransaction.commit()
    }


    private fun initView() {
        mHomeLayout = findViewById(R.id.home_layout_view) as RelativeLayout
        mHomeLayout!!.setOnClickListener(this)
        mPondLayout = findViewById(R.id.pond_layout_view) as RelativeLayout
        mPondLayout!!.setOnClickListener(this)
        mMessageLayout = findViewById(R.id.message_layout_view) as RelativeLayout
        mMessageLayout!!.setOnClickListener(this)
        mMineLayout = findViewById(R.id.mine_layout_view) as RelativeLayout
        mMineLayout!!.setOnClickListener(this)


        mHomeView = findViewById(R.id.home_image_view) as TextView
        mPondView = findViewById(R.id.fish_image_view) as TextView
        mMessageView = findViewById(R.id.message_image_view) as TextView
        mMineView = findViewById(R.id.mine_image_view) as TextView
        mHomeView!!.setBackgroundResource(R.drawable.comui_tab_home_selected)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun hideFragment(fragment: Fragment?, ft: FragmentTransaction) {
        if (fragment != null) {
            ft.hide(fragment)
        }
    }

    override fun onClick(v: View) {
        val fragmentTransaction = fm!!.beginTransaction()
        when (v.id) {
            R.id.home_layout_view -> {
                mHomeView!!.setBackgroundResource(R.drawable.comui_tab_home_selected)

                mPondView!!.setBackgroundResource(R.drawable.comui_tab_pond)
                mMessageView!!.setBackgroundResource(R.drawable.comui_tab_message)
                mMineView!!.setBackgroundResource(R.drawable.comui_tab_person)

                hideFragment(mMineFragment, fragmentTransaction)
                hideFragment(mPondFragment, fragmentTransaction)
                hideFragment(mMessageFragment, fragmentTransaction)
                if (mHomeFragment == null) {
                    mHomeFragment = NewRecommendFragment()
                    fragmentTransaction.add(R.id.content_layout, mHomeFragment)
                } else {
                    mCurrent = mHomeFragment
                    fragmentTransaction.show(mHomeFragment)
                }
            }
            R.id.pond_layout_view -> {
                mPondView!!.setBackgroundResource(R.drawable.comui_tab_pond_selected)
                mMessageView!!.setBackgroundResource(R.drawable.comui_tab_message)
                mHomeView!!.setBackgroundResource(R.drawable.comui_tab_home)
                mMineView!!.setBackgroundResource(R.drawable.comui_tab_person)

                hideFragment(mMineFragment, fragmentTransaction)
                hideFragment(mHomeFragment, fragmentTransaction)
                hideFragment(mMessageFragment, fragmentTransaction)
                if (mPondFragment == null) {
                    mPondFragment = Rec1Fragment()
                    fragmentTransaction.add(R.id.content_layout, mPondFragment)
                } else {
                    mCurrent = mPondFragment
                    fragmentTransaction.show(mPondFragment)
                }
            }
            R.id.message_layout_view -> {
                mMessageView!!.setBackgroundResource(R.drawable.comui_tab_message_selected)
                mHomeView!!.setBackgroundResource(R.drawable.comui_tab_home)
                mPondView!!.setBackgroundResource(R.drawable.comui_tab_pond)
                mMineView!!.setBackgroundResource(R.drawable.comui_tab_person)

                hideFragment(mMineFragment, fragmentTransaction)
                hideFragment(mPondFragment, fragmentTransaction)
                hideFragment(mHomeFragment, fragmentTransaction)
                if (mMessageFragment == null) {
                    mMessageFragment = Rec2Fragment()
                    fragmentTransaction.add(R.id.content_layout, mMessageFragment)
                } else {
                    mCurrent = mMessageFragment
                    fragmentTransaction.show(mMessageFragment)
                }
            }
            R.id.mine_layout_view -> {
                mMineView!!.setBackgroundResource(R.drawable.comui_tab_person_selected)
                mHomeView!!.setBackgroundResource(R.drawable.comui_tab_home)
                mPondView!!.setBackgroundResource(R.drawable.comui_tab_pond)
                mMessageView!!.setBackgroundResource(R.drawable.comui_tab_message)

                hideFragment(mPondFragment, fragmentTransaction)
                hideFragment(mHomeFragment, fragmentTransaction)
                hideFragment(mMessageFragment, fragmentTransaction)
                if (mMineFragment == null) {
                    mMineFragment = Rec3Fragment()
                    fragmentTransaction.add(R.id.content_layout, mMineFragment)
                } else {
                    mCurrent = mPondFragment
                    fragmentTransaction.show(mMineFragment)
                }
            }
        }

        fragmentTransaction.commit()
    }
}
