package lk.ac.kln

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddIncome : AppCompatActivity() {
    lateinit var addIncomeBtn: Button;
    private lateinit var edt_label: EditText
    private lateinit var edt_label_amount: EditText
    private lateinit var edt_label_description: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)

        addIncomeBtn=findViewById(R.id.addIncomeBtn);
        edt_label=findViewById(R.id.edt_label);
        edt_label_amount=findViewById(R.id.edt_label_amount);
        edt_label_description=findViewById(R.id.edt_label_description);

        addIncomeBtn.setOnClickListener{
            val label=edt_label.text.toString()
            val amount=edt_label_amount.text.toString()
            val description=edt_label_description.text.toString()

            if (label.isEmpty()) {
                Toast.makeText(this, "Please enter a Label !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (amount.isEmpty()) {
                Toast.makeText(this, "Please enter a Income !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (description.isEmpty()) {
                Toast.makeText(this, "Please enter a Description !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
}