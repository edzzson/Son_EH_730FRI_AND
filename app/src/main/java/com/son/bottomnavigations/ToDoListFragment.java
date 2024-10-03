package com.son.bottomnavigations;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ToDoListFragment extends Fragment {

    private ArrayList<String> toDoList;
    private ToDoAdapter adapter;
    private EditText editText;
    private ListView listView;
    private GestureDetector gestureDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);

        // Initialize views and list
        editText = view.findViewById(R.id.edit_text);
        Button addButton = view.findViewById(R.id.button_add);
        listView = view.findViewById(R.id.list_view);

        // Initialize the list and adapter
        toDoList = new ArrayList<>();
        adapter = new ToDoAdapter(requireActivity(), toDoList);
        listView.setAdapter(adapter);

        // Add button click listener to add items to the list
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newToDo = editText.getText().toString().trim();

                if (!newToDo.isEmpty()) {
                    // Add item to the list and notify the adapter
                    toDoList.add(newToDo);
                    adapter.notifyDataSetChanged();
                    editText.setText("");  // Clear the input field
                } else {
                    // Show a message if input is empty
                    Toast.makeText(requireActivity(), "Please enter a task", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // GestureDetector to handle double-tap
        gestureDetector = new GestureDetector(requireActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // Get position of the item clicked
                int position = listView.pointToPosition((int) e.getX(), (int) e.getY());

                // Ensure the position is valid
                if (position != AdapterView.INVALID_POSITION) {
                    showCustomDialog(position);
                }
                return super.onDoubleTap(e);
            }
        });

        // Set the onTouchListener for the ListView to detect gestures
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        return view;
    }

    // Method to show the custom dialog when an item is double-clicked
    private void showCustomDialog(int position) {
        // Inflate the custom dialog layout
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_delete, null);

        // Initialize views in the dialog
        EditText editText = dialogView.findViewById(R.id.edit_text);
        Button editButton = dialogView.findViewById(R.id.edit_button);
        Button deleteButton = dialogView.findViewById(R.id.delete_button);
        ImageButton closeButton = dialogView.findViewById(R.id.close_button);

        // Set the EditText with the selected item text
        editText.setText(toDoList.get(position));

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        // Set the onClickListener for the close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Set the onClickListener for the edit button
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editedTask = editText.getText().toString().trim();
                if (!editedTask.isEmpty()) {
                    // Update the task and notify adapter
                    toDoList.set(position, editedTask);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(requireActivity(), "Task cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set the onClickListener for the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the task and notify adapter
                toDoList.remove(position);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();
    }

}