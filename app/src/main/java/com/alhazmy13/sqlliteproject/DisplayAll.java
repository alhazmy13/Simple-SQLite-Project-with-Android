package com.alhazmy13.sqlliteproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class DisplayAll extends ActionBarActivity {

    ListView listView;
    StudentDataSource studentDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);
        listView=(ListView)findViewById(R.id.listView);
        studentDataSource=new StudentDataSource(getApplicationContext());
        studentDataSource.open();
        final List<Student> studentList=studentDataSource.getAllStudents();
        studentDataSource.close();
        String[] names=new String[studentList.size()];
        for(int i=0;i<studentList.size();i++){
            names[i]=studentList.get(i).getName();
        }
        ArrayAdapter<String>  adapter=new ArrayAdapter<String>(DisplayAll.this,android.R.layout.simple_list_item_1,android.R.id.text1,names);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {
                new AlertDialog.Builder(DisplayAll.this)
                        .setTitle("Select Your option")
                        .setMessage("Delete or Update")
                        .setPositiveButton("Edit",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(DisplayAll.this,EditStudent.class);
                                intent.putExtra("id",studentList.get(postion).getId());
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Delete",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                studentDataSource.open();
                                studentDataSource.deletStudent(studentList.get(postion).getId());
                                studentDataSource.close();
                                Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_all, menu);
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
