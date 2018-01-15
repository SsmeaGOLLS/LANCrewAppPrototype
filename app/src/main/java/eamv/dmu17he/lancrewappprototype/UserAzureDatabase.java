package eamv.dmu17he.lancrewappprototype;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.query.Query;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOperations;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

/**
 * Created by hkkrestlessPC on 13-01-2018.
 */

public class UserAzureDatabase
{
    private static MobileServiceClient mClient;
    public static MobileServiceTable<userAzureDBEntity> mToDoTable;

    private static UserAzureDatabase Instance;

    public static UserAzureDatabase getDatabase(Context context)
    {
        if(Instance==null)
        {
            Instance =new UserAzureDatabase();
            setUpDatabase(context);
        }
        return Instance;
    }

    private static void setUpDatabase(Context context)
    {
        try
        {
            //create mobile service client
            mClient = new MobileServiceClient("https://lancrewappbackend.azurewebsites.net", context);

            if(mClient==null)
            {
                Log.wtf("nu", "no client");
            }
            else
            {
                Log.wtf("nu", "client");
            }
            initLocalStore().get();
            // create the sync table
            mToDoTable = mClient.getTable("userAzureDBEntity", userAzureDBEntity.class);

            if(mToDoTable==null)
            {
                Log.wtf("nu", "no table");
            }
            else
            {
                Log.wtf("nu", "table");
            }

            //set up local storage
            //initLocalStore().get();
        }
        catch (MalformedURLException e)
        {
            Log.wtf("nu", "malformed");
        }
        catch (Exception e)
        {
            Log.wtf("nu", "exception");
        }
    }

    private static AsyncTask<Void, Void, Void> initLocalStore() throws MobileServiceLocalStoreException, ExecutionException, InterruptedException {

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    MobileServiceSyncContext syncContext = mClient.getSyncContext();

                    if (syncContext.isInitialized())
                        return null;

                    SQLiteLocalStore localStore = new SQLiteLocalStore(mClient.getContext(), "OfflineAzureDatabase", null, 1);

                    Map<String, ColumnDataType> tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("userName", ColumnDataType.String);
                    tableDefinition.put("userPassword", ColumnDataType.String);
                    tableDefinition.put("complete", ColumnDataType.Boolean);

                    localStore.defineTable("Users", tableDefinition);

                    SimpleSyncHandler handler = new SimpleSyncHandler();

                    syncContext.initialize(localStore, handler).get();

                } catch (final Exception e) {

                }

                return null;
            }
        };

        return runAsyncTask(task);
    }

   /* private static AsyncTask<Void, Void, Void> sync() {
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    MobileServiceSyncContext syncContext = mClient.getSyncContext();
                    syncContext.push().get();
                    mToDoTable.pull(null).get();
                } catch (final Exception e) {

                }
                return null;
            }
        };
        return runAsyncTask(task);
    }*/

    private static AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    public void addUser(final userAzureDBEntity newUser)
    {
        mToDoTable.insert(newUser);
    }

   /* public userAzureDBEntity addItemInTable(userAzureDBEntity item) throws ExecutionException, InterruptedException {
        userAzureDBEntity entity = mToDoTable.insert(item).get();
        return entity;
    }*/

    public MobileServiceList<userAzureDBEntity> getUsers() throws ExecutionException, InterruptedException
    {
        /*sync().get();
        Query query = QueryOperations.field("complete").
                eq(val(false));
        return mToDoTable.read(query).get();*/
        /*return mToDoTable.where().field("complete").
                eq(val(false)).execute().get();*/
        /*List<userAzureDBEntity> tempList=new ArrayList<userAzureDBEntity>();
        mToDoTable.lookUp("")*/
        /*AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTable();

                    //Offline Sync
                    final List<userAzureDBEntity> results = refreshItemsFromMobileServiceTableSyncTable();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.clear();

                            for (ToDoItem item : results) {
                                mAdapter.add(item);
                            }
                        }
                    });
                } catch (final Exception e){

                }

                return null;
            }
        };

        runAsyncTask(task);*/
        return null;
    }

    public void refreshItemsFromTable(final List<userAzureDBEntity> foundUsers) {

        // Get the items that weren't marked as completed and add them in the
        // adapter

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    final List<userAzureDBEntity> results = refreshItemsFromMobileServiceTable();
                    foundUsers.addAll(results);
                    //Offline Sync
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTableSyncTable();
                    for(userAzureDBEntity test : results)
                    {
                        Log.wtf("nu", "user: "+test.uName);
                    }
                } catch (final Exception e){

                }

                return null;
            }
        };

        runAsyncTask(task);
    }

    private List<userAzureDBEntity> refreshItemsFromMobileServiceTable() throws ExecutionException, InterruptedException
    {
        return mToDoTable.where().field("complete").
                eq(val(false)).execute().get();
    }
}
