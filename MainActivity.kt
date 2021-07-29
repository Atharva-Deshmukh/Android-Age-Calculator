package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //button ki functionality daalenge yaha pe
        val button=findViewById<Button>(R.id.button)
        button.setOnClickListener {view->
          //jaise c me printf hota hai, yaha pe toast hota hai, length long yaani kaafi der tak
            //dikhaate raho
            Toast.makeText(this,"Choose a date",Toast.LENGTH_LONG).show()

            //yaha function ko call karenge. lekin ab jo calendar aayga vo ek pop up ki tarah hoga
            //usko sambhaalne ke liye ek view laana padega. upar it: view ki jagah view likha

            //yaha view pass kiya
            printAge(view)
        }
    }

    //abhi calndar dikhaane ke liye ek function banaayenge

    //yaha view recieve kiya
    private fun printAge(view:View){

        //ab aaj ki date laane ke liye calendar class ko bulaao
        var myCalendar=Calendar.getInstance()

        //aaj ka current year nikala aur ye calendar ki initial values hogi
        var year=myCalendar.get(Calendar.YEAR)
        var month=myCalendar.get(Calendar.MONTH)
        var day=myCalendar.get(Calendar.DAY_OF_MONTH)

        //baad wale jo hai uko abhi upar nikala hai

        //neeche usko ache se likha hai kyuki horizontally lamba jaa raha tha
        //DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,year,month,day->},year,month,day).show()
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener
            {
                //ye values user deega
                    view,
                    year,
                    month,
                    day->

                //ab yaha age nikaalenge

                //month yaha 0 se 11 hai isiliye 1+  && maine day pehle and year baad ,e likha hai
                val selectedDate="$day/${month+1}/$year"
                var textView3=findViewById<TextView>(R.id.textView3)
                textView3.text=selectedDate

               //                 age calculation
                var textView4=findViewById<TextView>(R.id.textView4)

                //dob object bnaya calendar type ka to take date given by user
                var dob=Calendar.getInstance()
                dob.set(year,month,day)

                //current year mese DOB year subtract karo
                var age=myCalendar.get(Calendar.YEAR)-dob.get(Calendar.YEAR)

                //DOB can never be equal to or greater than today's date
                if(dob.get(Calendar.DAY_OF_YEAR)>= myCalendar.get(Calendar.DAY_OF_YEAR))
                {
                    textView4.text="DOB can never be equal to or greater than today's date"
                }
                else
                {
                    //ab agar usko kisi age kaa hone ko kuch din baaki hai, usko deal karenge
                    //day of year us year ka day de raha hai

                    //ex: 21(only year wise)    20/03/2021         24/03/2001
                    //aisa hua to vo abhi 21 ka nhi 20 ka hai na isiliye ek minus kiya
                    if(myCalendar.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
                    {
                        age--;
                    }

                    textView4.text="Your current age is $age years."
                }

            //ye values initialize ki thi aaj ki date pe
            },year,month,day).show()

    }
}