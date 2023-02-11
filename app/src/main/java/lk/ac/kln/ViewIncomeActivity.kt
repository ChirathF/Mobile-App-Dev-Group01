package lk.ac.kln

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ViewIncomeActivity : AppCompatActivity() {
    private lateinit var transactions: ArrayList<IncomeTransaction>
    private lateinit var transactionAdapter: IncomeTransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var db : AppDatabase
    private lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        updateIncomeDashboard()
        viewAddIncome()
    }

    //Show Total Income
    private fun updateIncomeDashboard(){
        val incomeValue = findViewById<TextView>(R.id.income_value)
        val incomeAmount = transactions.filter { it.amount>0 }.map {it.amount}.sum()

        incomeValue.text = "LKR %.2f".format(incomeAmount)
    }

    //Add button action
    private fun viewAddIncome(){
        val addButton = findViewById<FloatingActionButton>(R.id.income_add_btn)
        addButton.setOnClickListener{
            val intent = Intent(this, AddIncome::class.java)
            startActivity(intent)
        }
    }

}