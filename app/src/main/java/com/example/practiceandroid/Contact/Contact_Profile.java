package com.example.practiceandroid.Contact;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.practiceandroid.DatabaseUserLogin;
import com.example.practiceandroid.Manhinh_DK;
import com.example.practiceandroid.Manhinh_Login;
import com.example.practiceandroid.R;
import com.example.practiceandroid.User;
import com.example.practiceandroid.function.ToLowerCase_Trim;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Contact_Profile extends AppCompatActivity {
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    private static final int SELECT_PICTURE = 1;
    DatabaseUserLogin databaseUserLogin;
    Uri selectedImageUri;
    FirebaseStorage storage;
    StorageReference storageReference;
    @BindView(R.id.editText_Address) EditText edtAddress;
    @BindView(R.id.editText_ID) EditText edtID;
    @BindView(R.id.editText_Email) EditText edtEmail;
    @BindView(R.id.editText_Username) EditText edtUserName;
    @BindView(R.id.editText_PhoneNumber) EditText edtPhoneNumber;

    @BindView(R.id.change_photo) TextView tvChangePhoto;
    @BindView(R.id.profile_name) TextView name;
    @BindView(R.id.profile_avatar) ImageView ivProfile_Avatar;
    @BindView(R.id.button_submit) Button bttSubmit;
    @BindView(R.id.back_button) ImageButton ivBack;
    @BindView(R.id.logout_button) ImageButton logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__profile);
        databaseUserLogin = new DatabaseUserLogin(this, "user.sqlite", null, 1);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        //Su dung BindView thay cho findViewbyID
        ButterKnife.bind(this);
        name.setText(Manhinh_Login.userlogin.name_user);
        edtUserName.setText(Manhinh_Login.userlogin.name_user);
        edtID.setText(Manhinh_Login.userlogin.id);
        edtAddress.setText(Manhinh_Login.userlogin.address);
        edtEmail.setText(Manhinh_Login.userlogin.email);
        edtPhoneNumber.setText(Manhinh_Login.userlogin.phone);
        tvChangePhoto.setOnClickListener(v -> RequestPermission());
        layanh();
        ivBack.setOnClickListener(v -> finish());

        bttSubmit.setOnClickListener(v -> {
            String sEmail = edtEmail.getText().toString();
            String iPhone = edtPhoneNumber.getText().toString();

            if (TextUtils.isEmpty(edtAddress.getText().toString()) ||
                    TextUtils.isEmpty(edtUserName.getText().toString())){
                setupDialogMessage(" Something wrong\nPlease check again");
            }
            else if (!TextUtils.isEmpty(iPhone) && iPhone.length() != 10){
                setupDialogMessage("Phone number length must = 10");
            }
            else {
                setupDialogYesNo();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseUserLogin.QueryData("Drop table User");
                Intent mh = new Intent(Contact_Profile.this, Manhinh_Login.class);
                startActivity(mh);
            }
        });



    }

    private void setupDialogMessage(String message) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_messagebox);

        TextView tvMessage =dialog.findViewById(R.id.tvMessage_dialog);
        Button bttOK = dialog.findViewById(R.id.btt_dialogOk);

        tvMessage.setText(message);
        bttOK.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void setupDialogYesNo() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_confirmbox);

        Button bttYes = dialog.findViewById(R.id.btt_dialogYes);
        Button bttNo  = dialog.findViewById(R.id.btt_dialogNo);

        bttNo.setOnClickListener(v -> dialog.dismiss());
        bttYes.setOnClickListener(v ->  {
            mData.child("User").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    if (snapshot.child("email").getValue().toString().equals(edtEmail.getText().toString())) {
                        mData.child("User").child(snapshot.getKey()).child("address").setValue(edtAddress.getText().toString());
                        mData.child("User").child(snapshot.getKey()).child("phone").setValue(edtPhoneNumber.getText().toString());
                        Manhinh_Login.userlogin.address=edtAddress.getText().toString();
                        Manhinh_Login.userlogin.phone=edtPhoneNumber.getText().toString();

                    }

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            uploadImage();
            dialog.dismiss();
        });

        dialog.show();
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
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    ivProfile_Avatar.setImageURI(selectedImageUri);
                }
            }
        }
    }
    private void uploadImage() {

        if(selectedImageUri != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("Avata/"+ Manhinh_Login.userlogin.getName_user()+".png");
            ref.putFile(selectedImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Contact_Profile.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Contact_Profile.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
    private  void layanh()
    {
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("Avata/"+ Manhinh_Login.userlogin.getName_user()+".png");
        try {
            final File localfile= File.createTempFile(Manhinh_Login.userlogin.getName_user(),"png");
            mStorage.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            //Toast.makeText(HomeFragment.this,"Đưa ảnh lên thành công !!!",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            ivProfile_Avatar.setImageBitmap(bitmap);

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}