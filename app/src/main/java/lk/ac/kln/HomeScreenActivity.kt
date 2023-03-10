package lk.ac.kln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.content.Intent
import android.view.View
import lk.ac.kln.databinding.ActivityHomeScreenBinding


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
            val intent = Intent(this, ViewTransaction::class.java)
            startActivity(intent)
        }

        binding.incomeLayoutCard.setOnClickListener {
            val intent = Intent(this, ViewIncomeActivity::class.java)
            startActivity(intent)
        }

        binding.expenseLayoutCard.setOnClickListener {
            val intent = Intent(this, ViewExpenseActivity::class.java)
            startActivity(intent)
        }
    }

    //Fetch data from DB
    private fun fetchAll(){
        GlobalScope.launch {
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


