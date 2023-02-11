package lk.ac.kln

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewIncomeActivity : AppCompatActivity() {
    private lateinit var transactions: ArrayList<ExpenseTransaction>
    private lateinit var transactionAdapter: ExpenseTransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var db : AppDatabase
    private lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_income)
        recyclerView = findViewById(R.id.income_recyclerview)

        transactions = arrayListOf(
            ExpenseTransaction("Salary", 40000.0F),
            ExpenseTransaction("Donation", 5000.0F),
            ExpenseTransaction("Interest", 600.0F),

            )

        transactionAdapter = ExpenseTransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }
    }
}