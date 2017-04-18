package com.example.hp.sqlitedatabasedemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtName, edtPassword;
    private Button btnSave, btnView, btnDelete, btnUpdate;
    private MyDatabase myDatabase;
    private SQLiteDatabase db;


    private String name, password;

    private SingleRow singlerow;
    private ArrayList<SingleRow> singleRowArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singleRowArrayList = new ArrayList<SingleRow>();

        edtName = (EditText) findViewById(R.id.edtName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnView = (Button) findViewById(R.id.btnView);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);


        //Registering Buttons for click event
        btnSave.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

        myDatabase = new MyDatabase(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                Toast.makeText(MainActivity.this, "Save Button Clicked", Toast.LENGTH_LONG).show();
                name = edtName.getText().toString().trim();
                password = edtPassword.getText().toString().trim();

                db = myDatabase.getWritableDatabase();


                ContentValues cv = new ContentValues();
                cv.put(myDatabase.NAME, name);
                cv.put(myDatabase.PASSWORD, password);


                long id = db.insert(MyDatabase.TABLE_NAME, null, cv);
                if (id < 0) {
                    Toast.makeText(MainActivity.this, "Insertion unsuccessful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Insertion successful", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    edtPassword.setText("");
                }
                break;

            case R.id.btnView:
                Toast.makeText(MainActivity.this, "View clicked", Toast.LENGTH_LONG).show();
                db = myDatabase.getWritableDatabase();
                String[] columns = {myDatabase.UID, myDatabase.NAME, myDatabase.PASSWORD};

                Cursor cursor = db.query(myDatabase.TABLE_NAME, columns, null, null, null, null, null);
                //StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    int index1 = cursor.getColumnIndex(myDatabase.UID);
                    int index2 = cursor.getColumnIndex(myDatabase.NAME);
                    int index3 = cursor.getColumnIndex(myDatabase.PASSWORD);

                    String uid = cursor.getString(index1);
                    String name = cursor.getString(index2);
                    String password = cursor.getString(index3);

                    singlerow = new SingleRow(uid, name, password);
                    singleRowArrayList.add(singlerow);
                    //    buffer.append(uid+""+name+""+password);

                }

                Intent intent = new Intent(MainActivity.this, list.class);
                intent.putParcelableArrayListExtra("key", singleRowArrayList);
                startActivity(intent);
                break;

            case R.id.btnDelete:
                Toast.makeText(MainActivity.this, "Delete clicked", Toast.LENGTH_LONG).show();
                db = myDatabase.getWritableDatabase();
                String whereClause1 = MyDatabase.NAME + "=?";
                String[] whereArgs1 = {"jaskaran"};

                int d = db.delete(MyDatabase.TABLE_NAME, whereClause1, whereArgs1);

                Toast.makeText(MainActivity.this, "No. of rows deleted = " + d, Toast.LENGTH_LONG).show();
                break;

            case R.id.btnUpdate:
                Toast.makeText(MainActivity.this, "Update clicked", Toast.LENGTH_LONG).show();
                db = myDatabase.getWritableDatabase();
                ContentValues cv1 = new ContentValues();
                cv1.put(MyDatabase.NAME, "jaskaranmeet");
                String whereClause = MyDatabase.NAME + "=?";
                String[] whereArgs = {"jaskaran"};
                int u = db.update(MyDatabase.TABLE_NAME, cv1, whereClause, whereArgs);
                Toast.makeText(MainActivity.this, "No. of rows updated = " + u, Toast.LENGTH_LONG).show();


        }

    }
}
