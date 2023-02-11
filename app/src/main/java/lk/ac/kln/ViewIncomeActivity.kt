package lk.ac.kln

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewIncomeActivity : AppCompatActivity() {
    private lateinit var transactions: ArrayList<IncomeTransaction>
    private lateinit var transactionAdapter: IncomeTransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var db : AppDatabase
    private lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)git
        setContentView(R.layout.activity_view_income)
        recyclerView = findViewById(R.id.income_recyclerview)

        transactions = arrayListOf(
            IncomeTransaction("Salary", 40000.0F),
            IncomeTransaction("Donation", 5000.0F),
            IncomeTransaction("Interest", 600.0F),

            )

        transactionAdapter = IncomeTransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }


    }
}