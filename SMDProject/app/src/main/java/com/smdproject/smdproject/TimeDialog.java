package com.smdproject.smdproject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

@SuppressLint("ValidFragment")
public class TimeDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    TextView txtdate;
    public TimeDialog(View view){
        txtdate=(TextView) view;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int hrs = 0;
        int min = 0;
        return new TimePickerDialog(getActivity(), this, hrs, min, false);
    }

    public void onTimeSet(TimePicker view, int hrs, int min) {

        String date;
        if(min<10) date = hrs+":0"+min;
        else date = hrs+":"+min;
        if(hrs<10)date="0"+date;

        txtdate.setText(date);
    }
}
