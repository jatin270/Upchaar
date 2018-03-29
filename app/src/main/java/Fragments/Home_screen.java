package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import in.project.com.upchaar.MainActivity;
import in.project.com.upchaar.R;

public class Home_screen extends Fragment {

    private Button login_button;
    private Button signup_button;
    public MainActivity mainActivity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_home_screen,container,false);
        mainActivity= (MainActivity) getActivity();
        login_button= (Button) v.findViewById(R.id.login_button);
        signup_button= (Button) v.findViewById(R.id.signup_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.display_login_fragment();
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.chose_role_fragment();
            }
        });

        return v;
    }
}
