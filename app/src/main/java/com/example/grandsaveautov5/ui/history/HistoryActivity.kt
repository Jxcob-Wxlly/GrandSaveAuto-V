package com.example.gsa.ui.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.gsa.R
import com.example.gsa.data.AppDatabase
import com.example.gsa.data.Expense

class HistoryActivity : AppCompatActivity() {

    private lateinit var recyclerHistory: RecyclerView
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        recyclerHistory = findViewById(R.id.recyclerHistory)
        recyclerHistory.layoutManager = LinearLayoutManager(this)

        // Load expenses from RoomDB
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "expenses-db"
        ).build()
        val dao = db.expenseDao()

        Thread {
            val expenses = dao.getAllExpenses()
            runOnUiThread {
                adapter = HistoryAdapter(expenses)
                recyclerHistory.adapter = adapter
            }
        }.start()
    }
}
