package com.example.gsa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {

    private lateinit var recyclerHistory: RecyclerView
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        recyclerHistory = findViewById(R.id.recyclerHistory)
        recyclerHistory.layoutManager = LinearLayoutManager(this)

        // Example data (replace with RoomDB query later)
        val expenses = listOf(
            Expense("Food", "Lunch at KFC", 80.0, "2026-06-10"),
            Expense("Transport", "Uber ride", 120.0, "2026-06-11"),
            Expense("Shopping", "Sneakers", 1500.0, "2026-06-12")
        )

        adapter = HistoryAdapter(expenses)
        recyclerHistory.adapter = adapter
    }
}
