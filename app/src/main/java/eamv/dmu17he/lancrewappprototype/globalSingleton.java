package eamv.dmu17he.lancrewappprototype;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
    public userAzureDBEntity theCurrentUser;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedEdit;

    public void loadGlobalInfoFromFile(Context context)
    {
        sharedPref= PreferenceManager.getDefaultSharedPreferences(context);
        String tempUserName= sharedPref.getString("USERNAME", "");
        if(tempUserName!="")
        {
            rememberedUserName=tempUserName;
        }
        else
        {
            rememberedUserName="";
        }

        String tempPassword= sharedPref.getString("PASSWORD", "");
        if(tempPassword!="")
        {
            rememberedPassword=tempPassword;
        }
        else
        {
            rememberedPassword="";
        }

        boolean rememberedLogin= sharedPref.getBoolean("REMEMBERME", false);
        if(rememberedLogin)
        {
            rememberUserLogin=true;
        }
        else
        {
            rememberUserLogin=false;
        }


    }

    public void saveGlobalInfo(Context context)
    {
        sharedPref= PreferenceManager.getDefaultSharedPreferences(context);
        sharedEdit= sharedPref.edit();
        sharedEdit.putString("USERNAME", rememberedUserName);
        sharedEdit.putString("PASSWORD", rememberedPassword);
        sharedEdit.putBoolean("REMEMBERME", rememberUserLogin);
        sharedEdit.apply();
    }


    public static globalSingleton getGlobals(Context context)
    {
        if(gInstance==null)
        {
            gInstance = new globalSingleton(context);


        }
        return gInstance;
    }

    private globalSingleton (Context context)
    {
        loadGlobalInfoFromFile(context);
    }
}
