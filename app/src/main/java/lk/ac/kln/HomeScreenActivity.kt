package lk.ac.kln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import lk.ac.kln.databinding.ActivityHomeScreenBinding
import lk.ac.kln.databinding.ActivityMainBinding


class HomeScreenActivity : AppCompatActivity() {
    private lateinit var transactions : List<Transaction>
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var db : AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home_screen)

        //DB connection
        db = Room.databaseBuilder(this,AppDatabase::class.java,"transactions").build()

        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater())
        var view : View = binding.getRoot()
        setContentView(view)

        binding.balanceLayoutCard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.incomeLayoutCard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.expenseLayoutCard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    //Fetch data from DB
    private fun fetchAll(){
        GlobalScope.launch {

            //test values
//            val trDao = db.transactionDao()
//            val tr= Transaction(0,"test",20.0,"des");
//            val tr1= Transaction(0,"test",-20.0,"des")
//            trDao.insertAll(tr)
//            trDao.insertAll(tr1)
            transactions = db.transactionDao().getAll()

            runOnUiThread {
                updateDashboard()
            }
        }
    }

    private fun updateDashboard(){
        val totalAmount = transactions.map { it.Amount }.sum()
        val budgetAmount = transactions.filter { it.Amount>0 }.map{it.Amount}.sum()
        val expenseAmount = totalAmount - budgetAmount

//        val balance = findViewById(R.id.balance) as TextView
//        val budget = findViewById(R.id.budget) as TextView
//        val expense = findViewById(R.id.expense) as TextView

        binding.balance.text = "LKR %.0f".format(totalAmount)
        binding.budget.text = "LKR %.0f".format(budgetAmount)
        binding.expense.text = "LKR %.0f".format(expenseAmount)
    }

    override fun onResume() {
        super.onResume()
        fetchAll()
    }

}


