package lk.ac.kln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddExpense : AppCompatActivity() {
    lateinit var addExpenseBtn:Button;
    private lateinit var edt_label: EditText
    private lateinit var edt_label_amount: EditText
    private lateinit var edt_label_description: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        addExpenseBtn=findViewById(R.id.addExpenseBtn);
        edt_label=findViewById(R.id.edt_label);
        edt_label_amount= findViewById<EditText>(R.id.edt_label_amount);
        edt_label_description=findViewById(R.id.edt_label_description);

        addExpenseBtn.setOnClickListener{
            val label=edt_label.text.toString()
            val amount=("-".plus(edt_label_amount.text)).toString().toDouble()
            val description=edt_label_description.text.toString()

            if (label.isEmpty()) {
                Toast.makeText(this, "Please enter a Label !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else if (description.isEmpty()) {
                Toast.makeText(this, "Please enter a Description !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                val transaction=Transaction(0,label,amount,description)
                insert(transaction)
            }
        }


    }

    private fun insert(transaction: Transaction){
        //DB connection
        val db = Room.databaseBuilder(this,AppDatabase::class.java,"transactions").build()

        GlobalScope.launch {
            db.transactionDao().insertAll(transaction)
            finish()
        }
    }
}