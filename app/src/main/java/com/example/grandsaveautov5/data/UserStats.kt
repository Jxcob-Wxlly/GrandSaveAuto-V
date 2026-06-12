package com.example.gsa.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stats")
data class UserStats(
    @PrimaryKey val id: Int = 1, // single row for the user
    var xp: Int = 0,
    var level: Int = 1,
    var streak: Int = 0,
    var lastLogDate: String = "",
    var badges: String = "" // comma-separated list
)
