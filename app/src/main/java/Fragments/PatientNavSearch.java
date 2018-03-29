package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import in.project.com.upchaar.R;
public class PatientNavSearch extends Fragment {

    CheckBox DoctorCheckbox;
    EditText DoctorName;
    String DoctorRequired  = null;
    public PatientNavSearch() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_patient_nav_search, container, false);
        // data contains the list obtained by querying the database based on filters
        DoctorName = (EditText)view.findViewById(R.id.DoctorFilter);
        DoctorCheckbox = (CheckBox)view.findViewById(R.id.Doctorcheckbox);
        DoctorCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    DoctorName.setVisibility(View.VISIBLE);
                    getDoctorRequired();
                }else{
                    DoctorName.setVisibility(View.INVISIBLE);
                    DoctorRequired = null;
                }
            }
        });



        return view;
    }

    public void getDoctorRequired(){
     //   DoctorRequired = DoctorName.get
    }


}
