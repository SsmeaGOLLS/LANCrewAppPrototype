package eamv.dmu17he.lancrewappprototype;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceException;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

public class MessagesStartActivity extends AppCompatActivity
{

    private messageAdapter mAdapter;
    private UserAzureDatabase theDB;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_start);

        mAdapter = new messageAdapter(this, R.layout.message_inflatable);
        ListView mListView = (ListView)findViewById(R.id.messageListView);
        mListView.setAdapter(mAdapter);

        //get database
        theDB=UserAzureDatabase.getDatabase(this);

        //load adapter items from database
        new refreshItemsInAdapterTask().execute("");

    }

    private class refreshItemsInAdapterTask extends AsyncTask<String, Void, Void>

    {
        @Override
        protected Void doInBackground(String... params)
        {
            try
            {
                final List<messageAzureDBEntity> temp=theDB.messageTable.execute().get();

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        mAdapter.clear();

                        for (messageAzureDBEntity item : temp) {
                            mAdapter.add(item);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });

            }
            catch (InterruptedException e)
            {

            }
            catch (ExecutionException e)
            {

            } catch (MobileServiceException e)
            {

            }


            return null;
        }

    }

    public void newMessagePressed(View view)
    {
        theDB=UserAzureDatabase.getDatabase(this);
        final messageAzureDBEntity newMes=new messageAzureDBEntity();
        newMes.sentByName="Torben";
        newMes.crewGroupType="Kitchen Crew";
        newMes.theMessage="Tag en stor fed banan og stik den i røven!";

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final messageAzureDBEntity entity = addMessageToDB(newMes);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(!entity.isComplete()){
                                mAdapter.add(entity);
                            }
                        }
                    });
                } catch (final Exception e) {

                }
                return null;
            }
        };
        task.execute();
    }

    public messageAzureDBEntity addMessageToDB(messageAzureDBEntity theMessage) throws ExecutionException, InterruptedException
    {


            messageAzureDBEntity temp=theDB.messageTable.insert(theMessage).get();
        return temp;
    }
}
