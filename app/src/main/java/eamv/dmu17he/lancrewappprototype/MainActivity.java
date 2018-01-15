package eamv.dmu17he.lancrewappprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.printf("derk");
        //Log.v("", "started");

        UserAzureDatabase db=UserAzureDatabase.getDatabase(this);
        userAzureDBEntity newUser=new userAzureDBEntity();
       /* newUser.uName="theTester";
        newUser.uPassword="1234";
        //newUser.mComplete=true;

        db.addUser(newUser);*/

        /*try
        {
            db.addUser(newUser);
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }*/

       /* List<userAzureDBEntity> testList = new ArrayList<userAzureDBEntity>();



            db.refreshItemsFromTable(testList);


       for(userAzureDBEntity aUser : testList)
        {
            Log.wtf("nu", "user: "+aUser.uName);
        }*/


        /*userDatabase uDB=userDatabase.getDatabase(this);
        userDBEntity newUser=new userDBEntity();
        newUser.userName="hkkrestless";
        newUser.userPassword="Restless123";
        uDB.uDAO().insertUser(newUser);*/
        //check if remember me and prior user info exists, if so, autocomplete loginscreen
        if(globalSingleton.getGlobals().rememberUserLogin==true)
        {
            EditText userName =(EditText)findViewById(R.id.userNameInput);
            EditText password =(EditText)findViewById(R.id.passwordInput);
            CheckBox rememberCheckBox=(CheckBox)findViewById(R.id.RememberCheckbox);

            userName.setText(globalSingleton.getGlobals().theCurrentUser.userName);
            password.setText(globalSingleton.getGlobals().theCurrentUser.userPassword);
            rememberCheckBox.setChecked(true);
        }
    }

    public void loginPressed(View view)
    {
        EditText userName =(EditText)findViewById(R.id.userNameInput);
        EditText password =(EditText)findViewById(R.id.passwordInput);

        userDatabase uDB=userDatabase.getDatabase(this);
        userDBEntity newUser=uDB.uDAO().getUserFromUserName(userName.getText().toString());

        if(newUser==null&& !(userName.getText().toString().equals("admin")))
        {
            //not viable username!
            Toast toast = Toast.makeText(this, "Username does not exist!", Toast.LENGTH_SHORT);
           toast.show();
            return;
        }

        //check if admin
        if(userName.getText().toString().equals("admin"))
        {
            if(password.getText().toString().equals("1234"))
            {
                if(newUser==null)
                {
                    //admin user does not exist in db, create it
                    userDBEntity adminUser=new userDBEntity();
                    adminUser.userName="admin";
                    adminUser.userPassword="1234";
                    uDB.uDAO().insertUser(adminUser);

                    loggedIn(adminUser);
                }
                else
                {
                    loggedIn(newUser);
                }
            }
            else
            {
                //wrong admin code
                Toast toast = Toast.makeText(this, "Wrong Password!", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
        }
        else
        {
            //check if password is correct
            if(password.getText().toString().equals(newUser.userPassword))
            {
                loggedIn(newUser);
            }
            else
            {
                //wrong password
                Toast toast = Toast.makeText(this, "Wrong Password!", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
        }
    }

    public void loggedIn(userDBEntity theUser)
    {
        //set global with the current user
        globalSingleton.getGlobals().theCurrentUser=theUser;
        //if remember me is checked, make sure theuser is saved in globals
        globalSingleton.getGlobals().rememberUserLogin=true;
        globalSingleton.saveGlobalInfo();

        //create intent
        Intent intent=new Intent(this, MenuScreenActivity.class);
        startActivity(intent);
        //go to menuScreen
    }
}
