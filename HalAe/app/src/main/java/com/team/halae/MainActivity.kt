package com.team.halae

import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Base64
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v){
            main_home_tab->{
                clearSelected()
                main_home_tab.isSelected = true
            }
            main_search_tab->{
                clearSelected()
                main_search_tab.isSelected = true
            }
            main_sns_tab->{
                clearSelected()
                main_sns_tab.isSelected = true
            }
            main_donate_tab->{
                clearSelected()
                main_donate_tab.isSelected = true
            }
            main_mypage_tab->{
                clearSelected()
                main_mypage_tab.isSelected = true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(HalmateFragment())
        main_home_tab.isSelected = true
        main_home_tab.setOnClickListener(this)
        main_search_tab.setOnClickListener(this)
        main_sns_tab.setOnClickListener(this)
        main_donate_tab.setOnClickListener(this)
        main_mypage_tab.setOnClickListener(this)

        getHashKey()
    }

    fun addFragment(fragment: Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.main_frame, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.main_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun clearSelected(){
        main_home_tab.isSelected = false
        main_search_tab.isSelected = false
        main_sns_tab.isSelected = false
        main_donate_tab.isSelected = false
        main_mypage_tab.isSelected = false

    }

    private fun getHashKey() {
        try
        {
            val info = getPackageManager().getPackageInfo("com.team.halae", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures)
            {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("TAG", "key_hash=" + Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        }
        catch (e:PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }
}
