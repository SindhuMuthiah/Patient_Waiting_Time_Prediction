package com.gmrit.patientmanagement.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.gmrit.patientmanagement.R;
import com.gmrit.patientmanagement.modals.PatientDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText name,pid,phone,address,dept,age;
    RadioGroup gendergroup;
    String patno,gender="";
    public int i=0,token=0;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        name=(EditText)findViewById(R.id.name);
        pid=(EditText)findViewById(R.id.pid);
        gendergroup=(RadioGroup)findViewById(R.id.gendercheck);
        phone=(EditText)findViewById(R.id.phone);
        address=(EditText)findViewById(R.id.address);
       // dept=(Spinner)findViewById(R.id.spin);
        age=(EditText)findViewById(R.id.age);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.setTitle("Patient Registration");

        // find the radiobutton by returned id
        //gender = (RadioButton) findViewById(selectedId);

        final Spinner spinner = findViewById(R.id.spin);

        String[] department = new String[]{
                "Choose Problem",
                "General Medicine",
                "Cardiology",
                "Oncology",
                "Dental Medicine",
                "Opthamology",
                "Orthopaedics"
        };
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.department, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,department
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                gendergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        RadioButton radioButton=radioGroup.findViewById(i);
                        //Toast.makeText(getApplicationContext(),radioButton.getText().toString(),Toast.LENGTH_LONG).show();
                        gender=radioButton.getText().toString();
                    }
                });


                //Toast.makeText(PatientRegistration.this,gender.getText(),Toast.LENGTH_LONG).show();
                String n = name.getText().toString().trim();
                String id = pid.getText().toString().trim();

                String a = age.getText().toString().trim();
                String add = address.getText().toString();
                String dept =spinner.getSelectedItem().toString();
                String ph = phone.getText().toString().trim();
                if(n.isEmpty()) {
                    name.setError("Please enter Name");
                }
                else if(id.isEmpty()) {
                    pid.setError("Please fill ID");
                }
                else if(a.isEmpty()) {
                    age.setError("Please enter Age");
                } if (add.isEmpty()) {
                    address.setError("Please enter Address");
                }else if(ph.isEmpty()) {
                    phone.setError("Please enter Phone Number");
                } else if(ph.length() !=10) {
                    phone.setError("Phone Number is Invalid");
                }
                else if (dept.equals("Choose Problem")) {
                    Toast.makeText(PatientRegistration.this,"Choose Problem ",Toast.LENGTH_LONG).show();
                }
                else  {

                    //Toast.makeText(PatientRegistration.this, "Gender value " + gender, Toast.LENGTH_SHORT).show();
                    token++;
                    //Toast.makeText(PatientRegistration.this, "", Toast.LENGTH_SHORT).show();
                    patno = "Patient "+i;
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference().child("Patient_Details").child(dept).child(patno);
                    PatientDetails patientDetails = new PatientDetails(n,id,gender,a,add,dept,ph);
                    databaseReference.setValue(patientDetails);
                    //passing Problem Name
                    Intent in = new Intent(getApplicationContext(), DoctorsListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("dept",dept);
                    bundle.putInt("token",token);
                    bundle.putString("pname",n);
                    bundle.putString("phone",ph);
                    in.putExtras(bundle);
                    startActivity(in);

                    /*Intent intent = new Intent(PatientRegistration.this,RecyclerView.class);
                    Bundle b = new Bundle();
                    b.putString("ddept",dept);
                    intent.putExtras(b);
                    startActivity(intent);*/
                    //startActivity(new Intent(getApplicationContext(),PatientActivity.class));
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if (id == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
