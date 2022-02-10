package com.example.fragmentexample1updated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements PostLikeFragment.OnFragmentInteractionListener {

    private Button openButton;
    private Boolean isFragmentShown = false;

    private int mCurrentChoice = PostLikeFragment.NONE;

    public static final String FRAGMENT_STATE = "fragment-state";

    @Override
    public void onRadioButtonChoiceChecked(int choice) {
        mCurrentChoice = choice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            isFragmentShown = savedInstanceState.getBoolean(FRAGMENT_STATE);

            if (isFragmentShown) {
                openButton.setText(R.string.open);
            } else {
                openButton.setText(R.string.close);
            }
        }

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
        PostLikeFragment postLikeFragment = PostLikeFragment.newInstance(mCurrentChoice);
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FRAGMENT_STATE, isFragmentShown);
    }
}