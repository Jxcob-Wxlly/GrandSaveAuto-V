package com.example.gsa

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ReminderActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var etAmount: EditText
    private lateinit var etDate: EditText
    private lateinit var spinnerRecurrence: Spinner
    private lateinit var btnSaveReminder: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        // Bind views
        etTitle = findViewById(R.id.etReminderTitle)
        etDescription = findViewById(R.id.etReminderDescription)
        etAmount = findViewById(R.id.etReminderAmount)
        etDate = findViewById(R.id.etReminderDate)
        spinnerRecurrence = findViewById(R.id.spinnerRecurrence)
        btnSaveReminder = findViewById(R.id.btnSaveReminder)

        // Save Reminder
        btnSaveReminder.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val amount = etAmount.text.toString()
            val date = etDate.text.toString()
            val recurrence = spinnerRecurrence.selectedItem.toString()

            if (title.isNotEmpty() && description.isNotEmpty() && amount.isNotEmpty() && date.isNotEmpty()) {
                // TODO: Insert into RoomDB
                // TODO: Add notification scheduling logic
                Toast.makeText(this, "Reminder saved: $title ($recurrence)", Toast.LENGTH_SHORT).show()
                finish() // return to dashboard
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
