package com.example.settask;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.settask.Adapter.todoAdapter;
import com.example.settask.Model.todoModel;
import com.example.settask.Utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements DialogCloseListener{

    private RecyclerView taskRecyclerview;
    private todoAdapter taskadepter;

    private List<todoModel> tasklist;
    public DatabaseHandler db;
    private FloatingActionButton fab;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        db = new DatabaseHandler(this);
        db.openDatabase();

        //tasklist = new ArrayList<>();

        taskRecyclerview = findViewById(R.id.taskrecycleview);
        taskRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        taskadepter  =new todoAdapter(db,MainActivity.this);
        taskRecyclerview.setAdapter(taskadepter);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(taskadepter));
        itemTouchHelper.attachToRecyclerView(taskRecyclerview);

        /*
    Dummy Data
        todoModel task = new todoModel();
        task.setTask("Thia is the to do");
        task.setStatus(0);
        task.setId(1);

        tasklist.add(task);
        tasklist.add(task);
        tasklist.add(task);
        tasklist.add(task);

        taskadepter.settask(tasklist);

     */

        fab = findViewById(R.id.addbutton);

        tasklist = db.getAllTask();
        Collections.reverse(tasklist);
        taskadepter.settask(tasklist);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager() ,AddNewTask.TAG);
            }
        });
    }


    @Override
    public void handleDialogClose(DialogInterface dialog) {
        tasklist = db.getAllTask();
        Collections.reverse(tasklist);
        taskadepter.settask(tasklist);
        taskadepter.notifyDataSetChanged();


    }
}
