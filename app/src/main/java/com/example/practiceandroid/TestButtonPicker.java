package com.example.practiceandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import gun0912.tedbottompicker.TedBottomPicker;


public class TestButtonPicker extends AppCompatActivity {

    Button buttonRequiredPermisson;
    CircleImageView imgviewAvatarProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_button_picker);
        buttonRequiredPermisson= findViewById(R.id.button_required_permission);
        imgviewAvatarProduct= findViewById(R.id.imgview_Avatarproduct);


        imgviewAvatarProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requiredpermission();

            }
        });
    }
    private void requiredpermission(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                //Toast.makeText(TestButtonPicker.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                openImgPicker();

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(TestButtonPicker.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }
    private void openImgPicker() {
        TedBottomPicker.OnImageSelectedListener listener= new TedBottomPicker.OnImageSelectedListener() {
            @Override
            public void onImageSelected(Uri uri) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imgviewAvatarProduct.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
        TedBottomPicker tedBottomPicker= new TedBottomPicker.Builder(TestButtonPicker.this)
                .setOnImageSelectedListener(listener)
                .create();
        tedBottomPicker.show(getSupportFragmentManager());
    }
}