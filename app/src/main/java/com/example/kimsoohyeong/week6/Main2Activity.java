package com.example.kimsoohyeong.week6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    EditText etname, ettel, etmenu1, etmenu2, etmenu3, etaddr;
    RadioButton radio1, radio2, radio3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();
    }

    void init() {
        etname = (EditText)findViewById(R.id.etname);
        ettel = (EditText)findViewById(R.id.ettel);
        etmenu1 = (EditText)findViewById(R.id.etmenu1);
        etmenu2 = (EditText)findViewById(R.id.etmenu2);
        etmenu3 = (EditText)findViewById(R.id.etmenu3);
        etaddr = (EditText)findViewById(R.id.etaddr);
        radio1 = (RadioButton)findViewById(R.id.radio1);
        radio2 = (RadioButton)findViewById(R.id.radio2);
        radio3 = (RadioButton)findViewById(R.id.radio3);
    }

    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.btnCancel) {
            setResult(RESULT_CANCELED, intent);
        } else {
            String menu[] = new String[3];
            menu[0] = etmenu1.getText().toString();
            menu[1] = etmenu2.getText().toString();
            menu[2] = etmenu3.getText().toString();
            SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd");
            String date = time.format(new Date());
            Rest addNode = new Rest(etname.getText().toString(), ettel.getText().toString(),
                    menu, etaddr.getText().toString(), date, radio1.isChecked() ? 0 : radio2.isChecked() ? 1 : 2);
            intent.putExtra("addRest", addNode);
            setResult(RESULT_OK, intent);
        }
        finish();
    }
}
