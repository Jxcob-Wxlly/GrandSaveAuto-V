package com.example.gsa.ui.analytics

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.gsa.R
import com.example.gsa.data.AppDatabase

class AnalyticsActivity : AppCompatActivity() {

    private lateinit var tvTotalSpent: TextView
    private lateinit var tvCategoryBreakdown: TextView
    private lateinit var tvMonthlyTrend: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)

        tvTotalSpent = findViewById(R.id.tvTotalSpent)
        tvCategoryBreakdown = findViewById(R.id.tvCategoryBreakdown)
        tvMonthlyTrend = findViewById(R.id.tvMonthlyTrend)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "expenses-db").build()
        val dao = db.expenseDao()

        Thread {
            val expenses = dao.getAllExpenses()

            // Total spent
            val total = expenses.sumOf { it.amount }

            // Category breakdown
            val categoryMap = expenses.groupBy { it.category }
                .mapValues { entry -> entry.value.sumOf { it.amount } }

            // Monthly trend (group by date prefix yyyy-MM)
            val monthlyMap = expenses.groupBy { it.date.substring(0,7) }
                .mapValues { entry -> entry.value.sumOf { it.amount } }

            runOnUiThread {
                tvTotalSpent.text = "Total Spent: R$total"

                tvCategoryBreakdown.text = categoryMap.entries.joinToString("\n") {
                    "${it.key}: R${it.value}"
                }

                tvMonthlyTrend.text = monthlyMap.entries.joinToString("\n") {
                    "${it.key}: R${it.value}"
                }
            }
        }.start()
    }
}
