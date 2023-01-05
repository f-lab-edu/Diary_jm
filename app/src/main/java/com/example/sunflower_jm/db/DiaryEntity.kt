package com.example.sunflower_jm.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
@Entity
어떤 구성요소인지 알려주려면 꼭 어노테이션을 사용해야 함

data class
주된 목적은데이터를 가지고 있는 것
게시글 정보만 가지고 있기만 하면 되므로 데이터 클래스로 만들기 적합
 */
@Entity
data class DiaryEntity (
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String
) : java.io.Serializable