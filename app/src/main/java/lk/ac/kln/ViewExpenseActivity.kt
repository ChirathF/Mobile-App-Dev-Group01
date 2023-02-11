package lk.ac.kln

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewExpenseActivity : AppCompatActivity() {
    private lateinit var transactions: List<Transaction>
    private lateinit var transactionAdapter: ExpenseTransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var db : AppDatabase
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expense)
        recyclerView = findViewById(R.id.expense_recyclerview)

        db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transactions").build()

        transactions = listOf()

        transactionAdapter = ExpenseTransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }
        fetchAll()
        viewAddExpense()
    }

    private fun fetchAll(){
        GlobalScope.launch {
            //test values
            val trDao = db.transactionDao()
            val tr= Transaction(0,"test",40.0,"des");
            val tr1= Transaction(0,"test",-20.0,"des")
            trDao.insertAll(tr)
            trDao.insertAll(tr1)
            transactions = db.transactionDao().getAllExpense()

            runOnUiThread {
                updateExpenseDashboard()
                transactionAdapter.setData(transactions)
            }
        }
    }

    private fun updateExpenseDashboard(){
        val expenseValue = findViewById<TextView>(R.id.expense_value)
        val expenseAmount = transactions.filter { it.Amount<0 }.map {it.Amount}.sum()

        expenseValue.text = "LKR %.2f".format(expenseAmount)
    }
    private fun viewAddExpense(){
        val addButton = findViewById<FloatingActionButton>(R.id.expense_add_btn)
        addButton.setOnClickListener{
            val intent = Intent(this, AddExpense::class.java)
            startActivity(intent)
        }
    }


}