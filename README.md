# Grand Save Auto

A gamified expense tracker built in Android Studio with Kotlin and RoomDB.

## Features
- Add Expense: log amount, category, description, date (+10 XP each log).
- History: view all expenses stored in RoomDB.
- Profile: see streaks, badges, XP, and level.
- Dashboard: central hub with XP progress bar and navigation.
- Analytics: spending insights (total, category breakdown, monthly trend).
- Rewards: unlocked badges, streaks, XP milestones.

## Tech Stack
- Kotlin
- Android Studio
- RoomDB (SQLite persistence)
- RecyclerView for history
- ConstraintLayout for dashboard

## How It Works
1. Log an expense → saved in RoomDB.
2. XP and streak update automatically.
3. History shows expenses.
4. Profile & Rewards show gamified stats.
5. Analytics shows spending insights.

## Setup
- **Kotlin**
- **Language:** Java (JDK 12)
- **Build Tool:** Gradle 9.2.1
- **Version Control:** Git + GitHub
- AndroidX + Material Components
- Jetpack Navigation Component
- RoomDB (entities, DAO, repository, ViewModel)
