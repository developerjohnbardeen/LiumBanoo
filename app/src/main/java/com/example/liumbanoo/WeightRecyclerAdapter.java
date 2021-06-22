package com.example.liumbanoo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WeightRecyclerAdapter extends RecyclerView.Adapter<WeightRecyclerAdapter.WeightHolder> implements View.OnClickListener{

    private final Context context;
    private final LayoutInflater inflater;
    private final ArrayList<String> weightList;
    private static int oldPosition = -2;
    private static int newPosition = -1;
    private final onRecyclerViewItemClickListener onItemClickListener;



    public WeightRecyclerAdapter(Context context, ArrayList<String> list, onRecyclerViewItemClickListener onItemClickListener){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.weightList = list;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public WeightHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_weight_item, parent, false);
        return new WeightHolder(view, context, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull WeightHolder holder, int position) {


        cardViewBackGroundColorChecker(holder, position);
        String string = weightList.get(position);
        holder.weightTitle.setText(string);
        holder.weightCardView.setOnClickListener(v -> {
            holder.weightCardView.setCardBackgroundColor(context.getColor(R.color.orange));
            oldPosition = newPosition;
            newPosition = position;
        });

    }

    private void cardViewBackGroundColorChecker(WeightHolder holder, int position){

        if (newPosition == oldPosition){
            holder.weightCardView.setCardBackgroundColor(context.getColor(R.color.orange));
        }else {
            holder.weightCardView.setCardBackgroundColor(context.getColor(R.color.white));
        }
    }



    @Override
    public void onClick(View v) {

    }


    @Override
    public int getItemCount() {
        return weightList.size();
    }


    public class WeightHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView weightTitle;
        CardView weightCardView;
        final Context context;
        int oldPosition;
        int newPosition;
        RelativeLayout layout;
        boolean flag = false;
        onRecyclerViewItemClickListener onItemClickListener;

        public WeightHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView, Context context,
                            onRecyclerViewItemClickListener onItemClickListener) {
            super(itemView);
            this.context = context;
            this.onItemClickListener = onItemClickListener;
            weightTitle = itemView.findViewById(R.id.weight_text_view);
            weightCardView = itemView.findViewById(R.id.kilo_gram_card_view);
            layout = itemView.findViewById(R.id.top_single_item_layout);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null){
                onItemClickListener.onItemClickListener(v, getAdapterPosition());
            }
        }
    }

    public interface onRecyclerViewItemClickListener{
        void onItemClickListener(View view, int Position);
    }



    /*@Override
    public void onAttachedToRecyclerView(@NonNull @NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        Activity activity = (Activity) context;

        try{
            onItemClickListener = (onRecyclerViewItemClickListener) activity;

        }catch (RuntimeException e){
            throw new RuntimeException(activity.toString());
        }
    }
    */
}

