package com.plugged.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
 data class Token (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
     var token: String)