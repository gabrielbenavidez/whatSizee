package com.example.whatsizeee;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    List<SizeEntry> sizeEntries;
    MainActivity context;

    public RecycleViewAdapter(List<SizeEntry> sizeEntries, MainActivity context) {
        this.sizeEntries = sizeEntries;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_entry, parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.entryName.setText(sizeEntries.get(position).getName());
        if(sizeEntries.get(position).getImageURI() == null){
            holder.entryImage.setImageResource(R.drawable.add_photo_icon);
        }
        else{
            Glide.with(this.context).load(sizeEntries.get(position).getImageURI()).into(holder.entryImage);
        }
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EntryForm.class);
                intent.putExtra("id", sizeEntries.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sizeEntries.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView entryName;
        private ImageView entryImage;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            entryName = itemView.findViewById(R.id.entryName);
            entryImage = itemView.findViewById(R.id.entryImage);
            parentLayout = itemView.findViewById(R.id.oneLineEntryLayout);
        }
    }
}
