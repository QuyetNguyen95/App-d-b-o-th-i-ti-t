package com.example.administrator.dubaothoitiet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    static EditText edtTenThanhPho;
    TextView tvTenTp, tvTenQg, tvNhietDo, tvDoAm, tvGio, tvMay, tvNgayThang, tvTrangThai;
    Button btnChon, btnTiepTheo;
    ImageView imgIcon;
    static final String DEFAUT_CITY = "Hanoi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        addEvents();
    }

    private void addEvents() {
        GetWeatherData(DEFAUT_CITY);
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thanhPho = "";
                String city = edtTenThanhPho.getText().toString();
                if (city.equals("")) {
                    thanhPho = DEFAUT_CITY;
                    GetWeatherData(thanhPho);
                } else {
                    GetWeatherData(city);
                }
            }
        });
        btnTiepTheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHienThiCacNgayKeTiep();
            }
        });
    }

    private void xuLyHienThiCacNgayKeTiep() {
        String city = edtTenThanhPho.getText().toString();
        Intent intent = new Intent(MainActivity.this, ManHinhCacNgayKeTiepActivity.class);
        intent.putExtra("city", city);
        startActivity(intent);//ham chuyen man hinh
    }

    public void GetWeatherData(final String data) {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + data + "&units=metric&appid=9956a5636758a4d3c680a758e3d684d8";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String day = jsonObject.getString("dt");
                            String name = jsonObject.getString("name");
                            tvTenTp.setText("Tên Thành Phố: " + name);

                            long lDay = Long.valueOf(day);
                            Date date = new Date(lDay * 1000L);
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy HH:mm");
                            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
                            tvNgayThang.setText(sdf.format(calendar.getTime()));


                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                            String status = jsonObjectWeather.getString("main");
                            String icon = jsonObjectWeather.getString("icon");

                            Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/" + icon + ".png").into(imgIcon);
                            tvTrangThai.setText(status);

                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String nhietdo = jsonObjectMain.getString("temp");
                            String doam = jsonObjectMain.getString("humidity");

                            //chuyển nhiệt độ thành 1 số nguyên
                            Double d = Double.valueOf(nhietdo);
                            String nhietDoChuyenSangSoNguyen = String.valueOf(d.intValue());
                            tvNhietDo.setText(nhietDoChuyenSangSoNguyen + "℃");
                            tvDoAm.setText(doam + "%");

                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                            String tocDoGio = jsonObjectWind.getString("speed");
                            tvGio.setText(tocDoGio + "m/s");

                            JSONObject jsonObjectCloud = jsonObject.getJSONObject("clouds");
                            String may = jsonObjectCloud.getString("all");
                            tvMay.setText(may);

                            JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                            String country = jsonObjectSys.getString("country");
                            tvTenQg.setText("Tên Quốc Gia: " + country);

                        } catch (JSONException e) {
                            Log.e("LOI", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }

    private void anhxa() {
        edtTenThanhPho = findViewById(R.id.edtTenTp);
        tvTenTp = findViewById(R.id.tvThanhPho);
        tvTenQg = findViewById(R.id.tvQuocGia);
        tvDoAm = findViewById(R.id.tvDoAm);
        tvGio = findViewById(R.id.tvGio);
        tvMay = findViewById(R.id.tvMay);
        tvNgayThang = findViewById(R.id.tvNgayThang);
        btnChon = findViewById(R.id.btnThanhPho);
        btnTiepTheo = findViewById(R.id.btnNgayTiepTheo);
        imgIcon = findViewById(R.id.imgThoiTiet);
        tvNhietDo = findViewById(R.id.tvNhietDo);
        tvTrangThai = findViewById(R.id.tvTrangThai);

    }
}
