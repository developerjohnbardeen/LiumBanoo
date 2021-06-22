package com.example.liumbanoo.ResultFolder;

import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.liumbanoo.R;
import at.blogc.android.views.ExpandableTextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    private ExpandableTextView textInfo;
    private Button expandButton;
    private CardView startAgainCardView, backToToolsCardView;
    private RelativeLayout btnLayout;
    private ImageView backPressedImageView;


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        findViewsById();
    }





    private void findViewsById(){
        textInfo = findViewById(R.id.expandable_information_textview);
        expandButton = findViewById(R.id.expand_text_view_button);
        startAgainCardView  = findViewById(R.id.start_again_card_view);
        backToToolsCardView = findViewById(R.id.back_to_tools_card_view);
        backPressedImageView = findViewById(R.id.result_back_pressed_image_view);
        btnLayout = findViewById(R.id.button_layout);
        thisClickListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.expand_text_view_button):
                expandButton();
                break;
            case (R.id.start_again_card_view):
            case (R.id.result_back_pressed_image_view):
                onBackPressed();
                break;
        }
    }

    private void thisClickListener(){
        expandButton.setOnClickListener(this);
        startAgainCardView.setOnClickListener(this);
        backPressedImageView.setOnClickListener(this);
    }


    private void expandButton(){
        //expandButton.setVisibility(View.GONE);
        btnLayout.setVisibility(View.GONE);
        textInfo.toggle();
        expandableTextView();
    }
    private void expandableTextView(){
        textInfo.setAnimationDuration(750L);
        textInfo.setInterpolator(new OvershootInterpolator());
        textInfo.setExpandInterpolator(new OvershootInterpolator());
        textInfo.setCollapseInterpolator(new OvershootInterpolator());
    }
}
