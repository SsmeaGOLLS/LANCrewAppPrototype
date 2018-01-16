package eamv.dmu17he.lancrewapp.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import eamv.dmu17he.lancrewapp.R;
import eamv.dmu17he.lancrewapp.model.Schedule;
import eamv.dmu17he.lancrewapp.model.User;

/**
 * Created by smeag on 14-01-2018.
 */

public class ProfileAdapter extends ArrayAdapter<User> {
    Context mContext;
    int mLayoutResourceId;

    public ProfileAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);

        mContext = context;
        mLayoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final User currentItem = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
        }

       row.setTag(currentItem);
        final TextView name = (TextView) row.findViewById(R.id.nameTextView);
        name.setText(currentItem.getName());
        name.setTextSize(30f);
        final TextView number = (TextView) row.findViewById(R.id.numberTextView);
        number.setText(""+currentItem.getPhoneNumber());
        number.setTextSize(30f);
        final TextView nick = (TextView) row.findViewById(R.id.nickTextView);
        nick.setText(currentItem.getNickName());
        nick.setTextSize(30f);
        final TextView crew = (TextView) row.findViewById(R.id.crewTextView);
        crew.setText(currentItem.getCrew());
        crew.setTextSize(30f);


        return row;
    }
}

//Color.rgb(255, 20, 147) - Du er pink