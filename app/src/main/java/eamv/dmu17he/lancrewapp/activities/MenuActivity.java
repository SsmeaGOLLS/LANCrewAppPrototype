package eamv.dmu17he.lancrewapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import eamv.dmu17he.lancrewapp.R;
import eamv.dmu17he.lancrewapp.helper.AzureServiceAdapter;
import eamv.dmu17he.lancrewapp.helper.ToDialogError;

public class MenuActivity extends AppCompatActivity {

    Button button;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

       /* Button schedule = (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                goToScheduleActivity();
            }
        });

        Button contacts = (Button) findViewById(R.id.contacts);
        contacts.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                goToContactsActivity();
            }
        });

        Button click = (Button) findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                goToProfileActivity();
            }
        });
    */
        AzureServiceAdapter.Initialize();
        ToDialogError.initToDialogError();
    }

    public void goToScheduleActivity(View view){
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }

    public void goToContactsActivity(View view){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    public void goToProfileActivity(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }


}
