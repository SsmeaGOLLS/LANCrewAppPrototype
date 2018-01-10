package eamv.dmu17he.lancrewappprototype;

import android.content.Context;

/**
 * Created by hkkrestlessPC on 09-01-2018.
 */

public class globalSingleton
{
    private static globalSingleton gInstance;

    //the info
    public boolean rememberUserLogin=false;
    public String rememberedUserName;
    public String rememberedPassword;
    public userDBEntity theCurrentUser;


    public static void loadGlobalInfoFromFile()
    {

    }

    public static void saveGlobalInfo()
    {

    }


    public static globalSingleton getGlobals()
    {
        if(gInstance==null)
        {
            gInstance = new globalSingleton();

            //load info
            loadGlobalInfoFromFile();
        }
        return gInstance;
    }
}
