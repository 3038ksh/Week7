package com.example.kimsoohyeong.week6;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv1;
    EditText e1;
    Button b4;
    CheckBox c1;
    ArrayList<Rest> data = new ArrayList<>();
    RestAdapter adapter;
    boolean isDel = false;
    final int _CHOOSE_MENU_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListView();
    }

    public void setListView() {
        lv1 = (ListView)findViewById(R.id.listview);
        e1 = (EditText)findViewById(R.id.e1);
        b4 = (Button)findViewById(R.id.b4);
        c1 = (CheckBox)findViewById(R.id.c1);

        adapter = new RestAdapter(this, data, false);

        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                intent.putExtra("data", data.get(position));
                startActivity(intent);
            }
        });

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String search = e1.getText().toString();
                if (search.length() > 0) {
                    lv1.setFilterText(search);
                } else {
                    lv1.clearTextFilter();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == R.id.b1) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivityForResult(intent, _CHOOSE_MENU_CODE);
        } else if (v.getId() == R.id.b2) {
            adapter.setNameAscSort();
        } else if (v.getId() == R.id.b3) {
            adapter.setTypeAscSort();
        } else {
            if (isDel) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("삭제확인")
                        .setMessage("선택한 맛집을 정말 삭제할거에요?")
                        .setNegativeButton("취소",null)
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = data.size() - 1; i >= 0; i--) {
                                    if (data.get(i).getIsCheck() == 1)
                                        data.remove(i);
                                }
                                adapter.notifyDataSetChanged();
                                lv1.setAdapter(adapter);
                                isDel = false;
                                b4.setText("선택");
                                adapter.setDel(false);
                                adapter.notifyDataSetChanged();
                            }
                        }).show();
            } else {
                isDel = true;
                b4.setText("삭제");
                adapter.setDel(true);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == _CHOOSE_MENU_CODE) {
            if (resultCode == RESULT_OK) {
                Rest addNode = data.getParcelableExtra("addRest");
                this.data.add(addNode);
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
