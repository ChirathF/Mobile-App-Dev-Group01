package lk.ac.kln

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewExpenseActivity : AppCompatActivity() {
    private lateinit var deletedTransaction: Transaction
    private lateinit var oldTransactions : List<Transaction>
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

        // swipe to remove
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteTransaction(transactions[viewHolder.adapterPosition])
            }

        }

        val swipeHelper = ItemTouchHelper(itemTouchHelper)
        swipeHelper.attachToRecyclerView(recyclerView)

        viewAddExpense()
    }

    private fun fetchAll(){
        GlobalScope.launch {
            //test values
//            val trDao = db.transactionDao()
//            val tr= Transaction(0,"test",40.0,"des");
//            val tr1= Transaction(0,"test",-20.0,"des")
//            trDao.insertAll(tr)
//            trDao.insertAll(tr1)
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
    private fun undoDelete(){
        GlobalScope.launch {
            db.transactionDao().insertAll(deletedTransaction)

            transactions = oldTransactions

            runOnUiThread {
                transactionAdapter.setData(transactions)
                updateExpenseDashboard()
            }
        }
    }
    private fun showSnackbar(){
        val view = findViewById<View>(R.id.exp_coordinator)
        val snackbar = Snackbar.make(view, "Transaction deleted!", Snackbar.LENGTH_LONG)
        snackbar.setAction("Undo"){
            undoDelete()
        }
            .setActionTextColor(ContextCompat.getColor(this, R.color.red))
            .setTextColor(ContextCompat.getColor(this, R.color.white))
            .show()
    }

    private fun deleteTransaction(transaction: Transaction){
        deletedTransaction = transaction
        oldTransactions = transactions

        GlobalScope.launch {
            db.transactionDao().delete(transaction)

            transactions = transactions.filter { it.id != transaction.id }
            runOnUiThread {
                updateExpenseDashboard()
                transactionAdapter.setData(transactions)
                showSnackbar()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        fetchAll()
    }


}