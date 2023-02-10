package lk.ac.kln

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ViewIncomeActivity : AppCompatActivity() {

    private lateinit var transactions : ArrayList<Transaction>
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_income)

        val rvContacts = findViewById<TextView>(R.id.label) as TextView

        transactions = arrayListOf(
            Transaction(1, "income 1", 20.00, "none"),
            Transaction(2, "income 1", 20.00, "none"),
            Transaction(3, "income 1", 20.00, "none")
        )

        transactionAdapter = TransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)


//        recyclerview.apply {
//            adapter = transactionAdapter
//            layoutManager = linearLayoutManager
//        }


    }



}