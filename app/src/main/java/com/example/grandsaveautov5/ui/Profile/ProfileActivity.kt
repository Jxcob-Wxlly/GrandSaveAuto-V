package com.example.gsa.ui.profile

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gsa.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvUserName: TextView
    private lateinit var tvStreaks: TextView
    private lateinit var tvBadges: TextView
    private lateinit var tvStats: TextView
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvUserName = findViewById(R.id.tvUserName)
        tvStreaks = findViewById(R.id.tvStreaks)
        tvBadges = findViewById(R.id.tvBadges)
        tvStats = findViewById(R.id.tvStats)
        btnLogout = findViewById(R.id.btnLogout)

        val db = DatabaseProvider.getDatabase(applicationContext)
        val statsDao = db.userStatsDao()

        Thread {
            val stats = statsDao.getStats()
            runOnUiThread {
                if (stats != null) {
                    tvStreaks.text = "Current Streak: ${stats.streak} days"
                    tvBadges.text = "Badges: ${stats.badges}"
                    tvStats.text = "XP: ${stats.xp}\nLevel: ${stats.level}"
                }
            }
        }.start()

    }
}
