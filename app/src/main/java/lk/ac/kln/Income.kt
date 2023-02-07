package lk.ac.kln

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "incomes")
data class Income(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val Label:String,
    val Amount: Double,
    val description:String) {
}