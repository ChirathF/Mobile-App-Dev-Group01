package lk.ac.kln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

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
        edt_label_amount=findViewById(R.id.edt_label_amount);
        edt_label_description=findViewById(R.id.edt_label_description);

        addExpenseBtn.setOnClickListener{
            val label=edt_label.text.toString()
            val amount=edt_label_amount.text.toString()
            val description=edt_label_description.text.toString()

            if (label.isEmpty()) {
                Toast.makeText(this, "Please enter a Label !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (amount.isEmpty()) {
                Toast.makeText(this, "Please enter a Expense !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (description.isEmpty()) {
                Toast.makeText(this, "Please enter a Description !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
}