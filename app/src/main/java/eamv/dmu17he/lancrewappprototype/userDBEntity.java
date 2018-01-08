package eamv.dmu17he.lancrewappprototype;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by hkkrestlessPC on 06-01-2018.
 */

@Entity
public class userDBEntity
{
    @PrimaryKey(autoGenerate = true)
    public int userID;

    @ColumnInfo (name="userName")
    public String exerciseName;
}
