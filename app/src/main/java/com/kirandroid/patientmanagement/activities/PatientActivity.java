package com.kirandroid.patientmanagement.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kirandroid.patientmanagement.R;
import com.kirandroid.patientmanagement.adapters.MyAdapter;
import com.kirandroid.patientmanagement.modals.DoctorDetails;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Objects;

public class PatientActivity extends AppCompatActivity {

    ImageView docimage;
    TextView docname,degree,tokenno,time;
    DatabaseReference databaseReference;
    String doc,deg,photo,number;
    RecyclerView recyclerView;
    ListView listView;
    ArrayList<DoctorDetails> list;
    MyAdapter myAdapter;

    //public int token=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        docimage=(ImageView)findViewById(R.id.doctor);
        docname=(TextView)findViewById(R.id.docname);
        degree=(TextView)findViewById(R.id.degree);
        tokenno=(TextView)findViewById(R.id.tokennumber);
        time=(TextView)findViewById(R.id.time);


        Objects.requireNonNull(getSupportActionBar()).setTitle("E-Token Confirmation");
        Bundle b= getIntent().getExtras();

        Toast.makeText(this, "Doctor Allocated Successfully", Toast.LENGTH_SHORT).show();
        assert b != null;
//        String prob = bundle.getString("dept");
        int token = b.getInt("Token");
        String name = b.getString("Name");
        String qual = b.getString("Qual");
        String url = b.getString("Url");
        String ph = b.getString("phone")
;
        String tok = Integer.toString(token);
       String msg = "Dear "+",\nWelcome to ABC Hospitals,\nYou are assigned to "+name+",\n\nAverage Waiting Time is 15min";

        docname.setText(name);
        degree.setText(qual);
        tokenno.setText(tok);
        Glide.with(getApplicationContext()).load(url).into(docimage);

        time.setText("30 min.*");
        /*databaseReference= FirebaseDatabase.getInstance().getReference().child("Doctor_Details").child(prob);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    DoctorDetails d = dataSnapshot1.getValue(DoctorDetails.class);
                    doc = d.getName();
                    deg = d.getQualification();
                    photo = d.getUrl();*/
                    //ImageView img = d.getUrl();
                }
                /*myAdapter = new MyAdapter(PatientActivity.this,list);
                recyclerView.setAdapter(myAdapter);*/

                //Intent i = new Intent(PatientActivity.this,PatientActivity.class);
                //PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,i,0);
                /*String msg = "Dear "+name+",\nWelcome to ABC Hospitals,\nYou are assigned to "+doc+",\n\n0Average Waiting Time is 15min";
                //String m = msg + "\nAverage Waiting Time is 10min* \n\nYou will be Called after 15min*";
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phone,null,msg,null,null);
                docname.setText(doc);
                degree.setText(deg);
                Glide.with(getApplicationContext()).load(photo).into(docimage);*/


           // }

            /*@Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


//        //docimage.setImageBitmap(img);
//        docname.setText(doc);
//        degree.setText(deg);

    /*private void filter(String text) {
        ArrayList<DoctorDetails> filteredlist = new ArrayList<>();

        for(DoctorDetails item : list) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }

        myAdapter.filterList(filteredlist);
    }*/

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
