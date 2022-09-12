package com.example.sqllite.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sqllite.pojo.data;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
@Dao
public interface BooksDao {

@Insert
Completable insertBooks(data data);



@Query("select * from books_table")
Single<List<data>>getBooks();





@Query("update  books_table set book_title =:title where book_id=:id ")//(onConflict = OnConflictStrategy.REPLACE)
Completable update_titleBooks(String title,int id);

@Query("update  books_table set book_author =:author where book_id=:id ")//(onConflict = OnConflictStrategy.REPLACE)
Completable update_AuthorBooks(String author,int id);


@Query("update  books_table set book_pages =:pages where book_id=:id ")//(onConflict = OnConflictStrategy.REPLACE)
Completable update_PagesBooks(String pages,int id);

@Query("update  books_table set totalNum =:num where book_id=:id ")//(onConflict = OnConflictStrategy.REPLACE)
Completable update_numBooks(String num,int id);

@Query("update  books_table set remain =:remain where book_id=:id ")//(onConflict = OnConflictStrategy.REPLACE)
Completable update_remain(String remain,int id);

@Query("update  books_table set gain =:gain where book_id=:id ")//(onConflict = OnConflictStrategy.REPLACE)
Completable update_gain(String gain,int id);

@Query("update  books_table set gain2 =:gain2 where book_id=:id ")//(onConflict = OnConflictStrategy.REPLACE)
Completable update_gain2(String gain2,int id);

@Query( "DELETE FROM books_table WHERE book_title = :id")
Completable deleteBooks(String id);



}
