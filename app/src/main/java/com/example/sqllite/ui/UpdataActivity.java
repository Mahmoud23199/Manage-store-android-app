package com.example.sqllite.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqllite.room.BooksDatabase;
import com.example.sqllite.R;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UpdataActivity extends AppCompatActivity {
    EditText Title_input1, Author_input1, pages_input1,numberAl;
    Button but_update, but_delete;
    String title, original_price,numbers;
    int id1;
    String Sell_price;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);
         final BooksDatabase booksDatabase = BooksDatabase.getInstance(UpdataActivity.this);


        Title_input1 = findViewById(R.id.Title_input1);
        Author_input1 = findViewById(R.id.Author_input1);
        pages_input1 = findViewById(R.id.pages_input1);
        but_update = findViewById(R.id.but_update);
        but_delete = findViewById(R.id.but_delete);
        numberAl=findViewById(R.id.numberAl);

        Intent in = getIntent();
        id1 = in.getIntExtra("id", 1);
        title = in.getStringExtra("title");
        original_price = in.getStringExtra("original_price");
        Sell_price = in.getStringExtra("Sell_price");
        numbers=in.getStringExtra("total_num");




        Title_input1.setText(title);
        Author_input1.setText(original_price);
        pages_input1.setText(Sell_price);
        numberAl.setText(numbers);











        but_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1=Title_input1.getText().toString();
                String a1=Author_input1.getText().toString();
                String p1=pages_input1.getText().toString();
                String n2=numberAl.getText().toString();
                Toast.makeText(UpdataActivity.this, "Updated Successfully...", Toast.LENGTH_SHORT).show();

                //idtxt.setText(t1);

                //data d = new data(t1,a1,p1);

                booksDatabase.booksDao().update_titleBooks(t1,id1)
                        .subscribeOn(Schedulers.computation())
                        .subscribe();

                booksDatabase.booksDao().update_AuthorBooks(a1,id1)
                        .subscribeOn(Schedulers.computation())
                        .subscribe();

                booksDatabase.booksDao().update_PagesBooks(p1,id1)
                        .subscribeOn(Schedulers.computation())
                        .subscribe();
                booksDatabase.booksDao().update_numBooks(n2,id1)
                        .subscribeOn(Schedulers.computation())
                        .subscribe();


            }
        });
        but_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(UpdataActivity.this, "Deleted done", Toast.LENGTH_SHORT).show();
               //data d = new data(title, author, pages);
                confirmdilg();



            }
        });


    }

    public void confirmdilg() {
        AlertDialog.Builder built = new AlertDialog.Builder(this);
        built.setTitle("Delete "+ title + "?");
        built.setMessage("Are you sure you want to delete "+title+"?");
        built.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BooksDatabase booksDatabase = BooksDatabase.getInstance(UpdataActivity.this);
                booksDatabase.booksDao().deleteBooks(title).subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                               // Toast.makeText(UpdataActivity.this, "Deleted done", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(UpdataActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        });
            }
        });
        built.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        built.create().show();
}
}