package lk.ac.kln

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room


class ViewIncomeActivity : AppCompatActivity() {

    private lateinit var deletedTransaction: Transaction
    private lateinit var transactions : List<Transaction>
//private lateinit var transactions : ArrayList<Transaction>
    private lateinit var oldTransactions : List<Transaction>
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var db : AppDatabase
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_income)
        recyclerView = findViewById(R.id.recyclerview)

        transactions = arrayListOf()

        //testing recycler view
//        transactions = arrayListOf(
//            Transaction(1, "income 1",20.00,"none"),
//            Transaction(2, "income 1",20.00,"none"),
//            Transaction(3, "income 1",20.00,"none"),
//            Transaction(4, "income 1",20.00,"none")
//
//        )

        transactionAdapter = TransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)

    //connecting with db table
        db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transactions").build()

        recyclerView.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }

    }
}