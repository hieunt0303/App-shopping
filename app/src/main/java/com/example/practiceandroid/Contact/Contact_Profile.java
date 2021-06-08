package com.example.practiceandroid.Contact;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.practiceandroid.Contact.Fragment.adapter_tablayout_contact;
import com.example.practiceandroid.Fragment.ViewPageAdapter;
import com.example.practiceandroid.R;
import com.example.practiceandroid.User;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.IOException;
import java.security.Permission;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Contact_Profile extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    @BindView(R.id.editText_Address) EditText edtAddress;
    @BindView(R.id.editText_ID) EditText edtID;
    @BindView(R.id.editText_Email) EditText edtEmail;
    @BindView(R.id.editText_Username) EditText edtUserName;
    @BindView(R.id.editText_PhoneNumber) EditText edtPhoneNumber;

    @BindView(R.id.change_photo) TextView tvChangePhoto;
    @BindView(R.id.profile_avatar) ImageView ivProfile_Avatar;
    @BindView(R.id.button_submit) Button bttSubmit;
    @BindView(R.id.back_button) ImageView ivBack;

    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__profile);



        //Su dung BindView thay cho findViewbyID
        ButterKnife.bind(this);

        tvChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestPermission();
            }
        });

        ivBack.setOnClickListener(v -> finish());



    }

    //Request Permission
    private void RequestPermission(){
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                imageChooser();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(Contact_Profile.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
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
    void imageChooser() {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    ivProfile_Avatar.setImageURI(selectedImageUri);
                }
            }
        }
    }
}