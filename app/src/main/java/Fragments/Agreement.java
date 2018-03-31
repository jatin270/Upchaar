package Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import in.project.com.upchaar.PatientDash;
import in.project.com.upchaar.R;

/**
 * Created by ( Jatin Bansal ) on 30-03-2018.
 */

public class Agreement extends Fragment {

    private CheckBox checkBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.agreement_fragment,container,false);
        checkBox=v.findViewById(R.id.agreemen_checkbox);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    Intent intent=new Intent(getActivity(),PatientDash.class);
                    startActivity(intent);
                }
            }
        });

        return v;
    }
}
