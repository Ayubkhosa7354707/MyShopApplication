package com.ayub.khosa.myloginapplication.model

import androidx.compose.runtime.Immutable
import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "categorys")
@Immutable
data class CATEGORY(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "category_id") var category_id: String,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "name") var name: String,
    @androidx.databinding.Bindable
    @ColumnInfo(name = "img") var img: String
) : BaseObservable()