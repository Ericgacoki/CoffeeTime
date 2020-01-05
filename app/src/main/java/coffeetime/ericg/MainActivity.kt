package coffeetime.ericg

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Enjoy your Coffee", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        coffeeCalculator()
    }

    private fun coffeeCalculator() {

        var numberOfCups = 0 
        var totalAmount = 0
        val amountPerCup = 5

        Toast.makeText(this, " Coffee is Ready ", Toast.LENGTH_SHORT).show()

        btnReset.setOnClickListener {
            if (numberOfCups >= 1){

                numberOfCups = 0
            totalAmount = numberOfCups * amountPerCup

            cups.text = numberOfCups.toString()
            amount.text = totalAmount.toString()

            Toast.makeText(this, " Reset to 0 ", Toast.LENGTH_SHORT).show()
        }
            else{
                Toast.makeText(this, "Unnecessary reset !", Toast.LENGTH_SHORT).show()
            }
        }

        btnPlus.setOnClickListener {
            numberOfCups += 1

            totalAmount = numberOfCups * amountPerCup

            cups.text = numberOfCups.toString()
            amount.text = totalAmount.toString()
        }

        btnMinus.setOnClickListener {
            if (numberOfCups >= 1) {
                numberOfCups -= 1
                totalAmount = numberOfCups * amountPerCup

                cups.text = numberOfCups.toString()
                amount.text = totalAmount.toString()
            }
            else{
                Toast.makeText(this, " No cups yet !", Toast.LENGTH_SHORT).show()
            }
        }


       btnBuy.setOnClickListener {
           if(numberOfCups !=0){

            val buyAlert = AlertDialog.Builder(this)

            buyAlert.setTitle("Do you wish to  buy: $numberOfCups cup(s) \n                   for Kshs: $totalAmount ?")
            buyAlert.setPositiveButton("YES") { _, _ ->
                // Proceed to Buy
                Toast.makeText(this, " Action coming soon ", Toast.LENGTH_SHORT).show()

            }
            buyAlert.setNegativeButton("NO") { _, _ ->
                Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show()
            }
            val alertDialog: AlertDialog = buyAlert.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
           else{
               Toast.makeText(this, "You can't buy 0 cups !", Toast.LENGTH_SHORT).show()
           }
       }
    }
}
