package in.project.com.upchaar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Fragments.Reschedule;
import Fragments.Upcoming;

/**
 * Created by raghav on 31/3/18.
 */

public class CustomAdapterUpcoming extends ArrayAdapter<Upcoming> implements View.OnClickListener{
    private ArrayList<Upcoming> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView hospital;
        TextView time;
        TextView date;
        TextView token;
    }

    public CustomAdapterUpcoming(ArrayList<Upcoming> data, Context context) {
        super(context, R.layout.recommend_row_layout1, data);
        this.dataSet = data;
        this.mContext=context;

    }

//        Object object= getItem(position);
        @Override
        public void onClick(View v) {
//
//        int position=(Integer) v.getTag();
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
        Upcoming upcoming = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        CustomAdapterUpcoming.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new CustomAdapterUpcoming.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.recommend_row_layout1, parent, false);
            viewHolder.hospital = (TextView) convertView.findViewById(R.id.hospital);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.token = (TextView) convertView.findViewById(R.id.token);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CustomAdapterUpcoming.ViewHolder) convertView.getTag();
            result=convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;

        viewHolder.hospital.setText(upcoming.getHospital());
        viewHolder.time.setText(upcoming.getTime());
        viewHolder.date.setText(upcoming.getDate());
        viewHolder.token.setText(upcoming.getToken());

//        viewHolder.qual.setOnClickListener(this);
//        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
