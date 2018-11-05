package com.team.halae

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_login_search2.*

class LoginSearchActivity2: AppCompatActivity(), View.OnClickListener, OnMapReadyCallback {
    override fun onClick(v: View?) {
        when(v){

        }
    }

    private lateinit var mMap: GoogleMap
    var array = arrayOf("1","2","3")
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var networkService: NetworkService
        networkService = ApplicationController.instance.networkService


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_search2)

        var strAddress = intent.getStringExtra("address_result_keyword")
        Log.e("안녕",run(strAddress).toString())

        var latitude = run(strAddress).latitude.toString()
        var longitude = run(strAddress).longitude.toString()
        final_address_str.setText(strAddress)

        array[0] = latitude
        array[1] = longitude
        array[2] = strAddress


        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map_frame) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

        fun run(address : String) : Address {
            val geocoder = Geocoder(this)
            val results = geocoder.getFromLocationName(address, 1)
            val resultAddress = results.get(0)
//            val latLng = LatLng(resultAddress.getLatitude(), resultAddress.getLongitude())
            return resultAddress

    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!
        val latLng = LatLng(array[0].toDouble(), array[1].toDouble())
        val resultPosition : LatLng = latLng
        val resultAddress = array[2]
        mMap.addMarker(MarkerOptions().position(resultPosition).title(resultAddress))
        val position = CameraPosition.Builder()
                .target(latLng).zoom(16f).build()
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(position))
    }

}
