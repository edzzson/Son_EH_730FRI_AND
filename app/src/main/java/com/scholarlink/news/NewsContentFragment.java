package com.scholarlink.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class NewsContentFragment extends Fragment {

    private static final String ARG_HEADLINE = "headline";
    private static final String ARG_CONTENT = "content";

    public static NewsContentFragment newInstance(String headline, String content) {
        NewsContentFragment fragment = new NewsContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HEADLINE, headline);
        args.putString(ARG_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_content, container, false);
        TextView headlineTitle = view.findViewById(R.id.headline_title);
        TextView newsContent = view.findViewById(R.id.news_content);

        if (getArguments() != null) {
            String headline = getArguments().getString(ARG_HEADLINE);
            String content = getArguments().getString(ARG_CONTENT);
            headlineTitle.setText(headline);
            newsContent.setText(content);
        }

        return view;
    }
}
