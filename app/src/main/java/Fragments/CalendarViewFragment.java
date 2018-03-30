package Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import in.project.com.upchaar.R;

import static java.util.Calendar.MONDAY;

public class CalendarViewFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class timeSlots{
        int from;
        int to;
    }
    public class AppointmentDate {
        int day;
        int month;
        int year;
        ArrayList<timeSlots> times;
    };
    AppointmentDate DateSelected=new AppointmentDate();
    ArrayList<AppointmentDate> datesReceivedFromDoctor = new ArrayList<>();
    CalendarView calendarView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar_view, container, false);

        List<EventDay> events = new ArrayList<>();
        final Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.logo));
        calendarView = (CalendarView)view.findViewById(R.id.calendarView);
        calendarView.setEvents(events);
        AppointmentDate test = new AppointmentDate();
        test.month = 1;
        test.day = 13;
        test.year =2018;
        datesReceivedFromDoctor.add(test);
        CalenderInit();

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                //Log.d("date","" + eventDay.getCalendar().toString());
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

    @SuppressLint("ResourceAsColor")
    public void CalenderInit(){

        String dateString = datesReceivedFromDoctor.get(0).day + "/" +  datesReceivedFromDoctor.get(0).month + "/" + datesReceivedFromDoctor.get(0).year;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            calendarView.setDate(convertedDate);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

    }

}
