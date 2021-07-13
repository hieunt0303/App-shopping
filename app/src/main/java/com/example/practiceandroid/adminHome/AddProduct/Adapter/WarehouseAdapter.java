package com.example.practiceandroid.adminHome.AddProduct.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.practiceandroid.R;
import com.google.firebase.database.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

public class WarehouseAdapter extends RecyclerView.Adapter<WarehouseAdapter.WarehouseViewholder> {
    private List<AddProduct_Component> mlistComponent;
    private Context mContext;

    public WarehouseAdapter(List<AddProduct_Component> mlistComponent, Context mContext) {
        this.mlistComponent = mlistComponent;
        this.mContext = mContext;
        notifyDataSetChanged();
    }


    @NonNull

    @Override
    public WarehouseViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyler_warehouse, parent, false);
        return new WarehouseViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WarehouseAdapter.WarehouseViewholder holder, int position) {
        AddProduct_Component component = mlistComponent.get(position);
        if (component == null) return;
        holder.bind(mlistComponent.get(position));
    }

    @Override
    public int getItemCount() {
        if (mlistComponent != null) return mlistComponent.size();
        return 0;
    }

    public class WarehouseViewholder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView imCheck;
        FrameLayout frWareHouse;

        public WarehouseViewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.warehouse_name);
            imCheck = itemView.findViewById(R.id.radio_warehouse);
            frWareHouse = itemView.findViewById(R.id.frame_warehouse);
        }
        //Get the selected item
        void bind(final AddProduct_Component component){
            name.setText(component.getName());
            imCheck.setVisibility(component.isChecked()? View.VISIBLE : View.INVISIBLE);

            frWareHouse.setOnClickListener(v -> {
                component.setChecked(!component.isChecked());
                imCheck.setVisibility(component.isChecked()? View.VISIBLE : View.INVISIBLE);
                notifyDataSetChanged();
            });
        }

    }

    public List<AddProduct_Component> getAll() {return mlistComponent;}

    public List<AddProduct_Component> selected = new ArrayList<>();
    public List<AddProduct_Component> getSelected(){
        for (int i = 0; i < mlistComponent.size(); i++){
            if (mlistComponent.get(i).isChecked()){
                selected.add(mlistComponent.get(i));
            }
        }
        return selected;
    }


    public void addComponent(String name){
        mlistComponent.add(new AddProduct_Component(name));
        notifyDataSetChanged();
    }
}
