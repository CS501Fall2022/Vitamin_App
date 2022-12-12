package com.example.vitamin_app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarDialogFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener mDateSetListener;

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
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

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
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE, task);

                // Setting dates
                GregorianCalendar gc = new GregorianCalendar();
                gc.set(year,month,day);
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