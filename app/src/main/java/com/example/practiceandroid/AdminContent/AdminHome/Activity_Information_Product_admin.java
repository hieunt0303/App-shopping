package com.example.practiceandroid.AdminContent.AdminHome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.practiceandroid.Contact.Contact_Profile;
import com.example.practiceandroid.DatabaseUserLogin;
import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.function.FIREBASE;
import com.example.practiceandroid.function.ImageFromStorage;
import com.example.practiceandroid.function.ToLowerCase_Trim;
import com.example.practiceandroid.home.class_Information_Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Activity_Information_Product_admin extends AppCompatActivity {

    //  CÁC THÀNH PHẦN CÓ TRONG LAYOUT
    ImageView imgview_back;

    ImageView imgProduct1;
    ImageView imgProduct2;
    ImageView imgProduct3;
    ImageView img;

    EditText editText_nameProduct;
    EditText editText_inStock;
    EditText editText_price;
    EditText editText_category;
    EditText editText_detail;
    EditText editText_description;
    EditText editText_discount;
    private static final int SELECT_PICTURE = 1;
    Uri selectedImageUri;
    Uri Urianh;
    Uri upLoadUri1;
    Uri upLoadUri2;
    Uri upLoadUri3;
    FirebaseStorage storage;
    StorageReference storageReference;

    Button save;
    Button delete;

    // Các item trong nhắc trước chữ trong chat
    AlertDialog dialog;
    AlertDialog.Builder builder;
    String[] arr_categories = {"Woman", "Man", "Shoes", "Toys", "Electronics", "Furniture", "Phones", "Laptop"};

    // BIẾN PHỤ ĐỂ LƯU LẠI DATA TỪ GIAO DIỆN TRƯỚC ĐỀ PHÒNG XÓA NHÁNH SẢN PHẨM
    String idProduct;
    String categoryProduct;
    String sumRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__information__product_admin);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        // Khởi tạo dialog nhắc chữ
        builder = new AlertDialog.Builder(this);
        builder.setTitle("select choose");
        builder.setSingleChoiceItems(arr_categories, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editText_category.setText(arr_categories[which]);
                dialog.cancel();
            }
        });
        dialog = builder.create();

        // ANH XA
        imgview_back = findViewById(R.id.imageView_back_editAdmin);

        imgProduct1 = findViewById(R.id.imageView_imageProduct1_editAdmin);
        imgProduct2 = findViewById(R.id.imageView_imageProduct2_editAdmin);
        imgProduct3 = findViewById(R.id.imageView_imageProduct3_editAdmin);

        editText_nameProduct = findViewById(R.id.editText_nameProduct_editAdmin);
        editText_inStock = findViewById(R.id.editText_inStock_editAdmin);
        editText_price = findViewById(R.id.editText_Price_editAdmin);
        editText_discount = findViewById(R.id.editText_Discount_editAdmin);
        editText_category = findViewById(R.id.editText_Category_editAdmin);
        editText_detail = findViewById(R.id.editText_Detail_editAdmin);
        editText_description = findViewById(R.id.editText_Description_editAdmin);

        save = findViewById(R.id.button_Save_editAdmin);
        delete = findViewById(R.id.button_Delete_editAdmin);

        // GAN GIA TRI -> LẤY DATA TỪ adapter_Information_product
        // LÚC CLICK VÀO SẢN PHẨM VỚI VAI TRÒ ADMIN
        Intent intent = getIntent();
        // get id_product : intent.getStringExtra("Id_product") -> dùng để xóa và update cho dễ

        editText_nameProduct.setText(intent.getStringExtra("Name_product"));
        editText_inStock.setText(String.valueOf(intent.getIntExtra("inStock", 0)));
        editText_price.setText(intent.getStringExtra("Price_product_real"));
        editText_description.setText(intent.getStringExtra("description"));
        editText_detail.setText(intent.getStringExtra("detail"));
        editText_category.setText(intent.getStringExtra("Category_product"));
        editText_discount.setText(intent.getStringExtra("discount"));

        // GET 3 ẢNH SẢN PHẨM
        ImageFromStorage.setImage(intent.getStringExtra("Name_product"), 1, imgProduct1);
        ImageFromStorage.setImage(intent.getStringExtra("Name_product"), 2, imgProduct2);
        ImageFromStorage.setImage(intent.getStringExtra("Name_product"), 3, imgProduct3);

        idProduct=intent.getStringExtra("Id_product");
        categoryProduct=intent.getStringExtra("Category_product");
        sumRatingBar=intent.getStringExtra("Sum_Ratingbar");


        // THAY ĐỔI ẢNH BẰNG CÁCH NHẤN VÀO MÕI CÁI ẢNH
        // MỞ MÁY ẢNH RA VÀ LẤY ẢNH, HÀM CÓ TRONG SETTING, NHỚ XEM ĐÃ IMPORT THƯ VIỆN CHƯA
        imgProduct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img = imgProduct1;
                RequestPermission(1);


            }
        });

        imgProduct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img = imgProduct2;
                RequestPermission(2);


            }
        });


        imgProduct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img = imgProduct3;
                RequestPermission(3);


            }
        });

        // NHẤN SAVE VÀ DELETE
        // THỰC HIỆN 2 CHỨC NĂNG NÀY TRÊN CẢ REALTIMEDATABASE VÀ STORAGE ĐỂ SET ẢNH VÀ SET THÔNG TIN SẢN PHẨM
        editText_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MỞ RA DIALOG ĐỂ CHỌN
                dialog.show();
            }
        });
        imgview_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // NẾU ĐỔI :
        // NAME -> SETVALUE LẠI CHO SẢN PHẨM VÀ XÓA CÁI STORAGE LƯU HÌNH VÀ LƯU LẠI BẰNG TÊN MỚI HOẶC KIẾM CÁCH ĐỔI HẾT TÊN TRONG STORAGE
        // CATEGORY -> XÓA NHÁNH CHỨA SẢN PHẨM VÀ TẠO LẠI NHÁNH MỚI
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText_category.getText().toString().equals(intent.getStringExtra("Category_product"))) {
                    // NẾU ĐÃ CHỌN LẠI LOẠI THÌ PHẢI DELETE RỒI MỚI SET
                    // nhớ delete theo loại cũ : intent.getStringExtra("Category_product")
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("id_product").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("categories").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("name_product").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("price_product_real").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("discount").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("price_product_fake").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("Sum_Ratingbar").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("in_stock").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("Sum_Bought").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("favourite").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("add_to_cart").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("description").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("detail").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("color1").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("color2").removeValue();
                    FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("Img_border").removeValue();
                }
                FIREBASE.MDATA.child("Products").child(editText_category.getText().toString()).child(idProduct)
                        .setValue(new class_Information_Product(
                                idProduct,
                                editText_category.getText().toString(),
                                editText_nameProduct.getText().toString(),
                                editText_price.getText().toString(),
                                editText_discount.getText().toString(),
                                editText_price.getText().toString(),
                                sumRatingBar,
                                Integer.parseInt(editText_inStock.getText().toString()),
                                40,
                                false, false,
                                editText_description.getText().toString(),
                                editText_detail.getText().toString(),
                                1, 1, 1
                        ));
                // xét lại hình cho nó như đã nói ở trên
                // code here....
                uploadImage(1,upLoadUri1);
                uploadImage(2,upLoadUri2);
                uploadImage(3,upLoadUri3);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tiến hành xóa trên cả database và storage
                // finish(); dùng để xóa cái hiện tại và quay lại giao diện trước
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Information_Product_admin.this);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure to delete item "+intent.getStringExtra("Name_product"));
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("id_product").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("categories").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("name_product").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("price_product_real").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("discount").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("price_product_fake").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("Sum_Ratingbar").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("in_stock").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("Sum_Bought").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("favourite").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("add_to_cart").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("description").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("detail").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("color1").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("color2").removeValue();
                        FIREBASE.MDATA.child("Products").child(categoryProduct).child(idProduct).child("Img_border").removeValue();

                        finish();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                        finish();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


    }
    private void RequestPermission(int Postition){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                imageChooser(Postition);
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(Activity_Information_Product_admin.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }




    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser(int Postition) {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), Postition);
    }
    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == 1) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                upLoadUri1 = selectedImageUri;
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    img.setImageURI(selectedImageUri);

                }
            } else if (requestCode == 2) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                upLoadUri2 = selectedImageUri;
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    img.setImageURI(selectedImageUri);

                }
            } else if (requestCode == 3) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                upLoadUri3 = selectedImageUri;
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    img.setImageURI(selectedImageUri);

                }
            }
        }
    }
    private void uploadImage(int count, Uri uri) {

        if(uri != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            String child1= editText_nameProduct.getText().toString()+"/"+ ToLowerCase_Trim(editText_nameProduct.getText().toString())+count +".png";
            StorageReference ref = storageReference.child(child1);
            ref.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Activity_Information_Product_admin.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Activity_Information_Product_admin.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
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
}