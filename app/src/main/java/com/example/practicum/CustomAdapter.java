package com.example.practicum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    //CITE: code adapted from https://demonuts.com/sqlite-multiple-tables/

    private Context context;
    private ArrayList<Office> offices;

    public CustomAdapter(Context context, ArrayList<Office> offices) {

        this.context = context;
        this.offices = offices;
    }

    @Override
    public int getCount() {
        return offices.size();
    }

    @Override
    public Object getItem(int position) {
        return offices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.name = (TextView) convertView.findViewById(R.id.office_name);
            holder.address = (TextView) convertView.findViewById(R.id.office_address);
            holder.phone = (TextView) convertView.findViewById(R.id.office_phone_number);
            holder.type = (TextView) convertView.findViewById(R.id.office_type);
            convertView.setTag(holder);

        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.name.setText("Office Name: "+offices.get(position).getName());
        holder.type.setText("Office Type: "+offices.get(position).getType());
        holder.address.setText("Address: "+offices.get(position).getAddress());
        holder.phone.setText("Phone Number: "+offices.get(position).getPhone());

        return convertView;
    }

    public class ViewHolder {
        TextView name, address, phone, type;
    }

}
