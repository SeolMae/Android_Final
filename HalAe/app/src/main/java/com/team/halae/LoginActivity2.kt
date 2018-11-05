package com.team.halae

import android.content.Intent
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v4.content.ContextCompat
import android.util.Base64
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_login_search1.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class LoginActivity2: AppCompatActivity(), View.OnClickListener{

    override fun onClick(v: View?) {
        when(v){

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login2)



        login_address_search_icon.setOnClickListener {
            val intent = Intent(applicationContext, LoginSearchActivity1::class.java)
            intent.putExtra("address_keyword", login_address_keyword.text.toString())
            startActivity(intent)
        }

        login_location_gps.setOnClickListener{

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

            }
            else {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 0)
                finish()
            }

            val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager

            var location : Location?
            var provider : String? = LocationManager.GPS_PROVIDER

            location = lm.getLastKnownLocation(provider)
            var latitude : String?
            var longitude : String?
            var latitudeD : Double
            var longitudeD : Double
            latitude = location.latitude.toString()
            longitude = location.longitude.toString()
            latitudeD = location.latitude
            longitudeD = location.longitude

            var address : String = run(latitudeD,longitudeD)

            Log.e("text", "위도 : " + latitude + "경도 : " + longitude)
            Log.e("변환했음",address)

            val intent = Intent(applicationContext, LoginSearchActivity2::class.java)
            intent.putExtra("address_gps", address)
            startActivity(intent)
        }
    }


    fun run(lat : Double, lon : Double) : String {
        val geocoder = Geocoder(this)
        var addresses : List<Address>? = null
        var addressText = ""
        try
        {
            addresses = geocoder.getFromLocation(lat,lon, 1)
            if (addresses != null && addresses.size > 0)
            {
                val address = addresses.get(0)
                addressText = address.getAdminArea() + " " + (if (address.maxAddressLineIndex > 0) address.getAddressLine(0) else address.locality) + " "
                val txt = address.getSubLocality()
                if (txt != null)
                    addressText += txt + " "
                addressText += address.getThoroughfare() + " " + address.getSubThoroughfare()
            }
            return addressText
        }
        catch (e:Exception) {
            e.printStackTrace()
            return e.toString()
        }
    }

}
