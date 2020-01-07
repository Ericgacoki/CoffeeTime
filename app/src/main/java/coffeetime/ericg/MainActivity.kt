package coffeetime.ericg

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
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
        makeButtonsInvisible()


    }

    private fun coffeeCalculator() {

        var numberOfCups = 0
        var totalAmount = 0
        val amountPerCup = 5

        btnReset.setOnClickListener {

                    numberOfCups = 0
                    totalAmount = numberOfCups * amountPerCup

                    cups.text = numberOfCups.toString()
                    amount.text = totalAmount.toString()


                    // call invisibility function

                    makeButtonsInvisible()
                }

        btnPlus.setOnClickListener {
            numberOfCups += 1

            totalAmount = numberOfCups * amountPerCup

            cups.text = numberOfCups.toString()
            amount.text = totalAmount.toString()

            /* Make other Buttons visible so that the user can Minus, Reset or Buy... */

            makeButtonsVisible()

            when (numberOfCups) {
                1 -> {
                    Toast.makeText(this, " Buttons enabled !", Toast.LENGTH_SHORT).show()
                }
            }

        }

        btnMinus.setOnClickListener {
            when {
                numberOfCups > 1 -> {
                    numberOfCups -= 1
                    totalAmount = numberOfCups * amountPerCup

                    cups.text = numberOfCups.toString()
                    amount.text = totalAmount.toString()

                }
                numberOfCups == 1 -> {

                    numberOfCups -= 1
                    totalAmount = numberOfCups * amountPerCup

                    cups.text = numberOfCups.toString()
                    amount.text = totalAmount.toString()

                    /*  TODO:   ! VERY IMPORTANT ! -> call Invisibility function (here).
                    *    IF IT'S NOT DONE , THE USER CAN END UP BUYING ZERO CUPS  */

                    makeButtonsInvisible()
                }
            }
        }

        btnBuy.setOnClickListener {

                val buyAlert = AlertDialog.Builder(this)

                buyAlert.setTitle("Do you wish to  buy: $numberOfCups cup(s) \n                   for Kshs: $totalAmount ?")
                buyAlert.setPositiveButton("YES") { _, _ ->
                    // Proceed to Buy
                    //Toast.makeText(this, " Action coming soon ", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, " Just a moment...", Toast.LENGTH_SHORT).show()

                }
                buyAlert.setNegativeButton("NO") { _, _ ->
                    Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show()
                }
                val alertDialog: AlertDialog = buyAlert.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
        }
    }

    private fun makeButtonsInvisible() {

        btnReset.visibility = View.INVISIBLE
        btnMinus.isEnabled = false
        btnReset.isActivated = false

        btnMinus.visibility = View.INVISIBLE
        btnMinus.isEnabled = false
        btnReset.isActivated = false

        btnBuy.visibility = View.INVISIBLE
        btnBuy.isEnabled = false
        btnBuy.isActivated = false

        Toast.makeText(this, "3 Buttons disabled! Press '+' to enable them", Toast.LENGTH_SHORT).show()


    }

    private fun makeButtonsVisible() {
        btnReset.visibility = View.VISIBLE
        btnMinus.isEnabled = true
        btnReset.isActivated = true

        btnMinus.visibility = View.VISIBLE
        btnMinus.isEnabled = true
        btnReset.isActivated = true

        btnBuy.visibility = View.VISIBLE
        btnBuy.isEnabled = true
        btnBuy.isActivated = true
    }
}
