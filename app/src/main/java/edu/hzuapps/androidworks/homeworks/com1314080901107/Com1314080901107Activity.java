package com.example.chensongkui.Dict


import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Com1314080901107Activity extends AppCompatActivity  {
    MyDatabaseHelper dbHelper;
    Button insert = null;
    Button search = null;
    Button delete = null;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // ����MyDatabaseHelper����ָ�����ݿ�汾Ϊ1���˴�ʹ�����·������
        // ���ݿ��ļ��Զ��ᱣ���ڳ���������ļ��е�databasesĿ¼��
        dbHelper = new MyDatabaseHelper(this, "myDict.db3", 1);
        insert = (Button) findViewById(R.id.insert);
        search = (Button) findViewById(R.id.search);
        delete = (Button) findViewById(R.id.delete);
        insert.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View source) {
                // ��ȡ�û�����
                String word = ((EditText) findViewById(R.id.word))
                        .getText().toString();
                String detail = ((EditText) findViewById(R.id.detail))
                        .getText().toString();
                // �������ʼ�¼
                insertData(dbHelper.getReadableDatabase(), word, detail);
                // ��ʾ��ʾ��Ϣ
                Toast.makeText(MainActivity.this, "������ʳɹ���"
                        , Toast.LENGTH_LONG).show();
            }
        });
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View source) {
                // ��ȡ�û�����
                String key = ((EditText) findViewById(R.id.key)).getText()
                        .toString();
                // ִ�в�ѯ
                Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                        "select * from dict where word like ? or detail like ?",
                        new String[]{"%" + key + "%", "%" + key + "%"});
                // ����һ��Bundle����
                Bundle data = new Bundle();
                data.putSerializable("data", converCursorToList(cursor));
                // ����һ��Intent
                Intent intent = new Intent(MainActivity.this
                        , ResultActivity.class);
                intent.putExtras(data);
                // ����Activity
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(dbHelper.getReadableDatabase());
                Toast.makeText(MainActivity.this, "ɾ�����ʳɹ���"
                        , Toast.LENGTH_LONG).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }


    protected ArrayList<Map<String, String>>
    converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result =
                new ArrayList<Map<String, String>>();
        // ����Cursor�����
        while (cursor.moveToNext()) {
            // ��������е����ݴ���ArrayList��
            Map<String, String> map = new HashMap<>();
            // ȡ����ѯ��¼�е�2�С���3�е�ֵ
            map.put("word", cursor.getString(1));
            map.put("detail", cursor.getString(2));
            result.add(map);
        }
        return result;
    }

    private void insertData(SQLiteDatabase db, String word
            , String detail) {
        // ִ�в������
        db.execSQL("insert into dict values(null , ? , ?)"
                , new String[]{word, detail});
    }

    private void deleteData(SQLiteDatabase db) {
        db.execSQL("delete from dict");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // �˳�����ʱ�ر�MyDatabaseHelper���SQLiteDatabase
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

}

