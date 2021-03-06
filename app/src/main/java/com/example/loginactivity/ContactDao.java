package com.example.loginactivity;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.loginactivity.myObjects.Contact;

import java.util.List;


@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    List<Contact> index();

    @Query("DELETE  FROM contact")
    void deleteAll();

    @Query("SELECT * FROM contact WHERE id = :id")
    Contact get(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact... contacts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllOrders(List<Contact> contacts);

    @Update
    void update(Contact... contacts);

    @Delete
    void delete(Contact... contacts);
}
