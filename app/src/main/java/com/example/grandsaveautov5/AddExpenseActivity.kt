package com.example.gsa

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var etAmount: EditText
    private lateinit var etCategory: EditText
    private lateinit var etDescription: EditText
    private lateinit var etDate: EditText
    private lateinit var btnAddReceipt: Button
    private lateinit var btnSaveExpense: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        // Bind views
        etAmount = findViewById(R.id.etAmount)
        etCategory = findViewById(R.id.etCategory)
        etDescription = findViewById(R.id.etDescription)
        etDate = findViewById(R.id.etDate)
        btnAddReceipt = findViewById(R.id.btnAddReceipt)
        btnSaveExpense = findViewById(R.id.btnSaveExpense)

        // Add Receipt (placeholder)
        btnAddReceipt.setOnClickListener {
            Toast.makeText(this, "Receipt photo feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Save Expense
        btnSaveExpense.setOnClickListener {
            val amount = etAmount.text.toString().toDoubleOrNull()
            val category = etCategory.text.toString()
            val description = etDescription.text.toString()
            val date = etDate.text.toString()

            if (amount != null && category.isNotEmpty() && description.isNotEmpty() && date.isNotEmpty()) {
                val expense = Expense(category = category, description = description, amount = amount, date = date)

                // Save to RoomDB
                val db = DatabaseProvider.getDatabase(applicationContext)
                val dao = db.expenseDao()
                val statsDao = db.userStatsDao()

                Thread {
                    dao.insertExpense(expense)

                    var stats = statsDao.getStats() ?: UserStats()
                    // XP, streak, badge logic here.s
                    statsDao.insertOrUpdate(stats)
                }.start()

                Toast.makeText(this, "Expense saved! +10 XP earned!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
            Thread {
                dao.insertExpense(expense)

                val statsDao = db.userStatsDao()
                var stats = statsDao.getStats() ?: UserStats()

                // XP logic
                stats.xp += 10
                if (stats.xp >= stats.level * 100) {
                    stats.level += 1

                    val statsDao = db.userStatsDao()
                    var stats = statsDao.getStats() ?: UserStats()

                    val today = date

                    if (stats.lastLogDate.isEmpty()) {
                        // First log ever
                        stats.streak = 1
                        stats.lastLogDate = today
                    } else {
                        if (stats.lastLogDate == today) {
                            // Already logged today - streak unchanged
                        } else if (isYesterday(stats.lastLogDate, today)) {
                            // Consecutive day - increment streak
                            stats.streak += 1
                            stats.lastLogDate = today
                        } else {
                            // Missed a day - reset streak
                            stats.streak = 1
                            stats.lastLogDate = today
                        }
                    }

                }

                // Streak logic
                val today = date
                if (stats.lastLogDate == today) {
                    // same day, streak unchanged
                } else {
                    // check if yesterday
                    // (simple version: increment streak if consecutive, else reset)
                    stats.streak += 1
                    stats.lastLogDate = today
                }

                // Badge logic
                if (stats.xp >= 100 && !stats.badges.contains("Budget Hero")) {
                    stats.badges += if (stats.badges.isEmpty()) "Budget Hero" else ",Budget Hero"
                }

                statsDao.insertOrUpdate(stats)
            }.start()

        }
        import java.text.SimpleDateFormat
                import java.util.*

        fun isYesterday(lastDate: String, today: String): Boolean {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val last = sdf.parse(lastDate)
            val current = sdf.parse(today)

            val calLast = Calendar.getInstance().apply { time = last }
            val calToday = Calendar.getInstance().apply { time = current }

            calLast.add(Calendar.DAY_OF_YEAR, 1)
            return calLast.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                    calLast.get(Calendar.DAY_OF_YEAR) == calToday.get(Calendar.DAY_OF_YEAR)
        }


    }
