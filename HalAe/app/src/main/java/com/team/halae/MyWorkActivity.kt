package com.team.halae

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_my_work.*
import android.support.v4.app.Fragment
import android.util.Log

class MyWorkActivity : AppCompatActivity(), View.OnClickListener{

    override fun onClick(v: View?) {
        when(v){
            mywrite -> {
                var bundle = Bundle()
                bundle.putString("title", mywrite.text.toString())
                //AddFragment(FirstFragment(), bundle, "first", supportFragmentManager.findFragmentById(R.id.main2_container))
                ReplaceFragment(MywriteFragment(), bundle, "first")
            }
            mymark -> {
                var bundle = Bundle()
                bundle.putString("title", mymark.text.toString())
                ReplaceFragment(MymarkFragment(), bundle, "second")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_work)

        mywrite.setOnClickListener(this)
        mymark.setOnClickListener(this)
    }


    fun AddFragment(fragment: Fragment, bundle: Bundle, tag: String, fragment2: Fragment) {
        val fm = supportFragmentManager               // 프레그먼트 매니져 객체를 생성
        val transaction = fm.beginTransaction()        // 트랜젝션 객체를 생성 프레그먼트 매니져 클래스를 통해 해당 트랙젝션을 수행하겠다는 의미
        transaction.remove(fragment2)                                  // 기존 프레그먼트 제거
        fragment.arguments = bundle                                 // bundle 객체를 넘겨준다
        transaction.add(R.id.mywork_fragment, fragment, tag)         // 새로운 프레그먼트 추가!!
        //transaction.addToBackStack(null);                             // 백스텍에 저장!!
        transaction.commit()
    }

    fun AddFragment(fragment: Fragment, bundle: Bundle, tag: String) {            //오버라이딩하여 최초에 추가될 프레그먼트 생성함수
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        fragment.arguments = bundle
        transaction.add(R.id.mywork_fragment, fragment, tag)
        //transaction.addToBackStack(null)
        transaction.commit()
    }

    fun TagFragment(tag: String) {
        Log.v("home", tag)
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(tag)
        if(fragment!=null) {
            transaction.replace(R.id.mywork_fragment, fragment)
            transaction.commit()
        }
    }

    fun ReplaceFragment(fragment: Fragment, bundle: Bundle, tag: String) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        fragment.arguments = bundle
        transaction.replace(R.id.mywork_fragment, fragment, tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
