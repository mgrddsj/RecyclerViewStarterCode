package com.example.recyclerviewstudentversion;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Todo Implement methods required
//onCreateViewHolder()
//onBindViewHolder
//getItemCount
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>
{
     List<Player> plyrs;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_player_view, parent, false);

        //Im gonna send my view group to my view holder
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
    holder.photo.setImageResource(plyrs.get(position).getImageResource());
    holder.t1.setText(plyrs.get(position).getName());
    holder.t2.setText(plyrs.get(position).getAge());
        holder.t3.setText(plyrs.get(position).getMainSport());
        holder.t4.setText(plyrs.get(position).getWorth()+"");
    }

    @Override
    public int getItemCount()
    {
        return plyrs.size();
    }

    // Todo implement ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView photo;
        public TextView t1;
        public TextView t2;
        public TextView t3;
        public TextView t4;
        // get references to each of the views in the single_item.xml
        // Todo implement constructor
        private MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            photo=itemView.findViewById(R.id.imageView);
          t1=itemView.findViewById(R.id.textView1);
            t2=itemView.findViewById(R.id.textView2);
            t3=itemView.findViewById(R.id.textView3);
            t4=itemView.findViewById(R.id.textView4);
        }
    }

    // Todo Create the MyRecyclerAdapter class initializer and put the list into a variable.
    public MyRecyclerAdapter(List list)
    {
  plyrs=list;
    }

}
