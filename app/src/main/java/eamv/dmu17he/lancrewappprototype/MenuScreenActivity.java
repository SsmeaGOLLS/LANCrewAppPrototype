package eamv.dmu17he.lancrewappprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
    }

    public void dailyPlanPressed(View view)
    {
        Intent intent = new Intent(this, dailyPlanStartActivity.class);
        startActivity(intent);
    }

    public void dutyRosterPressed(View view)
    {
        Intent intent = new Intent(this, DutyRosterStartActivity.class);
        startActivity(intent);
    }

    public void messagesPressed(View view)
    {
        Intent intent = new Intent(this, MessagesStartActivity.class);
        startActivity(intent);
    }

    public void wakeUpSchedulePressed(View view)
    {
        Intent intent = new Intent(this, WakeUpScheduleStartActivity.class);
        startActivity(intent);
    }

    public void logOutPressed(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
