package com.example.gsa

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.TextView
import android.widget.Button

class DashboardActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var tvRank: TextView
    private lateinit var tvCoins: TextView
    private lateinit var tvStreak: TextView
    private lateinit var tvLevel: TextView
    private lateinit var tvExp: TextView
    private lateinit var tvAtk: TextView
    private lateinit var tvDef: TextView
    private lateinit var tvDex: TextView
    private lateinit var tvInt: TextView
    private lateinit var tvLuk: TextView
    private lateinit var tvBankBalance: TextView
    private lateinit var tvMonthlyBudget: TextView
    private lateinit var btnAddExpense: Button
    private lateinit var btnAnalytics: Button
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Bind views
        tvWelcome = findViewById(R.id.tvWelcome)
        tvRank = findViewById(R.id.tvRank)
        tvCoins = findViewById(R.id.tvCoins)
        tvStreak = findViewById(R.id.tvStreak)
        tvLevel = findViewById(R.id.tvLevel)
        tvExp = findViewById(R.id.tvExp)
        tvAtk = findViewById(R.id.tvAtk)
        tvDef = findViewById(R.id.tvDef)
        tvDex = findViewById(R.id.tvDex)
        tvInt = findViewById(R.id.tvInt)
        tvLuk = findViewById(R.id.tvLuk)
        tvBankBalance = findViewById(R.id.tvBankBalance)
        tvMonthlyBudget = findViewById(R.id.tvMonthlyBudget)
        btnAddExpense = findViewById(R.id.btnAddExpense)
        btnAnalytics = findViewById(R.id.btnAnalytics)
        bottomNav = findViewById(R.id.bottomNav)

        // Load user name dynamically from SharedPreferences
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "User")
        tvWelcome.text = "Welcome $username!"

        // Example: Load stats from DB or SharedPreferences
        val coins = sharedPref.getInt("coins", 8)
        val streak = sharedPref.getInt("streak", 1)
        val level = sharedPref.getInt("level", 1)
        val exp = sharedPref.getInt("exp", 44)
        val atk = sharedPref.getInt("atk", 9)
        val def = sharedPref.getInt("def", 4)
        val dex = sharedPref.getInt("dex", 5)
        val intStat = sharedPref.getInt("int", 5)
        val luk = sharedPref.getInt("luk", 4)
        val bankBalance = sharedPref.getFloat("bankBalance", 150000f)
        val monthlyBudget = sharedPref.getFloat("monthlyBudget", 40000f)

        // Update UI
        tvCoins.text = "Coins: $coins"
        tvStreak.text = "Streak: $streak"
        tvLevel.text = "LVL $level"
        tvExp.text = "EXP: $exp/100"
        tvAtk.text = "ATK: $atk"
        tvDef.text = "DEF: $def"
        tvDex.text = "DEX: $dex"
        tvInt.text = "INT: $intStat"
        tvLuk.text = "LUK: $luk"
        tvBankBalance.text = "Capitec Balance: R${bankBalance}"
        tvMonthlyBudget.text = "Monthly Budget: R${monthlyBudget}"

        // Buttons
        btnAddExpense.setOnClickListener {
            btnAddExpense.setOnClickListener {
                val intent = Intent(this, AddExpenseActivity::class.java)
                startActivity(intent)
            }

        }

        btnAnalytics.setOnClickListener {
            btnAnalytics.setOnClickListener {
                val intent = Intent(this, AnalyticsActivity::class.java)
                startActivity(intent)
            }

        }

        btnAnalytics.setOnClickListener {
            val intent = Intent(this, AnalyticsActivity::class.java)
            startActivity(intent)
        }

        btnRewards.setOnClickListener {
            val intent = Intent(this, RewardsActivity::class.java)
            startActivity(intent)
        }


        R.id.nav_reminder -> {
            val intent = Intent(this, ReminderActivity::class.java)
            startActivity(intent)
            true
        }

        R.id.nav_profile -> {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            true
        }
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "expenses-db").build()
        val statsDao = db.userStatsDao()

        Thread {
            val stats = statsDao.getStats()
            runOnUiThread {
                if (stats != null) {
                    val xpProgress = stats.xp % (stats.level * 100)
                    progressXP.progress = xpProgress
                }
            }
        }.start()

        #!/usr/bin/env kotlin

