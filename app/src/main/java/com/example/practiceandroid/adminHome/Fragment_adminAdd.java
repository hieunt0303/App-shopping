package com.example.practiceandroid.adminHome;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practiceandroid.R;
import com.example.practiceandroid.adminHome.AddProduct.Adapter.AddProduct_Component;
import com.example.practiceandroid.adminHome.AddProduct.Adapter.ClassifyAdapter;
import com.example.practiceandroid.adminHome.AddProduct.Adapter.WarehouseAdapter;
import com.example.practiceandroid.adminHome.AddProduct.AddProduct_Categories;
import com.example.practiceandroid.function.FIREBASE;
import com.example.practiceandroid.home.class_Information_Product;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_adminAdd#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_adminAdd extends Fragment {
    private static final int GET_CATEGORIES = 13;
    Unbinder unbinder;
    @BindView(R.id.textView_Name_KeyCount)
    TextView tvNameCount;
    @BindView(R.id.textView_Description_KeyCount)
    TextView tvDescriptionCount;
    @BindView(R.id.editText_Name)
    EditText edtName;
    @BindView(R.id.editText_Description)
    EditText edtDescription;

    @BindView(R.id.imagebutton_AddProduct_image1)
    ImageButton ibImage1;
    @BindView(R.id.imagebutton_AddProduct_image2)
    ImageButton ibImage2;
    @BindView(R.id.imagebutton_AddProduct_image3)
    ImageButton ibImage3;


    @BindView(R.id.textView_chooseImage1)
    TextView tvChooseImage1;
    @BindView(R.id.textView_chooseImage2)
    TextView tvChooseImage2;
    @BindView(R.id.textView_chooseImage3)
    TextView tvChooseImage3;





    @BindView(R.id.frame_detail)
    FrameLayout frDetail;
    @BindView(R.id.image_detailArrow)
    ImageView imDetailArrow;

    @BindView(R.id.framelayout_Categories)
    FrameLayout frCategories;
    @BindView(R.id.textView_Categories)
    TextView tvCatecories;

    @BindView(R.id.framelayout_Classification)
    FrameLayout frClassification;
    @BindView(R.id.textView_Classify)
    TextView tvClassify;
    List<AddProduct_Component> listColor;
    List<AddProduct_Component> listSize;

    @BindView(R.id.framelayout_Condittion)
    FrameLayout frCondittion;
    @BindView(R.id.textView_Condition)
    TextView tvCondittion;

    @BindView(R.id.framelayout_Warehouse)
    FrameLayout frWarehouse;
    @BindView(R.id.textView_Warehouse)
    TextView tvWarehouse;
    List<AddProduct_Component> listWarehouse;

    @BindView(R.id.framelayout_Price)
    FrameLayout frPrice;
    @BindView(R.id.textView_addPrice)
    TextView tvAddPrice;

    @BindView(R.id.framelayout_Storage)
    FrameLayout frStorage;
    @BindView(R.id.textView_Stock)
    TextView tvStock;


    @BindView(R.id.button_AddProduct)
    Button bttSave;

    FirebaseStorage storage;
    StorageReference storageReference;

    String sCategories, sCondition, sPrice, sName, sDescription, sStorage;
    Uri prImage1, prImage2, prImage3;
    List<String> lsWarehouse, lsColor, lsSize;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_adminAdd() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_adminAdd.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_adminAdd newInstance(String param1, String param2) {
        Fragment_adminAdd fragment = new Fragment_adminAdd();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_add, container, false);
        //Use ButterKnife instead of findViewByID
        unbinder = ButterKnife.bind(Fragment_adminAdd.this, view);

        //Count text in editText
        Textcount(edtName, tvNameCount, 120);
        Textcount(edtDescription, tvDescriptionCount, 3000);
        lsColor = new ArrayList<>();
        lsSize = new ArrayList<>();
        lsWarehouse = new ArrayList<>();
        //Set Firebase
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        //Set visible for detail
        LinearLayout lnDetail = view.findViewById(R.id.Linear_detail);
        frDetail.setOnClickListener(v -> {
            if (lnDetail.getVisibility() == View.VISIBLE) {
                lnDetail.setVisibility(View.INVISIBLE);
                imDetailArrow.setImageResource(R.drawable.ic_arrow_right);
            } else {
                lnDetail.setVisibility(View.VISIBLE);
                imDetailArrow.setImageResource(R.drawable.ic_arrow_down);
            }
        });

        ibImage1.setOnClickListener(v -> RequestPermission(1));
        ibImage2.setOnClickListener(v -> RequestPermission(2));
        ibImage3.setOnClickListener(v -> RequestPermission(3));




        frCategories.setOnClickListener(v -> getCategories());
        frPrice.setOnClickListener(v -> setupPriceDialog());
        frWarehouse.setOnClickListener(v -> setupWarehouseDialog());
        frClassification.setOnClickListener(v -> setupClassifyDialog());
        frCondittion.setOnClickListener(v -> setupCondittionDialog());
        frStorage.setOnClickListener(v -> setupStorageDialog());


        //GET FIREBASE DATA HERE
        //button Save
        bttSave.setOnClickListener(v -> {
            sName = edtName.getText().toString();
            sDescription = edtDescription.getText().toString();

            if (checkComponents());
            else {
                try {
                    Toast.makeText(Fragment_adminAdd.this.getContext(), "lsWarehouse: " + lsWarehouse.size() + "\nlsColor: " + lsColor.size() + "\nlsSize: " + lsSize.size(), Toast.LENGTH_SHORT).show();
                    final int random = new Random().nextInt((9999 - 500) + 1) + 400;
                    String fakePrice = String.valueOf(Integer.parseInt(sPrice) * 2 + 10000);
                    FIREBASE.MDATA.child("Products").child(sCategories).child(String.valueOf(random))
                            .setValue(new class_Information_Product(
                                    String.valueOf(random),
                                    sCategories,
                                    sName,
                                    sPrice,
                                    "0",
                                    sPrice,
                                    "0",
                                    100,
                                    40,
                                    false, false,
                                    "Made in Heaven",
                                    sDescription,
                                    1, 1, 1
                            ));
                    uploadImage(1, prImage1);
                    uploadImage(2, prImage2);
                    uploadImage(3, prImage3);
                } catch (NullPointerException e){
                    Toast.makeText(Fragment_adminAdd.this.getContext(), "Please pick 3 image", Toast.LENGTH_SHORT).show();
                }


            }


        });
        // Inflate the layout for this fragment
        return view;
    }



    //SET FIRBASE DATA HERE
    private void getWarehouseListData(){
        // Add firebase Data here
        listWarehouse = new ArrayList<>();
        listWarehouse.add(new AddProduct_Component("KTX khu A"));
        listWarehouse.add(new AddProduct_Component("KTX khu B"));
        listWarehouse.add(new AddProduct_Component("Bcons"));
    }
    private void getClassifyListData(){
        // Add firebase Data here
        listColor = new ArrayList<>();
        listColor.add(new AddProduct_Component("White"));
        listColor.add(new AddProduct_Component("Dark"));
        listSize = new ArrayList<>();
        listSize.add(new AddProduct_Component("small"));
        listSize.add(new AddProduct_Component("medium"));
        listSize.add(new AddProduct_Component("big"));
    }


    private void getCategories() {
        Intent intent = new Intent (Fragment_adminAdd.this.getContext(), AddProduct_Categories.class);
        startActivityForResult(intent, GET_CATEGORIES);
    }

    private void setupPriceDialog() {
        Dialog dialog = new Dialog(Fragment_adminAdd.this.getContext());
        dialog.setContentView(R.layout.dialog_edt);

        EditText editText = dialog.findViewById(R.id.dialog_edt);
        Button bttYes = dialog.findViewById(R.id.dialog_edt_Yes);
        Button bttNo = dialog.findViewById(R.id.dialog_edt_No);

        NumberFormat nf = NumberFormat.getInstance(Locale.US);

        bttYes.setOnClickListener(v -> {
            final String string = editText.getText().toString();
            if (string.isEmpty()) Toast.makeText(Fragment_adminAdd.this.getContext(), "Please enter Price", Toast.LENGTH_SHORT).show();
            else {
                sPrice = string;
                tvAddPrice.setText(nf.format(Integer.parseInt(sPrice)));
                dialog.dismiss();
            }
        });
        bttNo.setOnClickListener(v -> dialog.dismiss());

        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void setupWarehouseDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(Fragment_adminAdd.this.getContext());
        dialog.setContentView(R.layout.dialog_warehouse);
        dialog.setCanceledOnTouchOutside(true);

        RecyclerView rvWarehouse = dialog.findViewById(R.id.recyclerView_Warehouse);
        getWarehouseListData();
        WarehouseAdapter aWarehouse = new WarehouseAdapter(listWarehouse, Fragment_adminAdd.this.getContext());
        setupWarehouseRecyclerView(rvWarehouse, aWarehouse);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                lsWarehouse.clear();

                int sizeWarehouse = aWarehouse.getSelected().size();
                setWarehouseUItext(sizeWarehouse);
                for (int i = 0; i < sizeWarehouse; i++){
                    lsWarehouse.add(aWarehouse.getSelected().get(i).getName());
                }
            }

            private void setWarehouseUItext(int size) {
                String textWarehouse;
                if (size > 1) textWarehouse = size + " Warehouses";
                else textWarehouse = size + " Warehouse";
                tvWarehouse.setText(textWarehouse);
            }
        });

        dialog.show();
    }
    private void setupWarehouseRecyclerView(RecyclerView rv, WarehouseAdapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Fragment_adminAdd.this.getContext());
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }

    private void setupClassifyDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(Fragment_adminAdd.this.getContext());
        dialog.setContentView(R.layout.dialog_classify);
        dialog.setCanceledOnTouchOutside(true);

        getClassifyListData();

        RecyclerView rvColor = dialog.findViewById(R.id.recyclerView_color);
        ClassifyAdapter aColor = new ClassifyAdapter(listColor, Fragment_adminAdd.this.getContext());

        RecyclerView rvSize = dialog.findViewById(R.id.recyclerView_size);
        ClassifyAdapter aSize = new ClassifyAdapter(listSize, Fragment_adminAdd.this.getContext());

        ImageView addColor = dialog.findViewById(R.id.image_Add_Color);
        ImageView addSize = dialog.findViewById(R.id.image_Add_Size);
        TextView delColor = dialog.findViewById(R.id.textview_delete_Color);
        TextView delSize = dialog.findViewById(R.id.textview_delete_Size);

        Button bttEdit = dialog.findViewById(R.id.button_Classify);
        bttEdit.setOnClickListener(v -> {
            if (addColor.getVisibility() == View.GONE){
                addColor.setVisibility(View.VISIBLE);
                addSize.setVisibility(View.VISIBLE);
                delColor.setVisibility(View.VISIBLE);
                delSize.setVisibility(View.VISIBLE);
            } else{
                addColor.setVisibility(View.GONE);
                addSize.setVisibility(View.GONE);
                delColor.setVisibility(View.GONE);
                delSize.setVisibility(View.GONE);
            }
        });

        try {
            setupClassifyRecyclerView(rvColor, aColor);
            setupClassifyRecyclerView(rvSize, aSize);

            delColor.setOnClickListener(v -> {

                int position;
                while (aColor.getPositionSelected() != -1){
                    position = aColor.getPositionSelected();
                    listColor.remove(position);
                    aColor.notifyItemRemoved(position);
                }

            });
            delSize.setOnClickListener(v -> {
                int position;
                while (aSize.getPositionSelected() != -1){
                    position = aSize.getPositionSelected();
                    listSize.remove(position);
                    aSize.notifyItemRemoved(position);
                }

            });


            addColor.setOnClickListener(v -> {
                onAddClassify(listColor);
                aColor.notifyItemChanged(listColor.size() - 1);
            });
            addSize.setOnClickListener(v -> {
                onAddClassify(listSize);
                aSize.notifyItemChanged(listSize.size() - 1);
            });

        }catch (NullPointerException e){};

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                lsColor.clear();
                lsSize.clear();


                int sizeColor = aColor.getSelected().size();
                int sizeSize = aSize.getSelected().size();
                setClassifyUItext(sizeColor, sizeSize);
                for (int i = 0; i < sizeColor; i++){
                    lsColor.add(aColor.getSelected().get(i).getName());
                }
                for (int i = 0; i < sizeSize; i++){
                    lsSize.add(aSize.getSelected().get(i).getName());
                }
            }

            private void setClassifyUItext(int color, int size) {
                String textColor = (color > 1)? " Colors/" : " Color/";
                String textSize = (size > 1)? " Sizes" : " Size";
                String Text = color + textColor + size + textSize;
                tvClassify.setScaleX(1);
                tvClassify.setScaleY(1);
                tvClassify.setText(Text);
            }
        });

        dialog.show();
    }
    private void setupClassifyRecyclerView(RecyclerView rv, ClassifyAdapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Fragment_adminAdd.this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }

    private void onAddClassify(List<AddProduct_Component> listAdd) {
        Dialog dialog = new Dialog(Fragment_adminAdd.this.getContext());
        dialog.setContentView(R.layout.dialog_classify_add);

        EditText editText = dialog.findViewById(R.id.dialog_edt_classify);
        Button bttOK = dialog.findViewById(R.id.dialog_classify_OK);
        editText.requestFocus();
        bttOK.setOnClickListener(v -> {
            final String string = editText.getText().toString();
            if (string.isEmpty()) Toast.makeText(Fragment_adminAdd.this.getContext(), "Please enter name", Toast.LENGTH_SHORT).show();
            else {
                listAdd.add(new AddProduct_Component(editText.getText().toString()));
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void setupCondittionDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(Fragment_adminAdd.this.getContext());
        dialog.setContentView(R.layout.dialog_condition);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        RadioButton New = dialog.findViewById(R.id.radio_New);
        RadioButton Used = dialog.findViewById(R.id.radio_Used);

        FrameLayout fNew = dialog.findViewById(R.id.frame_New);
        FrameLayout fUsed = dialog.findViewById(R.id.frame_Used);

        try {
            fNew.setOnClickListener(v -> {
                sCondition = "New";
                tvCondittion.setText(sCondition);
                New.setChecked(true);
                Used.setChecked(false);
                tvCondittion.setScaleX(1);
                tvCondittion.setScaleY(1);
            });
            fUsed.setOnClickListener(v -> {
                sCondition = "Used";
                tvCondittion.setText(sCondition);
                New.setChecked(false);
                Used.setChecked(true);
                tvCondittion.setScaleX(1);
                tvCondittion.setScaleY(1);
            });
        }catch (NullPointerException e){}

        dialog.show();
    }
    private void setupStorageDialog() {
        Dialog dialog = new Dialog(Fragment_adminAdd.this.getContext());
        dialog.setContentView(R.layout.dialog_add_storage);

        EditText editText = dialog.findViewById(R.id.dialog_edt_Storage);
        Button bttYes = dialog.findViewById(R.id.dialog_Storage_Yes);
        Button bttNo = dialog.findViewById(R.id.dialog_Storage_No);

        NumberFormat nf = NumberFormat.getInstance(Locale.US);

        bttYes.setOnClickListener(v -> {
            final String string = editText.getText().toString();
            if (string.isEmpty()) Toast.makeText(Fragment_adminAdd.this.getContext(), "Please enter ammount of products", Toast.LENGTH_SHORT).show();
            else {
                sStorage = string;
                tvStock.setText(nf.format(Integer.parseInt(sStorage)));
                dialog.dismiss();
            }
        });
        bttNo.setOnClickListener(v -> dialog.dismiss());

        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


    boolean checkComponents(){
        if (lsColor.size() == 0 || lsWarehouse.size() == 0 || lsSize.size() == 0){
            Toast.makeText(Fragment_adminAdd.this.getContext(), "Please fill all components", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (TextUtils.isEmpty(edtName.getText().toString()) || TextUtils.isEmpty(edtDescription.getText().toString())){
            edtName.setError("Please set value in this component");
            edtDescription.setError("Please set value in this component");
            return true;
        }

        return false;
    }
    //Count textlength in edittext
    private void Textcount (TextView TextCount, TextView display, int length){
        TextCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String UI = s.length() + "/" + length;
                display.setText(UI);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }
    //Request Permission
    private void RequestPermission(int position){

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                imageChooser(position);
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(Fragment_adminAdd.this.getContext(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }

        };
        TedPermission.with(Fragment_adminAdd.this.getContext())
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }

    void imageChooser(int position) {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), position);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == 1) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    prImage1 = selectedImageUri;
                    ibImage1.setImageURI(selectedImageUri);
                    tvChooseImage1.setVisibility(View.GONE);
                } else tvChooseImage1.setVisibility(View.VISIBLE);
            }

            if (requestCode == 2) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    prImage2 = selectedImageUri;
                    ibImage2.setImageURI(selectedImageUri);
                    tvChooseImage2.setVisibility(View.GONE);
                } else tvChooseImage2.setVisibility(View.VISIBLE);
            }

            if (requestCode == 3) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    prImage3 = selectedImageUri;
                    ibImage3.setImageURI(selectedImageUri);
                    tvChooseImage3.setVisibility(View.GONE);
                } else tvChooseImage3.setVisibility(View.VISIBLE);
            }

            if (requestCode == GET_CATEGORIES){
                sCategories = data.getStringExtra("Categories");
                tvCatecories.setScaleX(1);
                tvCatecories.setScaleY(1);
                tvCatecories.setText(sCategories);
            }
        }
    }

    private void uploadImage(int count, Uri uri) {

        if(uri != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(Fragment_adminAdd.this.getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            String child1= sName+"/"+ ToLowerCase_Trim(sName)+count +".png";
            StorageReference ref = storageReference.child(child1);
            ref.putFile(uri)
                    .addOnSuccessListener(taskSnapshot -> {
                        progressDialog.dismiss();
                        Toast.makeText(Fragment_adminAdd.this.getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(Fragment_adminAdd.this.getContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    })
                    .addOnProgressListener(taskSnapshot -> {
                        double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                .getTotalByteCount());
                        progressDialog.setMessage("Uploaded "+(int)progress+"%");
                    });
        }
    }
    public  static String ToLowerCase_Trim(String data){
        //làm thành chữ thường và bỏ dấu cách
        String [] arrdata= data.split(" ");
        String trim="";
        for(int i=0;i<arrdata.length;i++){
            trim = trim + arrdata[i].toLowerCase();
        }
        return  trim;
    }

    public void onDestroyView() {
        super.onDestroyView();

        // unbind the view to free some memory
        unbinder.unbind();
    }
}