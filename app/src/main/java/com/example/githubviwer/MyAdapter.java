package com.example.githubviwer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

import model.User;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<User> users;
    public MyAdapter(Context ct, List<User> users) {
        context = ct;
        this.users = users;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtName.setText(users.get(position).getName());
        holder.txtLogin.setText(users.get(position).getLogin());
        holder.txtId.setText(""+users.get(position).getId());

        Picasso.get().load(users.get(position).getAvatar_url()).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        TextView txtLogin;
        TextView txtId;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtLogin = itemView.findViewById(R.id.txtLogin);
            txtId = itemView.findViewById(R.id.txtId);
            img = itemView.findViewById(R.id.imgAvatar);
        }
    }
}
