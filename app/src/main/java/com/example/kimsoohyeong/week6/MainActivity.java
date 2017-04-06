package com.example.kimsoohyeong.week6;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv1;
    TextView tv;
    ArrayList<Rest> data = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<String> dataName = new ArrayList<>();
    final int _CHOOSE_MENU_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListView();
    }

    public void setListView() {
        lv1 = (ListView)findViewById(R.id.listview);
        tv = (TextView)findViewById(R.id.tv);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dataName);

        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                intent.putExtra("data", data.get(position));
                startActivity(intent);
            }
        });

        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                final int pos = position;
                dlg.setTitle("삭제확인")
                        .setMessage("선택한 맛집을 정말 삭제할거예요?")
                        .setIcon(R.drawable.potato)
                        .setPositiveButton("닫기", null)
                        .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                data.remove(pos);
                                dataName.remove(pos);
                                adapter.notifyDataSetChanged();
                                tv.setText("맛집 리스트(" + data.size() + "개)");
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, _CHOOSE_MENU_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == _CHOOSE_MENU_CODE) {
            if (resultCode == RESULT_OK) {
                Rest addNode = data.getParcelableExtra("addRest");
                this.data.add(addNode);
                dataName.add(addNode.getName());
                adapter.notifyDataSetChanged();
                tv.setText("맛집 리스트(" + this.data.size() + "개)");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
