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

import java.util.List;

public class OderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public class ViewHoderOrder extends RecyclerView.ViewHolder{
        TextView tv_idOrder,tv_DateOrder,tv_NameRes,tv_AddressRes,tv_PriceOrder,tv_State;
        ImageView iv_Res;

        public ViewHoderOrder(@NonNull View itemView) {
            super(itemView);
            tv_NameRes=itemView.findViewById(R.id.tv_nameRes);
            tv_DateOrder=itemView.findViewById(R.id.tv_dateOrder);
            tv_idOrder=itemView.findViewById(R.id.tv_idOrder);
            tv_AddressRes=itemView.findViewById(R.id.tv_addressResOrder);
            tv_PriceOrder=itemView.findViewById(R.id.tv_PriceOrder);
            iv_Res=itemView.findViewById(R.id.iv_imgOrder);
            tv_State=itemView.findViewById(R.id.tv_Statement);


        }
    }
    private List<Order> orders;

    public OderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.row_item_order,parent,false);
        return new ViewHoderOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Order order = orders.get(position);
        ViewHoderOrder viewHoderOrder = (ViewHoderOrder) holder;
        StorageReference imgRef = FirebaseStorage.getInstance().getReference().child("restaurants/" + order.restaurant.logo);

        imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(viewHoderOrder.iv_Res);
            }
        });
        viewHoderOrder.tv_NameRes.setText(order.restaurant.name);
        viewHoderOrder.tv_idOrder.setText(order.id);
        viewHoderOrder.tv_PriceOrder.setText(order.pricetotal+" VND");
        viewHoderOrder.tv_AddressRes.setText(order.restaurant.address);
        viewHoderOrder.tv_DateOrder.setText(order.date);
        if (order.state==1)
        {
            viewHoderOrder.tv_State.setText("Đang vận chuyển");
        }
        else {
            viewHoderOrder.tv_State.setText("Hoàn thành");
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
