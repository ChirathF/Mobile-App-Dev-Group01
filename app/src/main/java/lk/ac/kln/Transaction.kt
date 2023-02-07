package lk.ac.kln

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val Label:String,
    val Amount: Double,
    val description:String) {
}