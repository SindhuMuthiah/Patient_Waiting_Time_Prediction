package com.kirandroid.patientmanagement.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kirandroid.patientmanagement.R;
import com.kirandroid.patientmanagement.modals.DoctorDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class DoctorImageSelectedActivity extends AppCompatActivity {

    ImageView doc;
    Button upload,submit;
    FirebaseStorage storage;
    StorageReference storageReference;
    TextView name,deg,dept,phone;
    private final int PICK_IMAGE_REQUEST = 22;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private Uri filePath;
    public int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
         this.setTitle("Doctor Profile");
        doc=(ImageView)findViewById(R.id.docimage);
        upload=(Button) findViewById(R.id.upload);
        submit=(Button)findViewById(R.id.submit);

        name=(TextView)findViewById(R.id.doctorname);
        deg=(TextView)findViewById(R.id.doctordegree);
        dept=(TextView)findViewById(R.id.doctordept);
        phone=(TextView)findViewById(R.id.doctorphone);

       /*i++;
        String doc = "Doctor "+i;*/
        Bundle bundle = getIntent().getExtras();

        final String nam = bundle.getString("Name");
        String degree = bundle.getString("Qualif");
        String gen = bundle.getString("Gender");
        String age = bundle.getString("age");
        String add = bundle.getString("add");
        String phon = bundle.getString("phone");
        String dep = bundle.getString("dept");


        //databaseReference= FirebaseDatabase.getInstance().getReference().child("Doctor Details");
        storage=FirebaseStorage.getInstance();
        storageReference= storage.getReference();

        /*firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Doctor_Details").child(dep).child(doc);
        DoctorDetails doctorDetails = new DoctorDetails(nam,degree,,a,add,ph,dept,);
        databaseReference.setValue(doctorDetails);*/


        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        name.setText(nam);
        deg.setText(degree);
        dept.setText(dep);
        phone.setText(phon);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
                //uploadImage();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    // Select Image method
    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                doc.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    // UploadImage method
    private void uploadImage()
    {
        if (filePath != null) {

            Bundle bundle = getIntent().getExtras();

            final String nam = bundle.getString("Name");
            final String degree = bundle.getString("Qualif");
            final String gen = bundle.getString("Gender");
            final String age = bundle.getString("age");
            final String add = bundle.getString("add");
            final String phon = bundle.getString("phone");
            final String dep = bundle.getString("dept");
            final String no = bundle.getString("id");
            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref = storageReference.child("Images/" + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                                    while (!uriTask.isSuccessful());
                                    Uri downloadUrl = uriTask.getResult();

                                    firebaseDatabase= FirebaseDatabase.getInstance();
                                    DatabaseReference databaseReference = firebaseDatabase.getReference("Doctor_Details").child(dep).child(no);
                                    DoctorDetails doctorDetails = new DoctorDetails(nam,degree,gen,age,add,phon,dep,downloadUrl.toString());
                                    databaseReference.setValue(doctorDetails);
                                    //String uploadid = databaseReference.push().getKey();
                                    //databaseReference.child("Doctor_Details").child(depart).child(no).setValue(doctorDetails);
                                    progressDialog.dismiss();
                                    Toast.makeText(DoctorImageSelectedActivity.this, "Doctor Registered Successfully!!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(DoctorImageSelectedActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        } else {
            Toast.makeText(this, "Please select Image !", Toast.LENGTH_SHORT).show();
        }
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
    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}

