package com.example.fragmentexample1updated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button openButton;
    private Boolean isFragmentShown = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openButton = findViewById(R.id.open_btn);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentShown) {
                    showFragment();
                } else {
                    hideFragment();
                }
            }
        });
    }

    protected void showFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        PostLikeFragment postLikeFragment = PostLikeFragment.newInstance();
        fragmentTransaction.add(R.id.fragment_container, postLikeFragment)
                           .addToBackStack(null)
                           .commit();

        isFragmentShown = true;
        openButton.setText(R.string.close);
    }

    protected void hideFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        PostLikeFragment postLikeFragment = (PostLikeFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        fragmentTransaction.remove(postLikeFragment).commit();

        isFragmentShown = false;
        openButton.setText(R.string.open);
    }
}