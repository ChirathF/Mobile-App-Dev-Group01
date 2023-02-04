package lk.ac.kln

import androidx.room.*

@Dao
interface IncomeDao {
    @Query("SELECT * from incomes")
    fun getAll(): List<Income>

    @Insert
    fun insertAll(vararg income: Income)

    @Delete
    fun delete(vararg income: Income)

    @Update
    fun update(vararg income: Income)
}