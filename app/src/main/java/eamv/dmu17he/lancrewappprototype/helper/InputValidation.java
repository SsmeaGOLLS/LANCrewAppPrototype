package eamv.dmu17he.lancrewappprototype.helper;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import eamv.dmu17he.lancrewappprototype.model.User;
import eamv.dmu17he.lancrewappprototype.sql.userDatabase;


public class InputValidation {
    private Context context;
    private User user;


    public InputValidation(Context context){
        this.context = context;
    }

    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message){
        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty()){
            textInputEditText.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextUsername(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message, Context context){
        String value = textInputEditText.getText().toString().trim();
        userDatabase db = userDatabase.getDatabase(context);

        User theUser = db.uDAO().findUserFromName(textInputEditText.getText().toString());

        if(theUser==null || value.isEmpty())
        {
            //no user by that name
            textInputLayout.setErrorEnabled(false);
        }
        else
        {
            //user exists
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
/*
        if (value.isEmpty() || !(theUser.getUsername()==value))
        {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        else{
            textInputLayout.setErrorEnabled(false);
        }
*/
        return true;
    }

    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1,TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();

        if(!value1.contentEquals(value2)){
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;

        }
        else{
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
    private void hideKeyboardFrom(View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }
}
