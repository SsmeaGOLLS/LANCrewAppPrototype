package eamv.dmu17he.lancrewappprototype;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hkkrestlessPC on 16-01-2018.
 */

public class messageAdapter extends ArrayAdapter<messageAzureDBEntity>
{
    Context mContext;
    int mLayoutResourceId;

    public messageAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);
        mContext = context;
        mLayoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final messageAzureDBEntity currentItem = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
        }

        row.setTag(currentItem);
        final TextView sentBy = (TextView) row.findViewById(R.id.sentByText);
        sentBy.setText(currentItem.sentByName);
        final TextView group = (TextView) row.findViewById(R.id.groupTypeText);
        group.setText(currentItem.crewGroupType);
        final TextView mes = (TextView) row.findViewById(R.id.messageText);
        mes.setText(currentItem.theMessage);




        return row;
    }

}
