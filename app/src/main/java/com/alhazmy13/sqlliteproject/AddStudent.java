package com.alhazmy13.sqlliteproject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddStudent extends ActionBarActivity {

    EditText name,email,number;
    Button add;

    StudentDataSource studentDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        name=(EditText)findViewById(R.id.nameET);
        email=(EditText)findViewById(R.id.emailET);
        number=(EditText)findViewById(R.id.numberET);
        add=(Button)findViewById(R.id.addBT);
        studentDataSource=new StudentDataSource(getApplicationContext());

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !number.getText().toString().isEmpty()){
                    studentDataSource.open();
                    studentDataSource.createStudent(name.getText().toString(),email.getText().toString(),Integer.parseInt(number.getText().toString()));
                    studentDataSource.close();
                    Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Filed cannot be empty!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_student, menu);
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
