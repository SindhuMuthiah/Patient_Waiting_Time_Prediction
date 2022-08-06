package com.kirandroid.patientmanagement.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.kirandroid.patientmanagement.R;
import com.kirandroid.patientmanagement.adapters.MyAdapter;
import com.kirandroid.patientmanagement.modals.DoctorDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorsListActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    String doc,deg,photo,number;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    ListView listView;
    ArrayList<DoctorDetails> list;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        final ProgressDialog progressDialog
                = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        EditText editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        recyclerView = (androidx.recyclerview.widget.RecyclerView) findViewById(R.id.myrecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        list = new ArrayList<DoctorDetails>();


        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        final String prob = bundle.getString("dept");
        int token = bundle.getInt("token");
        final String name = bundle.getString("pname");
        final String phone = bundle.getString("phone");
//        Intent i = new Intent(RecyclerView.this,PatientActivity.class);
//        Bundle bundle1 = new Bundle();
//        bundle1.putString("pname",name);
//        bundle1.putString("phone",phone);
//        i.putExtras(bundle1);
//        startActivity(i);
        //String tok = Integer.toString(token);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Doctor_Details").child(prob);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    DoctorDetails d = dataSnapshot1.getValue(DoctorDetails.class);
                    list.add(d);
                }
                progressDialog.dismiss();
                myAdapter = new MyAdapter(DoctorsListActivity.this, list);
                recyclerView.setAdapter(myAdapter);
            }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
  }

    private void filter(String text) {
        ArrayList<DoctorDetails> filteredlist = new ArrayList<>();

        for(DoctorDetails item : list) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }

        myAdapter.filterList(filteredlist);
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
