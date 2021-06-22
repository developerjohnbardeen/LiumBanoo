package com.example.liumbanoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liumbanoo.ResultFolder.ResultActivity;
import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar;
import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBarWrapper;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, WeightRecyclerAdapter.onRecyclerViewItemClickListener {

    private RecyclerView weightRecyclerView;
    private WeightRecyclerAdapter weightAdapter;
    private CardView calCardView;
    private ArrayList<String> weightList;
    private TextView lengthTitle;
    private ImageView mannequinImageView;
    private VerticalSeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        //weightThread();
        lengthFunction();
        weightGenerator();
        setWeightRecyclerView();
    }




    private void weightThread(){
        ExecutorService thread = Executors.newSingleThreadExecutor();
        weightList = new ArrayList<>();
        Handler handler = new Handler(Looper.getMainLooper());
        thread.execute(() ->{
            //weightGenerator();
            //setRecyclerFragment();
            //handler.post(this::setWeightRecyclerView);
        });
    }

    private void weightGenerator(){
        weightList = new ArrayList<>();
        for (int i = 25 ; i <= 174; i++){
            String weight = Integer.toString(i);
            weightList.add(weight);
        }
    }

    private void setWeightRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        weightRecyclerView.setLayoutManager(layoutManager);
        weightAdapter = new WeightRecyclerAdapter(this, weightList, this);
        weightRecyclerView.setAdapter(weightAdapter);
    }

    private void lengthFunction(){
        seekBar.setMin(100);
        seekBar.setMax(200);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mannequinImageView.getLayoutParams().height =  (progress + 300);
                mannequinImageView.requestLayout();

                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                StringBuilder string = new StringBuilder();
                string.append(progress);
                string.append("\n");
                string.append(getString(R.string.centi_meter));
                lengthTitle.setText(string);
                lengthTitle.setY(seekBar.getY() + val + ((float) seekBar.getThumbOffset() % 2));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }







    private void findViewsById(){
        weightRecyclerView = findViewById(R.id.body_mass_index_recycler_view);
        calCardView = findViewById(R.id.calculate_card_View);
        lengthTitle = findViewById(R.id.choosing_length_text_view);
        seekBar = findViewById(R.id.choosing_length_seekbar);
        mannequinImageView = findViewById(R.id.mannequin_image_view);
        thisOnClickListener();

        weightRecyclerView.setOnClickListener(v -> {
            int position = weightRecyclerView.getChildAdapterPosition(calCardView);
            Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.back_pressed_image_view):
                onBackPressed();
                break;
            case (R.id.calculate_card_View):
                onCalculateListener();
                break;
            case (R.id.body_mass_index_recycler_view):
                break;
        }
    }

    private void onCalculateListener(){
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);

    }

    private void thisOnClickListener(){
        calCardView.setOnClickListener(this);
        weightRecyclerView.setOnClickListener(this);
    }


    @Override
    public void onItemClickListener(View view, int Position) {
        Toast.makeText(this, "Toast" + Position, Toast.LENGTH_SHORT).show();
    }
}