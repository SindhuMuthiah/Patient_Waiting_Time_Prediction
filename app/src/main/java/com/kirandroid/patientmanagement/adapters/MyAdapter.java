package com.kirandroid.patientmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kirandroid.patientmanagement.activities.PatientActivity;
import com.kirandroid.patientmanagement.R;
import com.kirandroid.patientmanagement.modals.DoctorDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<DoctorDetails> doctordetails;
    int token=0;

    public MyAdapter(Context c , ArrayList<DoctorDetails> f) {
        context = c;
        doctordetails = f;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_select_doctor,parent,false));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;

        public ViewHolder(View v)
        {
            super(v);
            image =(ImageView)v.findViewById(R.id.image);
        }

        public ImageView getImage(){ return this.image;}
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        token++;
        //final Image image = images.get(position);
        holder.nam.setText(doctordetails.get(position).getName());
        holder.degre.setText(doctordetails.get(position).getQualification());
        holder.phon.setText(doctordetails.get(position).getPhone());
        final String url = doctordetails.get(position).getUrl();

        final String nam = doctordetails.get(position).getName();
        final String degree = doctordetails.get(position).getQualification();
        Picasso.get().load(url).into(holder.i);

       holder.sel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//               final Intent intent = new Intent(context,PatientActivity.class);
//               context.startActivity(intent);
               Intent i = new Intent(context, PatientActivity.class);
               Bundle b = new Bundle();
               b.putString("Name",nam);
               b.putString("Qual",degree);
               b.putInt("Token",token);
               b.putString("Url",url);
               i.putExtras(b);
               context.startActivity(i);
           }
       });
    //holder.image.setImageDrawable(doctordetails.get(position).getUrl());
        //Glide.with(holder.itemView.getContext()).load(new File(.getPath())).into(holder.image);
    }



    @Override
    public int getItemCount() {
        return doctordetails.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nam,degre,phon;
        ImageView i;
        LinearLayout linearLayout;
        Button sel;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nam=(TextView)itemView.findViewById(R.id.docname);
            degre=(TextView)itemView.findViewById(R.id.degree);
            phon=(TextView)itemView.findViewById(R.id.phone);
            i=(ImageView)itemView.findViewById(R.id.image);
            sel = (Button) itemView.findViewById(R.id.select);
        }
    }

    public void filterList(ArrayList<DoctorDetails> filteredList) {
        doctordetails = filteredList;
        notifyDataSetChanged();
    }
}
