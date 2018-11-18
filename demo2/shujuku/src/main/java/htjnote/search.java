package htjnote;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.shujuku.R;

import java.util.ArrayList;

import model.Data;
import presenter.MyAdapter;
import presenter.MyDatabase;

public class search extends AppCompatActivity {
    private EditText edit1;
    private ListView mListView;
    private Button button_search;
    ListView listView;
    LayoutInflater layoutInflater;
    ArrayList<Data> arrayList;
    MyDatabase myDatabase;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        edit1 = (EditText) findViewById(R.id.searchView);

        mListView = (ListView) findViewById(R.id.listView);
        layoutInflater = getLayoutInflater();

        myDatabase = new MyDatabase(this);
        button_search=(Button) findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                arrayList = myDatabase.search_array(edit1.getText().toString());
                MyAdapter adapter = new MyAdapter(layoutInflater,arrayList);
                mListView.setAdapter(adapter);
            }

        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //点击一下跳转到编辑页面（编辑页面与新建页面共用一个布局）
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),New_note.class);
                intent.putExtra("ids",arrayList.get(position).getIds());
                startActivity(intent);
                search.this.finish();
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {   //长按删除
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(search.this) //弹出一个对话框
                        //.setTitle("确定要删除此便签？")
                        .setMessage("确定要删除此便签？")
                        .setNegativeButton("取消",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myDatabase.toDelete(arrayList.get(position).getIds());
                                MyAdapter myAdapter = new MyAdapter(layoutInflater,arrayList);
                                mListView.setAdapter(myAdapter);
                                search.this.finish();
                                Intent intent1 = new Intent(getApplicationContext(),search.class);
                                startActivity(intent1);
                            }
                        })
                        .create()
                        .show();
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.fan_hui:
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                search.this.finish();
                break;
            default:
                break;
        }
        return  true;

    }
}
