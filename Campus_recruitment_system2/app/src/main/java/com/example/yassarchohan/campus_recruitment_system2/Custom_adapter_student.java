package com.example.yassarchohan.campus_recruitment_system2;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Yassar chohan on 1/26/2017.
 */
public class Custom_adapter_student extends ArrayAdapter<Getter_methods> {

    public Custom_adapter_student(Context context, int resource, List<Getter_methods> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.activity_custom_view,parent,false);
        }

        TextView txt = (TextView) convertView.findViewById(R.id.getname);
        TextView txt2 = (TextView) convertView.findViewById(R.id.getmsg);
        TextView txt3 = (TextView) convertView.findViewById(R.id.getvalue);




        Getter_methods message = getItem(position);

        txt.setText(message.getCom_name());
        txt2.setText(message.getDesige());
        txt3.setText(message.getPostedby());


        return convertView;
    }
}
