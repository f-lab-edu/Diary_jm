package com.example.sunflower_jm.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.sunflower_jm.db.DiaryEntity
import com.example.sunflower_jm.update.UpdateItemActivity
import com.example.sunflower_jm.update.UpdatePresenter

class DetailActivityContract : ActivityResultContract<DiaryEntity, HashMap<String, String>?>() {

    /*
     다른 액티비티를 호출하기 위한 인텐트를 생성, 제네릭 타입 I가 인텐트를 생성하기 위한 매개변수 타입으로 전달
     startActivityForResult 메서드 호출을 대체
     */
    override fun createIntent(context: Context, input: DiaryEntity): Intent {
        return UpdateItemActivity.getIntent(context, input)
//        return Intent(context, UpdateItemActivity::class.java).apply {
//            putExtra("input", input)
//        }
    }

    /*
    액티비티로 전달받은 결과 데이터를 제네릭 O타입으로 변환함
    onActivityResult 콜백 메서드 처리를 대체
     */
    override fun parseResult(resultCode: Int, intent: Intent?): HashMap<String, String>? {
        return when (resultCode) {
            Activity.RESULT_OK -> intent?.getSerializableExtra("result") as HashMap<String, String>
            else -> null
        }
    }
}