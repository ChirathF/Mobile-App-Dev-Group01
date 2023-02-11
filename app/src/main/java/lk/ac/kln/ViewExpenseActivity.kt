package lk.ac.kln

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ViewExpenseActivity : AppCompatActivity() {
    private lateinit var transactions: ArrayList<ExpenseTransaction>
    private lateinit var transactionAdapter: ExpenseTransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var db : AppDatabase
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expense)
        recyclerView = findViewById(R.id.expense_recyclerview)


        transactions = arrayListOf(
            ExpenseTransaction("Home Rent", -12000.0F),
            ExpenseTransaction("Food", -10000.0F),
            ExpenseTransaction("Electricity", -3000.0F),
            ExpenseTransaction("Bus Fair", -5000.0F),
            ExpenseTransaction("Gift Item", -2000.0F),
            ExpenseTransaction("Cloths", -1800.0F),
            ExpenseTransaction("Medicine", -600.0F),

        )

        transactionAdapter = ExpenseTransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }
        updateExpenseDashboard()
        viewAddExpense()


    }

    private fun updateExpenseDashboard(){
        val expenseValue = findViewById<TextView>(R.id.expense_value)
        val expenseAmount = transactions.filter { it.amount<0 }.map {it.amount}.sum()

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