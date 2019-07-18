package com.example.todoappwithfirebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private Context context;
    List<Nodes> nodesList;
    DatabaseReference mDatabase;
    private String data[];
    Intent i;
    public CourseAdapter(List<Nodes> nodesList,Context context) {
        this.context = context;
        this.nodesList = nodesList;

    }
    @NonNull
    @Override

    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item,parent,false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Nodes data = nodesList.get(position);
        holder.tittle1.setText(data.getTitle());
        holder.description1.setText(data.getDescription());
    }

    @Override
    public int getItemCount() {
        return nodesList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{

        TextView tittle1,description1;
        ImageView edit,delete;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle1 = itemView.findViewById(R.id.tittle);
            description1 = itemView.findViewById(R.id.description);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Nodes nodes = nodesList.get(getAdapterPosition());
                    i = new Intent(context,EditActivitty.class);
                    i.putExtra("id",nodes.id);
                    i.putExtra("title",nodes.title);
                    i.putExtra("description",nodes.description);
                    context.startActivity(i);
                }
            });
//            delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mDatabase.child("Notes").child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            Toast.makeText(HomeActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
//                            context.startActivity(new Intent(HomeActivity.this, HomeActivity.class));
//                        }
//                    });
//                }
//            });
        }
    }

}
