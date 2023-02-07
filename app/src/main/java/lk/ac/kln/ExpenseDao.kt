package lk.ac.kln

import androidx.room.*

@Dao
interface ExpenseDao {
    @Query("SELECT * from expenses")
    fun getAll(): List<Expense>

    @Insert
    fun insertAll(vararg expense: Expense)

    @Delete
    fun delete(vararg expense: Expense)

    @Update
    fun update(vararg expense: Expense)

}