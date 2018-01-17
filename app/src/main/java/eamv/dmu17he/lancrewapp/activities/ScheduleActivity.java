package eamv.dmu17he.lancrewapp.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import eamv.dmu17he.lancrewapp.R;
import eamv.dmu17he.lancrewapp.helper.AzureServiceAdapter;
import eamv.dmu17he.lancrewapp.helper.ToDialogError;
import eamv.dmu17he.lancrewapp.model.Schedule;
import eamv.dmu17he.lancrewapp.helper.ScheduleAdapter;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;

public class ScheduleActivity extends AppCompatActivity {

    private Button refresh;
    private MobileServiceClient mClient;
    private MobileServiceTable<Schedule> mTable;
    private ProgressBar mProgressBar;
    private ScheduleAdapter mScheduleAdapter;
    private AzureServiceAdapter mAzureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        initButtonAndProgressBar();
        initMobileService();

        createTable();
        
        refreshItemsFromTable();

       /* Button createSchedule = (Button) findViewById(R.id.createSchedule);
        if (mClient.getCurrentUser().getIsAdmin()){
            createSchedule.setVisibility(View.VISIBLE);
        } else{
            createSchedule.setVisibility(View.GONE);
        }*/

    }

    private void createTable() {
        try {
            initLocalStore().get();
            ListView listViewSchedule = (ListView) findViewById(R.id.scheduleListView);
            listViewSchedule.setAdapter(mScheduleAdapter);
            refreshItemsFromTable();
        } catch (InterruptedException | ExecutionException | MobileServiceLocalStoreException e) {
        ToDialogError.getInstance().createAndShowDialogFromTask(e, "Error", this);
    }
    }

    public void addItem(View view) {
        if (mClient == null) {
            return;
        }

        // Create a new item
        final Schedule schedule = new Schedule();
        schedule.setId("6");
        schedule.setGaName("123Glenn Mortensen");
        schedule.setDate("2018-01-12");
        schedule.setStartTime("09:00:00");
        schedule.setEndTime("13:00:00");
        schedule.setNickName("Dakkedakfyr");
        schedule.setTitle("Opvasker i køkken");

        final Schedule schedule1 = new Schedule();
        schedule1.setId("2");
        schedule1.setGaName("Glenn Mortensen");
        schedule1.setDate("2018-01-12");
        schedule1.setStartTime("10:00:00");
        schedule1.setEndTime("13:00:00");
        schedule1.setNickName("Mikkel VHN");
        schedule1.setTitle("Suutuuututtu");

        final Schedule schedule2 = new Schedule();
        schedule2.setId("3");
        schedule2.setGaName("Glenn Mortensen");
        schedule2.setDate("2018-01-12");
        schedule2.setStartTime("10:00:00");
        schedule2.setEndTime("13:00:00");
        schedule2.setNickName("Mikkel VHN");
        schedule2.setTitle("rgrgrgrgrg");

        final Schedule schedule3 = new Schedule();
        schedule3.setId("7");
        schedule3.setGaName("Glenn Mortensen");
        schedule3.setDate("2018-01-12");
        schedule3.setStartTime("10:00:00");
        schedule3.setEndTime("13:00:00");
        schedule3.setNickName("Mikkel VHN");
        schedule3.setTitle("DAvdav");

        final Activity mActivity = this;

        // Insert the new item
        @SuppressLint("StaticFieldLeak") //Just to suppress warning
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    final Schedule entity = addItemInTable(schedule);
                    /*
                    final Schedule entity1 = addItemInTable(schedule1);
                    final Schedule entity2 = addItemInTable(schedule2);
                    final Schedule entity3 = addItemInTable(schedule3);
                    */

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mScheduleAdapter.add(entity);

                        }
                    });
                } catch (final Exception e) {
                    ToDialogError.getInstance().createAndShowDialogFromTask(e, "Error", mActivity);
                    e.printStackTrace();
                }
                return null;
            }
        };
        runAsyncTask(task);
    }

    public Schedule addItemInTable(Schedule item) throws ExecutionException, InterruptedException {
        Schedule entity = mTable.insert(item).get();
        return entity;
    }
    public void refreshItems(View view){
        refreshItemsFromTable();
    }

    private void refreshItemsFromTable() {
        final Activity mActivity = this;

        @SuppressLint("StaticFieldLeak") // <-- Just to suppress warning
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    final List<Schedule> results = refreshItemsFromMobileServiceTable();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mScheduleAdapter.clear();

                            for (Schedule item : results) {
                                mScheduleAdapter.add(item);
                            }
                        }
                    });
                } catch (final Exception e){
                    ToDialogError.getInstance().createAndShowDialogFromTask(e, "Error", mActivity);
                }

                return null;
            }
        };

        runAsyncTask(task);
    }

    private List<Schedule> refreshItemsFromMobileServiceTable() throws ExecutionException, InterruptedException, MobileServiceException {
        return mTable.execute().get();
    }

    private AsyncTask<Void, Void, Void> initLocalStore() throws MobileServiceLocalStoreException, ExecutionException, InterruptedException {
        final Activity mActivity = this;
        @SuppressLint("StaticFieldLeak") // <-- Just to suppress warning
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    MobileServiceSyncContext syncContext = mClient.getSyncContext();

                    if (syncContext.isInitialized())
                        return null;

                    SQLiteLocalStore localStore = new SQLiteLocalStore(mClient.getContext(), "OfflineStore", null, 1);

                    Map<String, ColumnDataType> tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("startTime", ColumnDataType.String);
                    tableDefinition.put("endTime", ColumnDataType.String);
                    tableDefinition.put("date", ColumnDataType.String);
                    tableDefinition.put("title", ColumnDataType.String);
                    tableDefinition.put("nickName", ColumnDataType.String);
                    tableDefinition.put("gaName", ColumnDataType.String);

                    localStore.defineTable("Schedule", tableDefinition);

                    SimpleSyncHandler handler = new SimpleSyncHandler();

                    syncContext.initialize(localStore, handler).get();

                } catch (final Exception e) {
                    ToDialogError.getInstance().createAndShowDialogFromTask(e, "Error", mActivity);
                }

                return null;
            }
        };

        return runAsyncTask(task);
    }

    public AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    private void initButtonAndProgressBar() {
        mProgressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);
        mProgressBar.setVisibility(ProgressBar.GONE);
        refresh = (Button) findViewById(R.id.refresh);
    }

    private void initMobileService() {
        mAzureAdapter = AzureServiceAdapter.getInstance();
        mAzureAdapter.updateClient(this, this, mProgressBar);
        mClient = mAzureAdapter.getClient();
        mScheduleAdapter = new ScheduleAdapter(this, R.layout.schedule);
        mTable = mClient.getTable(Schedule.class);
    }

    public void goToCreateSchedule(View view){
        Intent intent = new Intent(this, CreateScheduleActivity.class);
        startActivity(intent);
    }

    public void refreshButtonPressed(View view){
        refreshItemsFromTable();
    }
}
