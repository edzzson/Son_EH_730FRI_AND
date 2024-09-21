package com.scholarlink.news;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements HeadlinesFragment.OnHeadlineClickListener {

    private boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        isLandscape = findViewById(R.id.news_content) != null;

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.headlines, new HeadlinesFragment())
                    .commit();

            // If landscape
            if (isLandscape) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.news_content, NewsContentFragment.newInstance("Select a headline", "News content will appear here"))
                        .commit();
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onHeadlineSelected(String headline, String content) {

        if (!isLandscape) {
            NewsContentFragment newsContentFragment = NewsContentFragment.newInstance(headline, content);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.headlines, newsContentFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            NewsContentFragment newsContentFragment = NewsContentFragment.newInstance(headline, content);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.news_content, newsContentFragment)
                    .commit();
        }
    }

}