package lk.ac.kln

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewIncomeActivity : AppCompatActivity() {
    private lateinit var transactions: List<Transaction>
    private lateinit var transactionAdapter: ExpenseTransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var db : AppDatabase
    private lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_income)
        recyclerView = findViewById(R.id.income_recyclerview)

        transactions = arrayListOf()

        transactionAdapter = ExpenseTransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }


    }
}