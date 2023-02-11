package lk.ac.kln

import androidx.room.*

@Dao
interface TransactionDao {
    @Query("SELECT * from transactions")
    fun getAll(): List<Transaction>

    //Query for getting all income transactions
    @Query("SELECT * from transactions WHERE amount > 0")
    fun getAllIncome(): List<Transaction>

    //Query for getting all expense transactions
    @Query("SELECT * from transactions WHERE amount < 0")
    fun getAllExpense(): List<Transaction>

    @Insert
    fun insertAll(vararg transaction: Transaction)

    @Delete
    fun delete(vararg transaction: Transaction)

    @Update
    fun update(vararg transaction: Transaction)
}