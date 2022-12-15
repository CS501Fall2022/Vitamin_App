package com.example.vitamin_app;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.provider.CalendarContract;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarDialogFragment extends DialogFragment{

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mListener;

    int day, month, year, hour, minute;

    public static CalendarDialogFragment newInstance(String task) {
        Bundle args = new Bundle();
        CalendarDialogFragment fragment = new CalendarDialogFragment();
        // Assign bundle
        args.putString("task", task);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.dialog_fragment_calendar, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button datePicker = (Button) view.findViewById(R.id.dataPickerBtn);
        String task = getArguments().getString("task");

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                // Create Date picker - in built functionality
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // When user has chosen Date
                Toast.makeText(getContext(), "DateSet",Toast.LENGTH_LONG).show();
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), mListener, hour, minute, DateFormat.is24HourFormat(getContext()));
                timePickerDialog.show();
            }
        };

        mListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                // When user has chosen time
                // Forward user to google calendar
                Toast.makeText(getContext(), "TimeSet "+hour,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE, task);

                // Setting dates
                GregorianCalendar gc = new GregorianCalendar();
                gc.set(year,month,day, hour, minute);
                gc.add(Calendar.DATE, 0);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        gc.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        gc.getTimeInMillis());

                startActivity(intent);
            }
        };
    }

}