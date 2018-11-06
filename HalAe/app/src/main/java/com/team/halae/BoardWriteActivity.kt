package com.team.halae

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_board_comment.*
import kotlinx.android.synthetic.main.activity_board_write.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class BoardWriteActivity : AppCompatActivity() {
    var token : String = " "
    private var networkService: NetworkService? = null
    private var requestManager: RequestManager? = null

    var selectHal_idx : Int? = null




    private var iv_UserPhoto: ImageView? = null
    private var absoultePath: String? = null

    companion object {
        private val PICK_FROM_ALBUM = 1
        private val CROP_FROM_iMAGE = 2
    }

    private var mImageCaptureUri: Uri? = null
    private var editProfile : MultipartBody.Part? = null

    private var imageFile : File? = null

    fun doTakeAlbumAction() // 앨범에서 이미지 가져오기
    {
        // 앨범 호출
        val intent = Intent(Intent.ACTION_PICK)
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE)
        startActivityForResult(intent, PICK_FROM_ALBUM)
    }

    private fun storeCropImage(bitmap: Bitmap, filePath: String) {
        // SmartWheel 폴더를 생성하여 이미지를 저장
        val dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SmartWheel"
        val directory_SmartWheel = File(dirPath)
        if (!directory_SmartWheel.exists())
        // SmartWheel 디렉터리에 폴더가 없다면 (새로 이미지를 저장할 경우)
            directory_SmartWheel.mkdir()
        val copyFile = File(filePath)
        var out: BufferedOutputStream? = null
        var baos : ByteArrayOutputStream? = null
        try {
            copyFile.createNewFile()
            out = BufferedOutputStream(FileOutputStream(copyFile))
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            // sendBroadcast를 통해 Crop된 사진을 앨범에 보이도록 갱신
            sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(copyFile)))
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos!!.toByteArray())

        editProfile = MultipartBody.Part.createFormData("image", "name", photoBody)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK)
            return
        when (requestCode) {
            PICK_FROM_ALBUM -> {
                if(data == null){
                    Log.v("main", "inRWhen")
                    return
                }

                run({
                    // 이후의 처리가 카메라와 같으므로 일단 break없이 진행
                    mImageCaptureUri = data.getData()
                    Log.d("SmartWheel", mImageCaptureUri!!.getPath().toString())
                })
                run({
                    // 이미지를 가져온 이후의 리사이즈할 이미지 크기
                    // 이후에 이미지 크롭 어플리케이션을 호출
                    val intent = Intent("com.android.camera.action.CROP")
                    intent.setDataAndType(mImageCaptureUri, "image/*")
                    // CROP할 이미지를 200*200 크기로 저장
                    intent.putExtra("outputX", 200) // CROP한 이미지의 x축 크기
                    intent.putExtra("outputY", 200) // CROP한 이미지의 y축 크기
                    intent.putExtra("aspectX", 1) // CROP 박스의 X축 비율
                    intent.putExtra("aspectY", 1) // CROP 박스의 Y축 비율
                    intent.putExtra("scale", true)
                    intent.putExtra("return-data", true)

                    startActivityForResult(intent, CROP_FROM_iMAGE) // CROP_FROM_CAMERA case문 이동
                })
            }
            CROP_FROM_iMAGE -> {

                // 크롭이 된 이후의 이미지
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에 임시 파일 삭제
                if (resultCode != RESULT_OK) {
                    return
                }

                val extras = data!!.getExtras()
                // CROP된 이미지를 저장하기 위한 FILE 경로
                val filePath = (Environment.getExternalStorageDirectory().getAbsolutePath() +
                        "/SmartWheel/" + System.currentTimeMillis() + ".jpg")
                if (extras != null) {
                    val photo = extras.getParcelable("data") as Bitmap // CROP된 BITMAP
                    iv_UserPhoto!!.setImageBitmap(photo) // 레이아웃의 이미지칸에 CROP된 BITMAP을 보여줌
                    storeCropImage(photo, filePath) // CROP된 이미지를 외부저장소, 앨범에 저장
                    absoultePath = filePath
                }
                // 임시 파일 삭제
                val f = File(mImageCaptureUri!!.getPath())
                if (f.exists()) {
                    f.delete()
                }
            }
        }
        if(data == null){
            Log.v("main", "outWhen")

            return
        }
//        imageFile = File(mImageCaptureUri!!.path)
//        Log.d("데이터",data.toString())
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        networkService = ApplicationController.instance!!.networkService
        requestManager = Glide.with(this)

        doTakeAlbumAction()

        var halList : ArrayList<UsrHalData>
        var halName : ArrayList<String>? = null

        spinner_hal.setOnClickListener{
            val getUsrHalResponse = networkService!!.getUsrHalList(token)
            getUsrHalResponse.enqueue(object : Callback<UsrHalListResponse>{
                override fun onFailure(call: Call<UsrHalListResponse>?, t: Throwable?) {
                    ApplicationController.instance!!.makeToast("통신 확인")
                }

                override fun onResponse(call: Call<UsrHalListResponse>?, response: Response<UsrHalListResponse>?) {
                    if(response!!.isSuccessful){
                        if(response!!.body().message == "Successfully get usr_halmae"){
                            halList = response!!.body().data
                            for(i in 0..halList.size)
                                halName!!.add(halList[i].hal_name)

                            val adapter = ArrayAdapter<String>(this@BoardWriteActivity, R.layout.support_simple_spinner_dropdown_item, halName)
                            spinner_hal.adapter = adapter
                            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)


                            fun onNothingSelected(parent: AdapterView<*>?) {
                            }

                            fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                selectHal_idx = halList!![position].hal_idx
                            }
                        }
                    }
                }

            })
        }

        boardwrite_add.setOnClickListener{
            var board_title = boardwrite_title.text.toString()
            var board_content = boardwrite_content.text.toString()


            val postBoardAddResponse = networkService!!.postBoardAdd(token, boardAddPost(board_title, board_content, absoultePath!!, selectHal_idx!!, token))
            postBoardAddResponse.enqueue(object : Callback<postBoardAddResponse>{
                override fun onFailure(call: Call<postBoardAddResponse>?, t: Throwable?) {
                    ApplicationController.instance!!.makeToast("통신 확인")
                }

                override fun onResponse(call: Call<postBoardAddResponse>?, response: Response<postBoardAddResponse>?) {if(response!!.isSuccessful){
                    if(response!!.body().message == "Successfully register board"){
                        ApplicationController.instance!!.makeToast("수정 완료")
                    }
                }
                }

            })

        }

    }


}
