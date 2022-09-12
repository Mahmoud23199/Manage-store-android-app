package com.example.sqllite.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqllite.room.BooksDatabase;
import com.example.sqllite.R;
import com.example.sqllite.pojo.data;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddActivity extends AppCompatActivity {
    EditText Title_input,Author_input,pages_input,total_num;
   // ImageView image_in;
    Button but_add;
    private static  final int Pick_IMAGE_CODE=1;
    private static  final int IMAGE_PERMISSION_CODE=10;
    //Uri imageUri;
    int gain = 0;
    int gain2 =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final BooksDatabase booksDatabase = BooksDatabase.getInstance(AddActivity.this);

        Title_input=findViewById(R.id.Title_input1);
        Author_input=findViewById(R.id.Author_input1);
        pages_input=findViewById(R.id.pages_input1);
        but_add=findViewById(R.id.but_update);
        total_num=findViewById(R.id.total_num);
        //image_in=findViewById(R.id.image_in);

       // image_in.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
             //   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                 //   if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                   //         == PackageManager.PERMISSION_DENIED){
                   //     String [] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                   //     requestPermissions(permissions,IMAGE_PERMISSION_CODE);
                  //  }
                  //  else {  pickUpFromGallery();}
             //   }
             //   else {   pickUpFromGallery(); }

          //  }
       // });




        but_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(AddActivity.this, "Item Added Successfully...", Toast.LENGTH_SHORT).show();
                data d =new data(Title_input.getEditableText().toString(),Author_input.getEditableText().toString(),pages_input.getEditableText().toString(),total_num.getText().toString(),total_num.getText().toString(),gain+"",gain2+"");

                booksDatabase.booksDao().insertBooks(d).subscribeOn(Schedulers.computation()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        //Toast.makeText(AddActivity.this, "Added Done", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {
                      // Toast.makeText(AddActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Pick_IMAGE_CODE && resultCode == RESULT_OK) {

           //imageUri= data.getData();
           // image_in.setImageURI(imageUri);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case IMAGE_PERMISSION_CODE:
            {
                if (grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    pickUpFromGallery();
                }
                else {
                    Toast.makeText(this, "permission was denied...!", Toast.LENGTH_SHORT).show();
               }
            }

        }
    }

    private void  pickUpFromGallery()
    {
        Intent in = new Intent(Intent.ACTION_PICK);
        in.setType("image/*");
        startActivityForResult(in,Pick_IMAGE_CODE);

    }
}