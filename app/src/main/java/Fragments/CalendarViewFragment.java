package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import in.project.com.upchaar.R;
public class CalendarViewFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class AppointmentDate {
        int day;
        int month;
        int year;
    };
    AppointmentDate DateSelected;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar_view, container, false);

        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.logo));

        CalendarView calendarView = (CalendarView)view.findViewById(R.id.calendarView);
        calendarView.setEvents(events);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                Log.d("date","" + eventDay.getCalendar().toString());
                DateSelected.day = eventDay.getCalendar().DAY_OF_MONTH;
                DateSelected.month = eventDay.getCalendar().MONTH;
                DateSelected.year = eventDay.getCalendar().YEAR;
            }
        });
//        OnSelectDateListener listener = new OnSelectDateListener() {
//            @Override
//            public void onSelect(List<Calendar> calendars) {
//                Log.d("date","" + calendars.toString());
//            }
//        };
//
//        DatePickerBuilder builder = new DatePickerBuilder(this.getActivity(), listener)
//                .pickerType(CalendarView.ONE_DAY_PICKER);
//        DatePicker datePicker = builder.build();
//        datePicker.show();



        return view;
    }

}
