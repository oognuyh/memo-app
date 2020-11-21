package com.example.note.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var title: String = "",
    var content: String = "",
    var date: String = "",
    var color: Int = -1
) : Parcelable
