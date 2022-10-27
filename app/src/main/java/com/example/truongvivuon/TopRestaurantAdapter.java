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

public class TopRestaurantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnTopRestaurantItemClickListener {
        void onTopRestaurantItemClick(TopRestaurant topRestaurant);
    }

    public class ViewHolderTopRestaurant extends RecyclerView.ViewHolder {
        TextView tvName_TR, tvRate;
        ImageView ivImage_TR;

        public ViewHolderTopRestaurant(View itemView) {
            super(itemView);
            tvName_TR = itemView.findViewById(R.id.tvName_TR);
            ivImage_TR = itemView.findViewById(R.id.ivImage_TR);
            tvRate = itemView.findViewById(R.id.tvRate);
        }
    }


    private List<TopRestaurant> mRestaurants;
    private OnTopRestaurantItemClickListener mListener;
    private int TYPE_LAYOUT ;

    public TopRestaurantAdapter(ArrayList<TopRestaurant> topRestaurants, int type_layout) {
        mRestaurants = topRestaurants;
//        mListener = listener;
        TYPE_LAYOUT =type_layout;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if(TYPE_LAYOUT == 1){
            View view = inflater.inflate(R.layout.row_top_restaurant, parent, false);
            return new ViewHolderTopRestaurant(view);
        }else {
            View view = inflater.inflate(R.layout.row_top_restaurant, parent, false);
            return new ViewHolderTopRestaurant(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TopRestaurant topRestaurant = mRestaurants.get(position);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        if (TYPE_LAYOUT == 1) {
            ViewHolderTopRestaurant viewHolderTopRestaurant = (ViewHolderTopRestaurant) holder;
            StorageReference profileRef = storageReference.child("restaurants/" + topRestaurant.logo);
            profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(viewHolderTopRestaurant.ivImage_TR);
                }
            });
            viewHolderTopRestaurant.tvName_TR.setText(topRestaurant.name);
            viewHolderTopRestaurant.tvRate.setText("Rate:" + topRestaurant.rate);
            viewHolderTopRestaurant.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onTopRestaurantItemClick(topRestaurant);
                }
            });
        } else {
            ViewHolderTopRestaurant viewHolderTopRestaurant = (ViewHolderTopRestaurant) holder;
            StorageReference profileRef = storageReference.child("restaurants/" + topRestaurant.logo);
            profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(viewHolderTopRestaurant.ivImage_TR);
                }
            });
            viewHolderTopRestaurant.tvName_TR.setText(topRestaurant.name);
            viewHolderTopRestaurant.tvRate.setText("Rate: " + topRestaurant.rate);
            viewHolderTopRestaurant.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mListener.onTopRestaurantItemClick(topRestaurant);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public void addTopRestaurants(ArrayList<TopRestaurant> topRestaurants){
        mRestaurants.addAll(topRestaurants);
        notifyDataSetChanged();
    }
}
