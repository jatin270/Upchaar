package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import in.project.com.upchaar.MainActivity;
import in.project.com.upchaar.R;

public class SignupOptions extends DialogFragment {

    private Button spatientbutton;
    private Button sdoctorbutton;
    private Button shospitalbutton;
    public MainActivity mainActivity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_signup_options,container,false);
        spatientbutton= (Button) v.findViewById(R.id.spatient_button);
        sdoctorbutton= (Button) v.findViewById(R.id.sdoctor_button);
        shospitalbutton= (Button) v.findViewById(R.id.shospital_button);
        mainActivity= (MainActivity) getActivity();

        spatientbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.display_signup_patient();
            }
        });

        return  v;
    }
}
