package org.example.georgesavill90.tasktimer;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] projection = { TaskContract.Columns._ID,
                                TaskContract.Columns.TASKS_NAME,
                                TaskContract.Columns.TASKS_DESCRIPTION,
                                TaskContract.Columns.TASKS_SORTORDER};
        ContentResolver contentResolver = getContentResolver();

        ContentValues values = new ContentValues();
//        values.put(TaskContract.Columns.TASKS_SORTORDER, "99");
//        values.put(TaskContract.Columns.TASKS_DESCRIPTION, "Completed");
//        String selection = TaskContract.Columns.TASKS_SORTORDER + " = " + 2;


//        values.put(TaskContract.Columns.TASKS_DESCRIPTION, "For Deletion");
//        String selection = TaskContract.Columns.TASKS_SORTORDER + " = ?";
//        String[] args = {"2"};

//        int count = contentResolver.delete(TaskContract.buildTaskUri(1), null, null);
//        Log.d(TAG, "onCreate: " + count + " record(s) deleted");
        String selection = TaskContract.Columns.TASKS_DESCRIPTION + " = ?";
        String[] args = {"For Deletion"};
        int count = contentResolver.delete(TaskContract.CONTENT_URI, selection, args);
        Log.d(TAG, "onCreate: " + count + " record(s) deleted");

//        values.put(TaskContract.Columns.TASKS_NAME, "Content Provider");
//        values.put(TaskContract.Columns.TASKS_DESCRIPTION, "Record content provider video");
//        int count = contentResolver.update(TaskContract.buildTaskUri(1), values, null, null);
//        Log.d(TAG, "onCreate: " + count + " record(s) updated");

//        values.put(TaskContract.Columns.TASKS_NAME, "New Task 1");
//        values.put(TaskContract.Columns.TASKS_DESCRIPTION, "Description 1");
//        values.put(TaskContract.Columns.TASKS_SORTORDER, 2);
//        Uri uri = contentResolver.insert(TaskContract.CONTENT_URI, values);

        Cursor cursor = contentResolver.query(TaskContract.CONTENT_URI,
                projection,
                null,
                null,
                TaskContract.Columns.TASKS_NAME);

        if(cursor != null){
            Log.d(TAG, "onCreate: number of rows: " + cursor.getCount());
            while(cursor.moveToNext()){
                for(int i=0; i<cursor.getColumnCount(); i++){
                    Log.d(TAG, "onCreate: " + cursor.getColumnName(i) + ": " + cursor.getString(i));
                }
                Log.d(TAG, "onCreate:  ====================================== ");
            }
            cursor.close();
        }
//        AppDatabase appDatabase = AppDatabase.getInstance(this);
//        final SQLiteDatabase db = appDatabase.getReadableDatabase();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
