package eamv.dmu17he.lancrewappprototype;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by hkkrestlessPC on 06-01-2018.
 */

@Dao
public interface userDAO
{
    @Query("SELECT * FROM userDBEntity")
    List<userDBEntity> getAll();

    @Insert
    void insertAll(List<userDBEntity> users);

    @Insert
    void insertUser(userDBEntity theUser);

    @Update
    void update(userDBEntity theUser);

    @Delete
    void delete(userDBEntity theUser);

    @Query("DELETE FROM userDBEntity")
    public void deleteTable();
}