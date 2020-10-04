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

import com.gmrit.patientmanagement.R;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText name,did,phone,address,dept,age;
    RadioGroup gendergroup;
    String n,id,ph,add,d,a,gender;
    private FirebaseDatabase firebaseDatabase;
    public static int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);
        this.setTitle("Doctor Registration");

        name=(EditText)findViewById(R.id.name);
        did=(EditText)findViewById(R.id.did);
        gendergroup=(RadioGroup)findViewById(R.id.gender);
        phone=(EditText)findViewById(R.id.phone);
        address=(EditText)findViewById(R.id.address);
        // dept=(Spinner)findViewById(R.id.spin);
        age=(EditText)findViewById(R.id.age);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        final Spinner spinner = findViewById(R.id.spin);

        /*findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorRegistration.this,DoctorActivity.class));
            }
        });*/

        String[] department = new String[]{
                "Choose Department",
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
                /*int selectedId = gendergroup.getCheckedRadioButtonId();
                gender=(RadioButton) findViewById(selectedId);
                if(selectedId == 0) {
                    //gen = gender.getText().toString();
                }
                else {
                    Toast.makeText(DoctorRegistration.this,"Select Gender",Toast.LENGTH_LONG).show();
                }*/
                //Toast.makeText(PatientRegistration.this,gender.getText(),Toast.LENGTH_LONG).show();
                String n = name.getText().toString().trim();
                String id = did.getText().toString().trim();
                String a = age.getText().toString().trim();
                String add = address.getText().toString();
                String dept =spinner.getSelectedItem().toString();
                String ph = phone.getText().toString().trim();
                if(n.isEmpty()) {
                    name.setError("Please enter Name");
                }
                else if(id.isEmpty()) {
                    did.setError("Please fill ID");
                }
                else if(a.isEmpty() ) {
                    age.setError("Please enter Age");
                }else if (add.isEmpty()) {
                    address.setError("Please enter Address");
                }else if(ph.isEmpty()) {
                    phone.setError("Please enter Phone Number");
                } else  {
                    i++;
                    final String doc = "Doctor " + i;
                    /*firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("Doctor_Details").child(dept).child(doc);
                    DoctorDetails doctorDetails = new DoctorDetails(n,id,gen,a,add,ph,dept);
                    databaseReference.setValue(doctorDetails);*/

                   // startActivity(new Intent(getApplicationContext(),DoctorActivity.class));
                    Intent intent = new Intent(getApplicationContext(), DoctorImageSelectedActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("Name",n);
                    bundle.putString("Qualif",id);
                    bundle.putString("Gender",gender);
                    bundle.putString("age",a);
                    bundle.putString("add",add);
                    bundle.putString("dept",dept);
                    bundle.putString("id",doc);
                    bundle.putString("phone",ph);

                    intent.putExtras(bundle);

                    startActivity(intent);
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
