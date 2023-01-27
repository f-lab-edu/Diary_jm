package com.example.sunflower_jm.activity

import android.Manifest
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.databinding.AddItemBinding
import com.example.sunflower_jm.db.DiaryEntity

class AddItemActivity : AppCompatActivity() {

    lateinit var binding: AddItemBinding
    lateinit var db: AppDatabase
    lateinit var diaryDao: DiaryDao
    private var uriInfo: Uri? = null

    private val permissionList = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    private val requestMultiplePermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            result.forEach {
                Log.e("권한", it.toString())
                if (!it.value) {
                    Toast.makeText(applicationContext, "${it.key}권한 허용 필요", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

    // 파일 불러오기
//    private val readImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//        Glide.with(this).load(uri).into(binding.imgLoad)
//        uriInfo = uri
//    }
    private val readImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it.data?.data?.let { uri ->
                contentResolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION)
                Glide.with(this).load(uri).into(binding.imgLoad)
                uriInfo = uri

            }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getInstance(this)!!
        diaryDao = db.getDiaryDao()
//        requestPermissions()
        requestMultiplePermission.launch(permissionList)
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"

        binding.btnAddImage.setOnClickListener {
            readImage.launch(intent)
        }
        binding.btnCompletion.setOnClickListener {
            insertItem()
        }
    }

    private fun insertItem() {
        val itemImage = uriInfo.toString()
        // 임의로 URL을 넣으면 이미지가 잘 나옴
//        val itemImage = "data:image/jpeg;base64,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"
        val itemTitle = binding.editTitle.text.toString()
        val itemContent = binding.editContent.text.toString()
        if (itemTitle.isBlank() || itemContent.isBlank()) {
            Toast.makeText(this, "모든 항목을 채워주세요!!", Toast.LENGTH_SHORT).show()
        } else {
            Thread {
                diaryDao.insertItem(DiaryEntity(null, itemImage, itemTitle, itemContent))
                runOnUiThread {
                    Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.start()
        }
    }
}