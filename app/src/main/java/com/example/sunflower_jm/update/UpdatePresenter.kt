package com.example.sunflower_jm.update

import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.db.DiaryEntity

class UpdatePresenter(
    private val id: Int,
    private val itemTitle: String,
    private val itemContent: String,
    private val diaryDao: DiaryDao,
    private val view: UpdateContract.View
) : UpdateContract.Presenter {

    override fun updateContent() {
        Thread {
            diaryDao.updateItem(DiaryEntity(id, itemTitle, itemContent))
        }.start()
        view.finishActivity("수정되었습니다.")
    }

    override fun makeMap() {
        val map: HashMap<String, String> = hashMapOf(
            "title" to itemTitle,
            "content" to itemContent
        )
        view.sendResult(map)
    }


}