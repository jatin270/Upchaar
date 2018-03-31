package Fragments;

/**
 * Created by bhavyagupta on 30/03/18.
 */


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.project.com.upchaar.DoctorsListActivity;
import in.project.com.upchaar.R;

public class DocListAdapter extends RecyclerView.Adapter<DocListAdapter.MyViewHolder> {
    private List<Doctor> DocList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView doctorName, hospitalName, time, qualifications;

        public MyViewHolder(View view) {
            super(view);
            doctorName = (TextView) view.findViewById(R.id.dname);
            hospitalName = (TextView) view.findViewById(R.id.hname);
            time = (TextView) view.findViewById(R.id.time);
            qualifications = (TextView) view.findViewById(R.id.qual);
        }
    }


    public DocListAdapter(List<Doctor> DocList) {
        this.DocList = DocList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doc_list_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Doctor movie = DocList.get(position);
        holder.doctorName.setText("Abhinav");
        holder.hospitalName.setText("Medanta");
        holder.time.setText("movie");
        holder.qualifications.setText("cardiologist");
    }

    @Override
    public int getItemCount() {
        return DocList.size();
    }
}
