package com.example.practiceandroid.Contact.Tablayout_Fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceandroid.R;
import com.example.practiceandroid.function.ImageFromStorage;
import com.example.practiceandroid.shopping.FeedBacks_Products;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentSnapshot;


import org.jetbrains.annotations.NotNull;

public class adapter_Feedback  extends FirebaseRecyclerAdapter<FeedBacks_Products, adapter_Feedback.FeedbackItemViewholder> {
    onItemClickListener mlistener;

    public adapter_Feedback(@NonNull @NotNull FirebaseRecyclerOptions<FeedBacks_Products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull adapter_Feedback.FeedbackItemViewholder holder, int position, @NonNull @NotNull FeedBacks_Products model) {
            holder.prName.setText(model.getProductName());
            holder.prPrice.setText("$" + model.getProductPrice());
            ImageFromStorage.setImage(model.getProductName(), 1, holder.ivProduct);
    }

    @NonNull
    @NotNull
    @Override
    public FeedbackItemViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_feedback_item, parent, false);
        return new FeedbackItemViewholder(view);
    }


    public class FeedbackItemViewholder extends RecyclerView.ViewHolder{
        TextView prName;
        TextView prPrice;
        ImageView ivProduct;
        Button bttComment;


        public FeedbackItemViewholder(@NonNull View itemView){
            super(itemView);

            prName = itemView.findViewById(R.id.textView_Name_feedback);
            prPrice = itemView.findViewById(R.id.textView_Price_feedback);
            ivProduct = itemView.findViewById(R.id.imageView_Product_feedback);
            bttComment = itemView.findViewById(R.id.button_Comment);

            bttComment.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && mlistener != null){
                    mlistener.onItemListener(getSnapshots().getSnapshot(position), position);
                }
            });
        }

    }

    public interface onItemClickListener{
        void onItemListener(DataSnapshot documentSnapshot, int position);
    }

    public void setOnclickListener(onItemClickListener listener){
        this.mlistener = listener;
    }

}
