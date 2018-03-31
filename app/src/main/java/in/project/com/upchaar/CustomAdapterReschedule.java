package in.project.com.upchaar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Fragments.Doctor;
import Fragments.Reschedule;

/**
 * Created by bhavyagupta on 31/03/18.
 */

public class CustomAdapterReschedule extends ArrayAdapter<Reschedule> implements View.OnClickListener{

    private ArrayList<Reschedule> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView doctor;
        TextView hospital;
        TextView time;
        TextView date;
        TextView token;
    }

    public CustomAdapterReschedule(ArrayList<Reschedule> data, Context context) {
        super(context, R.layout.recommend_row_layout, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {
//
//        int position=(Integer) v.getTag();
//        Object object= getItem(position);
//        Doctor doc=(Doctor)object;
//
//        switch (v.getId())
//        {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " +doc.getName(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
//        }
    }

//    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Reschedule reschedule = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.doc_list_layout, parent, false);
            viewHolder.hospital = (TextView) convertView.findViewById(R.id.hospital);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.doctor = (TextView) convertView.findViewById(R.id.doctor);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.token = (TextView) convertView.findViewById(R.id.token);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;

        viewHolder.hospital.setText(reschedule.getHospital());
        viewHolder.doctor.setText(reschedule.getDoctor());
        viewHolder.time.setText(reschedule.getTime());
        viewHolder.date.setText(reschedule.getDate());
        viewHolder.token.setText(reschedule.getToken());

//        viewHolder.qual.setOnClickListener(this);
//        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}