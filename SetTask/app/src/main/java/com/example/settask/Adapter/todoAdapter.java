package com.example.settask.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.settask.AddNewTask;
import com.example.settask.MainActivity;
import com.example.settask.Model.todoModel;
import com.example.settask.R;
import com.example.settask.Utils.DatabaseHandler;

import java.util.List;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.ViewHolder> {
    private List<todoModel> todoList;
    private MainActivity activity;
    private DatabaseHandler db;

    public todoAdapter(DatabaseHandler db ,MainActivity activity){
        this.activity = activity;
        this.db = db;
    }

    /*
    public RecyclerView.ViewHolder oncreateViewHolder(ViewGroup parent, int viewtype){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent,false);
        return new RecyclerView.ViewHolder(itemView);
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout, parent,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        db.openDatabase();
        final todoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    db.updateStatus(item.getId(),1);
                }
                else {
                    db.updateStatus(item.getId(),0);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
    private boolean toBoolean(int n){
        return n!=0;
    }
    public void settask(List<todoModel> todoList){
        this.todoList =todoList;
        notifyDataSetChanged();
    }
    public Context getContext(){
        return activity;
    }

    public void deleteitem(int position){
        todoModel item =todoList.get(position);
        db.deleteTask(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);

    }
    public void setTask(List<todoModel> todoList){
        this.todoList=todoList;
        notifyDataSetChanged();
    }
    public void editItem(int position){
        todoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(),AddNewTask.TAG);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;
        ViewHolder(View view){
            super(view);
            task =view.findViewById(R.id.todocheck);

        }
    }
}
