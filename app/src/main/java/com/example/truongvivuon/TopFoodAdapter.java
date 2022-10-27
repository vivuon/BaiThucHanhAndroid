package com.example.truongvivuon;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopFoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public interface OnFoodItemClickListener {
        void onFoodItemClick(Food food);
    }
    public class ViewHolderFood extends RecyclerView.ViewHolder {
        ImageView ivImageF;
        TextView tvNameF, tvRateF, tvPrice;

        public ViewHolderFood(View itemView) {
            super(itemView);
            tvNameF = itemView.findViewById(R.id.tvNameF);
            tvRateF = itemView.findViewById(R.id.tvRateF);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivImageF = itemView.findViewById(R.id.ivImageF);
        }
    }


    private List<Food> mFood;
    private OnFoodItemClickListener mListener;
    private int TYPE_LAYOUT ;

    public TopFoodAdapter(List<Food> foods,OnFoodItemClickListener listener, int type_layout) {
        mFood = foods;
        mListener = listener;
        TYPE_LAYOUT =type_layout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if(TYPE_LAYOUT == 1){
            View view = inflater.inflate(R.layout.row_top_food, parent, false);
            return new ViewHolderFood(view);
        }else {
            View view = inflater.inflate(R.layout.row_top_food, parent, false);
            return new ViewHolderFood(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Food food = mFood.get(position);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        if(TYPE_LAYOUT == 1) {
            ViewHolderFood viewHolderFood = (ViewHolderFood) holder;
            StorageReference profileRef = storageReference.child("foods/" + food.getImage());
            profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(viewHolderFood.ivImageF);
                }
            });
            viewHolderFood.tvNameF.setText(food.name);
            viewHolderFood.tvRateF.setText("Rate: " + food.rate);
            viewHolderFood.tvPrice.setText("" + food.price);
            viewHolderFood.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFoodItemClick(food);
                }
            });
        }
        else
        {
            ViewHolderFood viewHolderFood = (ViewHolderFood) holder;
            StorageReference profileRef = storageReference.child("foods/"+ food.getImage());
            profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(viewHolderFood.ivImageF);
                }
            });
            viewHolderFood.tvNameF.setText(food.getName());
            viewHolderFood.tvRateF.setText("Rate: "+food.rate);
            viewHolderFood.tvPrice.setText(""+food.price);
            viewHolderFood.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFoodItemClick(food);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mFood.size();
    }

    public void addFood(ArrayList<Restaurant> restaurants){
        mFood.addAll(mFood);
        notifyDataSetChanged();
    }
}
