package eamv.dmu17he.lancrewappprototype.sql;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import eamv.dmu17he.lancrewappprototype.model.User;

/**
 * Created by Martin on 09-01-2018.
 */

@Dao
public interface userDAO
{
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Insert
    void insertAll(List<User> users);

    @Insert
    void insertUser(User theUser);

    @Update
    void update(User theUser);

    @Delete
    void delete(User theUser);

    @Query("DELETE FROM User")
    public void deleteTable();
}