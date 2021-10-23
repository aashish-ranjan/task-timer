package com.example.tasktimer;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.tasktimer.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        ContentResolver contentResolver = getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TasksContract.Columns.DESCRIPTION, "completed");

        String selection = TasksContract.Columns.SORT_ORDER + " >= 2";
        contentResolver.update(TasksContract.CONTENT_URI, contentValues, selection, null);

        String []  projection = {
                TasksContract.Columns._ID,
                TasksContract.Columns.NAME,
                TasksContract.Columns.DESCRIPTION,
                TasksContract.Columns.SORT_ORDER };

        Cursor cursor = contentResolver.query(TasksContract.CONTENT_URI,
                projection,
                null,
                null,
                TasksContract.Columns.SORT_ORDER);

        if (cursor != null) {
            Log.d(TAG, "onCreate: no. of rows: " + cursor.getCount());
            while (cursor.moveToNext()) {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate: ==============================");
            }
            cursor.close();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}