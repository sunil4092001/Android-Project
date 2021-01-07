package com.example.settask;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.settask.Model.todoModel;
import com.example.settask.Utils.DatabaseHandler;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewTask extends BottomSheetDialogFragment {

    public static final String TAG = "ActionButtonDialog";
    private EditText newTasktext;
    private Button newTaskSaveButton;
    private DatabaseHandler db;
    private DialogInterface dialog;

    public static AddNewTask newInstance(){
        return  new AddNewTask();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_task      , container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newTasktext = getView().findViewById(R.id.newtasktest);
        newTaskSaveButton = getView().findViewById(R.id.newButtonSave);

        db= new DatabaseHandler(getActivity());
        db.openDatabase();

        boolean isUpdate = false;
        final Bundle bundle = getArguments();
        if(bundle != null) {
            isUpdate = true;
            String task = bundle.getString("task");
            newTasktext.setText(task);
            if (task.length() > 0)
                newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        }
            newTasktext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (s.toString().equals("")){
                        newTaskSaveButton.setEnabled(false);
                        newTaskSaveButton.setTextColor(Color.GRAY);
                    }
                    else {
                        newTaskSaveButton.setEnabled(true);
                        newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));

                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            final boolean finalIsUpdate = isUpdate;
            newTaskSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = newTasktext.getText().toString();
                    if (finalIsUpdate){
                        db.updateTask(bundle.getInt("id"),text);
                    }else {
                        todoModel task = new todoModel();
                        task.setTask(text);
                        task.setStatus(0);
                    }
                    dismiss();
                }
            });
        }


    public void onDismiss(DialogInterface dialog){
        Activity activity = getActivity();
        if (activity instanceof DialogCloseListener){
            ((DialogCloseListener)activity).handleDialogClose(dialog);
        }
    }
}
