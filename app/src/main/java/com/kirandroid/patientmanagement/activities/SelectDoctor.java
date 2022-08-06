package com.kirandroid.patientmanagement.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirandroid.patientmanagement.R;

import java.util.Objects;

public class SelectDoctor extends AppCompatActivity {

    TextView name,degree,phone;
    ImageView docimage;
    Button select;
    String nam,qual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_doctor);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Select Doctor");
        name=(TextView)findViewById(R.id.docname);
        degree=(TextView)findViewById(R.id.degree);
        phone=(TextView)findViewById(R.id.phone);
        docimage=(ImageView)findViewById(R.id.image);

        select=(Button)findViewById(R.id.select);

        nam = name.getText().toString().trim();
        qual=degree.getText().toString().trim();

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectDoctor.this, PatientActivity.class);
                Bundle b = new Bundle();
                b.putString("Name",nam);
                b.putString("Qual",qual);
                i.putExtras(b);
                startActivity(i);
                //startActivity(new Intent(getApplicationContext(),PatientActivity.class));

            }
        });

        /*Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.);
        docimage.setImageBitmap(icon);*/
    }
}
