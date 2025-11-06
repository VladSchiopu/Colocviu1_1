package ro.pub.cs.systems.eim.colocviu1_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import ro.pub.cs.systems.eim.colocviu1_1.Constants


class Colocviu1_1MainActivity : AppCompatActivity() {

    private lateinit var concat_text: EditText

    private lateinit var nr_clicks: EditText
    private var text_exists = 0

    private var click_number = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_colocviu1_1_main)

        concat_text = findViewById(R.id.concat_text)
        concat_text.setText("")

        nr_clicks = findViewById(R.id.nr_clicks)
        nr_clicks.setText("0")

        click_number = 0


        if (savedInstanceState != null) {
            nr_clicks.setText(savedInstanceState.getString(Constants.NR_CLICKS, "0"))
            click_number = savedInstanceState.getInt("click_number", 0)
        } else {
            nr_clicks.setText("0")
            click_number = 0
        }



        val North = findViewById<Button>(R.id.north)

        North.setOnClickListener {
            if(text_exists == 0)
            {
                concat_text.setText("North")
                text_exists = 1

                click_number++
                nr_clicks.setText(click_number.toString())
            }
            else
            {
                var text1 = concat_text.text
                text1.append(", North")
                concat_text.setText(text1)

                click_number++
                nr_clicks.setText(click_number.toString())
            }

            startServiceIfConditionIsMet(concat_text.text.toString())
        }

        val South = findViewById<Button>(R.id.south)

        South.setOnClickListener {
            if(text_exists == 0)
            {
                concat_text.setText("South")
                text_exists = 1

                click_number++
                nr_clicks.setText(click_number.toString())
            }
            else
            {
                var text1 = concat_text.text
                text1.append(", South")
                concat_text.setText(text1)

                click_number++
                nr_clicks.setText(click_number.toString())
            }
            startServiceIfConditionIsMet(concat_text.text.toString())
        }


        val East = findViewById<Button>(R.id.east)

        East.setOnClickListener {
            if(text_exists == 0)
            {
                concat_text.setText("East")
                text_exists = 1

                click_number++
                nr_clicks.setText(click_number.toString())
            }
            else
            {
                var text1 = concat_text.text
                text1.append(", East")
                concat_text.setText(text1)

                click_number++
                nr_clicks.setText(click_number.toString())
            }
            startServiceIfConditionIsMet(concat_text.text.toString())
        }


        val West = findViewById<Button>(R.id.west)

        West.setOnClickListener {
            if(text_exists == 0)
            {
                concat_text.setText("West")
                text_exists = 1

                click_number++
                nr_clicks.setText(click_number.toString())
            }
            else
            {
                var text1 = concat_text.text
                text1.append(", West")
                concat_text.setText(text1)

                click_number++
                nr_clicks.setText(click_number.toString())
            }
            startServiceIfConditionIsMet(concat_text.text.toString())
        }

        val activityResultsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "The activity returned with result REGISTER", Toast.LENGTH_LONG).show()
            }
            else if (result.resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "The activity returned with result CANCELED", Toast.LENGTH_LONG).show()
            }
        }

        val navigateToSecondaryActivityButton = findViewById<Button>(R.id.navigate)

        navigateToSecondaryActivityButton.setOnClickListener {
            val intent = Intent(this, Colocviu1_1SecondaryActivity::class.java)
            intent.putExtra(Constants.TEXT_COORD, concat_text.text.toString())
            activityResultsLauncher.launch(intent)
        }



    }

    private fun startServiceIfConditionIsMet(text4: String) {
        val intent = Intent(applicationContext, Colocviu1_1Service::class.java).apply {
            putExtra(Constants.TEXT_COORD, text4.toString())
        }
        applicationContext.startService(intent)

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(Constants.NR_CLICKS, nr_clicks.text.toString())
        outState.putInt("click_number", click_number)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey(Constants.NR_CLICKS)) {
            nr_clicks.setText(savedInstanceState.getString(Constants.NR_CLICKS))
        }
    }
}