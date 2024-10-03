package com.son.bottomnavigations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;

public class ToDoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> toDoList;

    public ToDoAdapter(Context context, ArrayList<String> toDoList) {
        this.context = context;
        this.toDoList = toDoList;
    }

    @Override
    public int getCount() {
        return toDoList.size();
    }

    @Override
    public Object getItem(int position) {
        return toDoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_to_do, parent, false);
        }

        // Bind the views
        CheckBox checkBox = convertView.findViewById(R.id.checkbox);
        TextView todoTitle = convertView.findViewById(R.id.todo_title);

        // Set the task title
        todoTitle.setText(toDoList.get(position));

        return convertView;
    }
}