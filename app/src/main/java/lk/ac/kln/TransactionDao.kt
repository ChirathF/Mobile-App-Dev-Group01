package lk.ac.kln

import androidx.room.*

@Dao
interface TransactionDao {
    @Query("SELECT * from transactions")
    fun getAll(): List<Transaction>

    @Insert
    fun insertAll(vararg transaction: Transaction)

    @Delete
    fun delete(vararg transaction: Transaction)

    @Update
    fun update(vararg transaction: Transaction)
}