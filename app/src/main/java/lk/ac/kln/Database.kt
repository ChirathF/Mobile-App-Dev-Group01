package lk.ac.kln

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Income::class,Expense::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract  fun incomeDao() : IncomeDao
}