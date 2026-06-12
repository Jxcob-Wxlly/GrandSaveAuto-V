package com.example.gsa

import android.os.Bundle
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class AnalyticsActivity : AppCompatActivity() {

    private lateinit var pieChart: PieChart
    private lateinit var spinnerTimeRange: Spinner
    private lateinit var spinnerCategory: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)

        pieChart = findViewById(R.id.pieChart)
        spinnerTimeRange = findViewById(R.id.spinnerTimeRange)
        spinnerCategory = findViewById(R.id.spinnerCategory)

        // Example data (replace with DB query later)
        val entries = listOf(
            PieEntry(500f, "Food"),
            PieEntry(300f, "Transport"),
            PieEntry(200f, "Entertainment"),
            PieEntry(400f, "Shopping"),
            PieEntry(600f, "Bills"),
            PieEntry(100f, "Other")
        )

        val dataSet = PieDataSet(entries, "Expenses")
        dataSet.colors = listOf(
            resources.getColor(R.color.purple_200),
            resources.getColor(R.color.teal_200),
            resources.getColor(R.color.purple_500),
            resources.getColor(R.color.teal_700),
            resources.getColor(R.color.black),
            resources.getColor(R.color.gray)
        )

        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.invalidate() // refresh chart
    }
}
