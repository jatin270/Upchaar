package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import in.project.com.upchaar.R;

/**
 * Created by ( Jatin Bansal ) on 31-03-2018.
 */

public class Day_Details_Fragment extends Fragment {


    TextView mt;
    TextView ms;
    TextView nt;
    TextView ns;
    TextView et;
    TextView es;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.daydetails,container,false);
        mt=v.findViewById(R.id.mt);
        ms=v.findViewById(R.id.ms);
        nt=v.findViewById(R.id.nt);
        ns=v.findViewById(R.id.ns);
        et=v.findViewById(R.id.et);
        es=v.findViewById(R.id.es);
        return v;
    }

    public void setData(int mt1,int ms1,int nt1,int ns1,int et1,int es1){
        mt.setText(String.valueOf(mt1));
        ms.setText(String.valueOf(ms1));
        nt.setText(String.valueOf(nt1));
        ns.setText(String.valueOf(ns1));
        et.setText(String.valueOf(et1));
        es.setText(String.valueOf(es1));
    }

}
