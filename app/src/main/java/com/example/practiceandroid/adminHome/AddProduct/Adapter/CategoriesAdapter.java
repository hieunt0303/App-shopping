package com.example.practiceandroid.adminHome.AddProduct.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.practiceandroid.R;
import com.google.firebase.database.annotations.NotNull;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
  private List<AddProduct_Component> mlistComponent;
  private onCategoriesClickListener mlistener;

    public CategoriesAdapter(List<AddProduct_Component> mlistComponent, onCategoriesClickListener mlistener) {
        this.mlistComponent = mlistComponent;
        this.mlistener = mlistener;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_categories, parent, false);
        return new CategoriesViewHolder(view, mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        AddProduct_Component component = mlistComponent.get(position);
        if (component == null) return;
        holder.tvName.setText(component.getName());
    }

    @Override
    public int getItemCount() {
      if (mlistComponent != null){
          return mlistComponent.size();
      }
        return 0;
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvName;
        onCategoriesClickListener onCategoriesClickListener;

        public CategoriesViewHolder(@NonNull View itemView, onCategoriesClickListener listener){
            super(itemView);

            tvName = itemView.findViewById(R.id.textView_Category);
            this.onCategoriesClickListener = listener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onCategoriesClickListener.onItemListioner(getAdapterPosition());
        }
    }

    public interface onCategoriesClickListener{
      void onItemListioner(int posittion);
    }
}
