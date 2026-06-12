package com.example.gsa.ui.rewards

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gsa.R
import com.example.gsa.data.AppDatabase
import com.example.gsa.data.DatabaseProvider

class RewardsActivity : AppCompatActivity() {

    private lateinit var tvXPLevel: TextView
    private lateinit var tvStreak: TextView
    private lateinit var tvBadgesList: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rewards)

        tvXPLevel = findViewById(R.id.tvXPLevel)
        tvStreak = findViewById(R.id.tvStreak)
        tvBadgesList = findViewById(R.id.tvBadgesList)

        val db = DatabaseProvider.getDatabase(applicationContext)
        val statsDao = db.userStatsDao()

        Thread {
            val stats = statsDao.getStats()
            runOnUiThread {
                if (stats != null) {
                    tvXPLevel.text = "XP: ${stats.xp} | Level: ${stats.level}"
                    tvStreak.text = "Current Streak: ${stats.streak} days"
                    tvBadgesList.text = if (stats.badges.isNotEmpty()) {
                        "Badges: ${stats.badges}"
                    } else {
                        "Badges: None yet"
                    }
                }
            }
        }.start()
    }
}
