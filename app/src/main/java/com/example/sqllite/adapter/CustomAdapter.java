package com.example.sqllite.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqllite.R;
import com.example.sqllite.pojo.data;
import com.example.sqllite.ui.UpdataActivity;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewholder> {
    ArrayList<data> names=new ArrayList<>();
    Context context;
    int counter;
    int gain;
    OnadapterClick listener;

    public CustomAdapter(Context context, OnadapterClick listener) {

        this.context=context;
        this.listener=listener;

    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.row, null,false);
        MyViewholder holder = new MyViewholder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewholder holder, final int position) {
         String num = names.get(position).getTotalNum();


        holder.book_title.setText(names.get(position).getBook_title());
        holder.book_pages.setText("Selling price is :"+names.get(position).getBook_pages());
        holder.book_author.setText("Original price is :"+names.get(position).getBook_author());
        holder.txt_num.setText("Total number of items is :"+names.get(position).getTotalNum());

        if (names.get(position).getRemain() !=null && Integer.parseInt(names.get(position).getRemain()) != 0) {
            holder.txt_remaind.setText("Remind :" + names.get(position).getRemain());
        }
        else Toast.makeText(context, "We no longer have this product to be sold. Please bring another quantity*-*", Toast.LENGTH_SHORT).show();

        holder.txt_gain.setText("Item gain :"+names.get(position).getGain()+" $");
        holder.txt_gain2.setText("Your gain :"+names.get(position).getGain2()+" $");
        


        //holder.imagep1.setImageURI(Uri.parse(names.get(position).getImageIn()));

        holder.but_sold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (counter < Integer.parseInt(names.get(position).getRemain())) {
                    counter++;
                    holder.txt_sold.setText(String.valueOf(counter));
                }
                else Toast.makeText(context, "You do not have this number to sell ", Toast.LENGTH_SHORT).show();
            }
        });
        holder.but_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter >0) {
                    counter--;
                  holder.txt_sold.setText(String.valueOf(counter));
                }
            }
        });

        holder.but_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent i = new Intent(context.getApplicationContext(), Details.class);
                //  i.putExtra("total_num",names.get(position).getTotalNum());
                // i.putExtra("Sell_price",names.get(position).getBook_pages());

                // context.startActivity(i);
                if (counter == 0 ) {
                    Toast.makeText(context, "Enter number of item sold", Toast.LENGTH_SHORT).show();
                } else {


                    if (names.get(position).getRemain() != null && Integer.parseInt(names.get(position).getRemain()) != 0) {
                        listener.onItemClick(names.get(position).getRemain(), names.get(position).getBook_pages(), names.get(position).getBook_id(), counter, names.get(position).getGain() + "", names.get(position).getGain2(), names.get(position).getBook_author());
                    } else
                        Toast.makeText(context, "We no longer have this product to be sold. Please bring another quantity*-* ", Toast.LENGTH_LONG).show();

                }
            }
        });

        holder.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context.getApplicationContext(), UpdataActivity.class);
                in.putExtra("id",names.get(position).getBook_id());
                in.putExtra("title",names.get(position).getBook_title());
                in.putExtra("total_num",names.get(position).getTotalNum());
                in.putExtra("Sell_price",names.get(position).getBook_pages());
                in.putExtra("original_price",names.get(position).getBook_author());


                context.startActivity(in);
            }
        });





    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public void setNames(ArrayList<data> names) {
        this.names = names;
        notifyDataSetChanged();
    }

    class MyViewholder extends RecyclerView.ViewHolder {
        TextView txt_remaind,book_title,book_author,book_pages,txt_num,txt_gain,txt_sold,txt_gain2;
        ImageView imageView5;
        Button but_sub,but_sold,but_no;


        public MyViewholder(@NonNull View row) {
            super(row);

            book_author=row.findViewById(R.id.book_author);
            book_pages=row.findViewById(R.id.book_pages);
            book_title=row.findViewById(R.id.book_title);
            txt_remaind=row.findViewById(R.id.txt_remaind);
            txt_gain=row.findViewById(R.id.txt_gain);
            txt_num=row.findViewById(R.id.txt_num);
            but_sub=row.findViewById(R.id.but_sub);
            but_sold=row.findViewById(R.id.but_sold);
            txt_sold=row.findViewById(R.id.txt_sold);
            txt_gain2=row.findViewById(R.id.txt_gain2);
            but_no=row.findViewById(R.id.but_no);
            imageView5=row.findViewById(R.id.imageView5);




        }
    }
    public void filter(String toString) {
        ArrayList<data>filterDataA = new ArrayList<>();
        for (data d : names)
        {
            if (d.getBook_title().toLowerCase().contains(toString.toLowerCase()))
            {
                filterDataA.add(d);
            }
        }

        setNames(filterDataA);
        notifyDataSetChanged();
    }


}
