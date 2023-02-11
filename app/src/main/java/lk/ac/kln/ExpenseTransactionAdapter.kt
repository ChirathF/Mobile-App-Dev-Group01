package lk.ac.kln

import android.content.Context
import android.view.LayoutInflater
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ExpenseTransactionAdapter (private val transactions: ArrayList<ExpenseTransaction>):
    RecyclerView.Adapter<ExpenseTransactionAdapter.ExpenseTransactionHolder>() {

    class ExpenseTransactionHolder(view: View): RecyclerView.ViewHolder(view){
        val label: TextView = view.findViewById(R.id.label)
        val amount: TextView = view.findViewById(R.id.amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseTransactionHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.expense_transatction_layout, parent, false)
        return  ExpenseTransactionHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseTransactionHolder, position: Int) {
        val transaction = transactions[position]
        val context: Context = holder.amount.context

        if(transaction.amount >= 0){
            holder.amount.text = "+ LKR%.2f".format(transaction.amount)
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.purple_200))
        }else{
            holder.amount.text = "- LKR%.2f".format(Math.abs(transaction.amount))
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
        holder.label.text = transaction.label

    }

    override fun getItemCount(): Int {
        return transactions.size
    }

}
