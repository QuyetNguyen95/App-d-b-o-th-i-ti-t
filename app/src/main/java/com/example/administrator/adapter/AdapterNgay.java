package com.example.administrator.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.dubaothoitiet.R;
import com.example.administrator.model.Ngay;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterNgay extends ArrayAdapter<Ngay> {
    @NonNull Activity context;
    int resource;
    @NonNull List<Ngay> objects;
    public AdapterNgay(@NonNull Activity context, int resource, @NonNull List<Ngay> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);

        TextView txtNgay = row.findViewById(R.id.txtNgay);
        TextView txtTrangThaiNgay = row.findViewById(R.id.txtTrangThaiNgay);
        TextView txtNhietDoMinMax = row.findViewById(R.id.txtNhietDoMinMax);
        ImageView imgIcon = row.findViewById(R.id.imgIcon);

        Ngay day = this.objects.get(position);
        txtNgay.setText(day.getNgay());
        txtTrangThaiNgay.setText(day.getTrangthai());
        txtNhietDoMinMax.setText(day.getNhietdoMax() + "℃ - " + day.getNhietdoMin() + "℃");

        Picasso.with(this.context).load("http://openweathermap.org/img/w/" + day.getIcon() + ".png").into(imgIcon);

        return row;
    }
}
