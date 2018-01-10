package eamv.dmu17he.lancrewappprototype.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import java.util.List;

import eamv.dmu17he.lancrewappprototype.R;
import eamv.dmu17he.lancrewappprototype.helper.InputValidation;
import eamv.dmu17he.lancrewappprototype.model.User;
import eamv.dmu17he.lancrewappprototype.sql.userDatabase;
//import eamv.dmu17he.lancrewappprototype.sql.DBHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextUsername;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
   // private DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();
    }
    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextUsername = (TextInputEditText) findViewById(R.id.textInputEditTextUsername);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
    }

    private void initListeners(){
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    private void initObjects(){
        //DAO SHIT HER!
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonLogin:
                verifyFromDAO();
                break;

            case R.id.textViewLinkRegister:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void verifyFromDAO() {
       /*
        String userName = textInputEditTextUsername.getText().toString();
        String password = textInputEditTextPassword.getText().toString();

        Log.d("FIND MIG", textInputEditTextUsername.getText().toString());
        Log.d("OGSÅ MIG", textInputEditTextPassword.getText().toString());

        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);


        userDatabase db = userDatabase.getDatabase(this);
        db.uDAO().insertUser(user);


        List<User> users = db.uDAO().getAll();
        for (User uDBE : users) {
            String log = "id: " + uDBE.getId() + ", User Name: " + uDBE.getUsername() + ", Password: " + uDBE.getPassword();
            Log.d("User: :", log);

        }
        */
    }

    private void emptyInputEditText(){
        textInputEditTextUsername.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
