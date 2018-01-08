package eamv.dmu17he.lancrewappprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if remember me and prior user info exists, if so, autocomplete loginscreen
    }

    public void loginPressed(View view)
    {
        EditText userName =(EditText)findViewById(R.id.userNameInput);
        EditText password =(EditText)findViewById(R.id.passwordInput);

        //check if admin
        if(userName.getText().toString()=="admin"&&password.getText().toString()=="1234")
        {
            //
        }
        else
        {
            //check if username exists in database
            //check if password is correct
        }
    }

    public void loggedIn(userDBEntity theUser)
    {
        //create intent
        //go to menuScreen
    }
}
