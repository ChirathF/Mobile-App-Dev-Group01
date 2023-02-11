package lk.ac.kln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewExpenseActivity : AppCompatActivity() {
    private lateinit var transactions: ArrayList<ExpenseTransaction>
    private lateinit var transactionAdapter: ExpenseTransactionAdapter
    private lateinit var layoutManager: LinearLayoutManager
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
            ExpenseTransaction("Internet", -990.0F),

        )

        transactionAdapter = ExpenseTransactionAdapter(transactions)
        layoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            adapter = transactionAdapter
            layoutManager = layoutManager
        }





    }
}