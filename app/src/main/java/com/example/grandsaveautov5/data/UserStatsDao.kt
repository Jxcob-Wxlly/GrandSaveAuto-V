package com.example.gsa.data

import androidx.room.*

@Dao
interface UserStatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(stats: UserStats)

    @Query("SELECT * FROM user_stats WHERE id = 1")
    suspend fun getStats(): UserStats?
}
