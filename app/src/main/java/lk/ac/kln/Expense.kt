package lk.ac.kln

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val Label:String,
    val Amount: Double,
    val description:String) {
}