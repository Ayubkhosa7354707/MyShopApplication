package com.ayub.khosa.myloginapplication.model

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
@Immutable
data class USER(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,

    @ColumnInfo(name = "first_name") var first_name: String,

    @ColumnInfo(name = "last_name") var last_name: String,

    @ColumnInfo(name = "password") var password: String,

    @ColumnInfo(name = "user_id") var user_id: String,

    @ColumnInfo(name = "email_id") var email_id: String,

    @ColumnInfo(name = "tokenCode") var tokenCode: String,

    @ColumnInfo(name = "userStatus") var userStatus: String
) : Serializable




