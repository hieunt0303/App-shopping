package com.example.practiceandroid.adminHome.AddProduct.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.practiceandroid.R;
import com.google.firebase.database.annotations.NotNull;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.classifyViewHoler>{
    private List<AddProduct_Component> mlistComponent;
    private Context mContext;


    public ClassifyAdapter(List<AddProduct_Component> mlistComponent, Context mContext) {
        this.mlistComponent = mlistComponent;
        this.mContext = mContext;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public classifyViewHoler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_classify, parent, false);
        return new classifyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ClassifyAdapter.classifyViewHoler holder, int position) {
        AddProduct_Component component = mlistComponent.get(position);
        if (component == null) return;
        holder.bind(mlistComponent.get(position));
    }

    @Override
    public int getItemCount() {
        if (mlistComponent != null) return mlistComponent.size();
        return 0;
    }

    public class classifyViewHoler extends RecyclerView.ViewHolder {
        TextView title;
        ImageView box;
        public classifyViewHoler (@NonNull View item){
            super(item);
            title = item.findViewById(R.id.textview_classify_title);
            box = item.findViewById(R.id.classify_box);
        }

        //Get the selected item
        void bind(final AddProduct_Component component){
            box.setImageResource(component.isChecked() ? R.drawable.classify_selected : R.drawable.classify_border);
            box.bringToFront();
            title.setText(component.getName());

            box.setOnClickListener(v -> {
                component.setChecked(!component.isChecked());
                box.setImageResource(component.isChecked() ? R.drawable.classify_selected : R.drawable.classify_border);
                notifyDataSetChanged();
            });
        }

    }


    public List<AddProduct_Component> selected = new ArrayList<>();
    public List<AddProduct_Component> getSelected(){
        for (int i = 0; i < mlistComponent.size(); i++){
            if (mlistComponent.get(i).isChecked()){
                selected.add(mlistComponent.get(i));
            }
        }
        return selected;
    }

    public int getPositionSelected() {
        int size = mlistComponent.size();
        for (int i = 0; i < size; i++){
            if (mlistComponent.get(i).isChecked()){
                return i;
            }
        }
        return -1;
    }
}
