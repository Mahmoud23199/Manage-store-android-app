package com.example.sqllite.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sqllite.adapter.OnadapterClick;
import com.example.sqllite.room.BooksDatabase;
import com.example.sqllite.adapter.CustomAdapter;
import com.example.sqllite.R;
import com.example.sqllite.pojo.data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID ="dd";
    RecyclerView rv_main;
    FloatingActionButton add_button;
    private static  final int Pick_IMAGE_CODE=11;
    private static  final int IMAGE_PERMISSION_CODE=12;
    EditText but_search;
    CustomAdapter adapter;
    ImageView emp,imageView2;
    Uri image_Uri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final BooksDatabase booksDatabase = BooksDatabase.getInstance(MainActivity.this);

        rv_main=findViewById(R.id.rv_main);
        add_button=findViewById(R.id.add_button);
        emp=findViewById(R.id.emp);
        imageView2=findViewById(R.id.imageView2);
        but_search=findViewById(R.id.but_search);
        Button butt_refresh=findViewById(R.id.butt_refresh);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,Details.class);
                startActivity(in);
            }
        });



        butt_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                //if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                //   == PackageManager.PERMISSION_DENIED){
                //String [] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                // requestPermissions(permissions,IMAGE_PERMISSION_CODE);
                // }
                // else {  pickUpFromGallery();}
                //}
                // else {   pickUpFromGallery(); }
                //  }
               // Intent i = new Intent(MainActivity.this, Details.class);
                //startActivity(i);
                String Cart1="اذكار";
                DisplayNotification(Cart1);
                booksDatabase.booksDao().getBooks().subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<data>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<data> data) {
                                adapter.setNames((ArrayList<data>) data);
                                adapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });

            }


        });
        //Search
        but_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty())
                {
                    booksDatabase.booksDao().getBooks().subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<List<data>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(List<data> data) {
                                    adapter.setNames((ArrayList<data>) data);
                                    adapter.notifyDataSetChanged();

                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            });

                    //adapter = new CustomAdapter(MainActivity.this,null);
                    //rv_main.setAdapter(adapter);
                }
                else {
                    adapter.filter(s.toString());
                }


            }
        });


        //refresh




        adapter = new CustomAdapter(MainActivity.this, new OnadapterClick() {
            @Override
            public void onItemClick(String num, String sell, final int id,int counter,String gain,String gain2,String buy) {


                int t1 = Integer.parseInt(num);

                int temp_gain=Integer.parseInt(sell)*counter+Integer.parseInt(gain);
                int temp_gain2=(Integer.parseInt(sell)-Integer.parseInt(buy))*counter +Integer.parseInt(gain2);

                Toast.makeText(MainActivity.this,"Successfully sold ", Toast.LENGTH_SHORT).show();
                String number = String.valueOf(t1-counter);
                booksDatabase.booksDao().update_remain(number,id).
                        subscribeOn(Schedulers.computation())
                        .subscribe();
                adapter.notifyDataSetChanged();



                booksDatabase.booksDao().update_gain(String.valueOf(temp_gain),id).
                        subscribeOn(Schedulers.computation())
                        .subscribe();
                adapter.notifyDataSetChanged();

                booksDatabase.booksDao().update_gain2(String.valueOf(temp_gain2),id).
                        subscribeOn(Schedulers.computation())
                        .subscribe();
                adapter.notifyDataSetChanged();










            }

        });

        rv_main.setAdapter(adapter);
        rv_main.setLayoutManager(new LinearLayoutManager(this));
        rv_main.setHasFixedSize(true);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, AddActivity.class);
                startActivity(in);
            }
        });




        booksDatabase.booksDao().getBooks().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<data> data) {
                        adapter.setNames((ArrayList<data>) data);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });











    }



    @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    super.onActivityResult(requestCode, resultCode, data);
     if (requestCode == Pick_IMAGE_CODE && resultCode == RESULT_OK) {

        image_Uri= data.getData();
        imageView2.setImageURI(image_Uri);

    }
     }

    @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch (requestCode){
           case IMAGE_PERMISSION_CODE:
        {
       if (grantResults.length>0 &&grantResults[0]== PackageManager.PERMISSION_GRANTED)
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

    private void DisplayNotification(String title) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Channel display name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("My des of channel");
            NotificationManager nm = getSystemService(NotificationManager.class);

            nm.createNotificationChannel(channel);
        }
        Intent in = new Intent(this,MainActivity.class);
        PendingIntent pn =PendingIntent.getActivity(this,0,in,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);

        builder.setSmallIcon(R.drawable.ic_add_shopping)
                .setContentTitle(title)
                .setContentText("صلي علي النبي محمد *-*")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.drawable.ic_add_shopping,title,pn);

        NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
        nmc.notify(10,builder.build());


    }



}