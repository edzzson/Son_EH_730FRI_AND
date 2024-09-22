package com.scholarlink.menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Set the title and subtitle for the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Menu Activity");
            getSupportActionBar().setSubtitle("Edz Henissey Son");
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Add menu items programmatically
        menu.add(0, 1, Menu.NONE, "1st");
        menu.add(0, 2, Menu.NONE, "2nd");
        menu.addSubMenu(0, 3, Menu.NONE, "3rd").add(0, 4, Menu.NONE, "Exit");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                // Navigate to FirstFragment
                FirstFragment firstFragment = new FirstFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, firstFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                return true;

            case 2:
                // Show MyDialogFragment
                MyDialogFragment dialog = new MyDialogFragment();
                dialog.show(getSupportFragmentManager(), "MyDialogFragment");
                return true;

            case 4:
                // Exit the app
                finishAffinity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}