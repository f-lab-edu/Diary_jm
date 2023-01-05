package com.example.sunflower_jm.update

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower_jm.databinding.UpdateItemBinding
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.db.DiaryEntity

class UpdateItemActivity : AppCompatActivity(), UpdateContract.View {

    lateinit var binding: UpdateItemBinding
    lateinit var db: AppDatabase
    lateinit var diaryDao: DiaryDao

    private lateinit var item: DiaryEntity

    private val presenter by lazy {
        UpdatePresenter(
            item.id!!,
            binding.editTitle.text.toString(),
            binding.editContent.text.toString(),
            diaryDao,
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UpdateItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        diaryDao = db.getDiaryDao()

        binding.btnUpdateCompletion.setOnClickListener {
            if (binding.editTitle.text.isBlank() || binding.editContent.text.isBlank()) {
                Toast.makeText(this, "모든 항목을 채워주세요!", Toast.LENGTH_SHORT).show()
            } else {
                presenter.updateContent()
            }
        }

        item = intent.getSerializableExtra(KEY_DATA) as DiaryEntity
        binding.editTitle.text = Editable.Factory.getInstance().newEditable(item.title)
        binding.editContent.text = Editable.Factory.getInstance().newEditable(item.content)

    }

    override fun finishActivity(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        presenter.makeMap()
        finish()
    }

    override fun sendResult(map: HashMap<String, String>) {
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra("result", map)
        })
    }

    companion object {

        private const val KEY_DATA = "key-data"

        fun getIntent(context: Context, data: DiaryEntity): Intent =
            Intent(context, UpdateItemActivity::class.java).apply {
                putExtra(KEY_DATA, data)
            }
    }
}