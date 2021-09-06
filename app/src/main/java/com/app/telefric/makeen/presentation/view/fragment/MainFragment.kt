package com.app.telefric.makeen.presentation.view.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.app.telefric.R
import com.app.telefric.base.EightFoldsDatePickerDialog
import kotlinx.android.synthetic.main.fragment_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : Fragment() {


    private var value: Int = 0
    private var pluse: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_main, container, false)
      return view;
    }



    fun openCalToGo(){

        val calendar = Calendar.getInstance()
        val datePickerDialog = activity?.let {
            EightFoldsDatePickerDialog(
                it,
                object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
                    val realmonth   = month+pluse
                        val getmonth = EightFoldsDatePickerDialog.getmonth(realmonth)

                        val date = "$dayOfMonth $getmonth , $year"
                        if (value==1){
                            date_to_return.text=date
                        }else{
                            date_to_go.text=date
                        }


                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)
            )
        }

     //arguments are   year , month , date (use for setting custom mix date)
        datePickerDialog?.setMaxDate(2090,8,25);  //arguments are   year , month , date (use for setting custom max date)
        datePickerDialog?.setTodayAsMinDate();   // sets today's date as min date
        //   datePickerDialog.setTodayAsMaxDate();    // sets today's date as max date

        datePickerDialog?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        date_to_return.setOnClickListener{
            value=1
            openCalToGo()
        }
        date_to_go.setOnClickListener{
            value=2
            openCalToGo()
        }
    }

}