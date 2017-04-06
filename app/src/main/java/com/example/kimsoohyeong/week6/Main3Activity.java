package com.example.kimsoohyeong.week6;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView txtname, etmenu1, etmenu2, etmenu3, tvTel, tvURL, tvRegdate;
    ImageView imgno;
    Rest data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        data = intent.getParcelableExtra("data");

        init();
        setData(data);
    }

    void init() {
        txtname = (TextView) findViewById(R.id.txtname);
        etmenu1 = (TextView) findViewById(R.id.etmenu1);
        etmenu2 = (TextView) findViewById(R.id.etmenu2);
        etmenu3 = (TextView) findViewById(R.id.etmenu3);
        tvTel = (TextView) findViewById(R.id.tvTel);
        tvURL = (TextView) findViewById(R.id.tvURL);
        tvRegdate = (TextView) findViewById(R.id.tvRegdate);
        imgno = (ImageView) findViewById(R.id.imgno);
    }

    void setData(Rest data) {
        imgno.setImageResource(data.getNum() == 0 ? R.drawable.chicken :
                data.getNum() == 1 ? R.drawable.pizza : R.drawable.hamburger);
        txtname.setText(data.getName());
        etmenu1.setText(data.getMenu()[0]);
        etmenu2.setText(data.getMenu()[1]);
        etmenu3.setText(data.getMenu()[2]);
        tvTel.setText(data.getCallNum());
        tvURL.setText(data.getUrl());
        tvRegdate.setText(data.getDate());
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnback) {
            finish();
        } else if (v.getId() == R.id.imageView2) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/" + data.getCallNum()));
            startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getUrl()));
            startActivity(intent);
        }
    }
}
