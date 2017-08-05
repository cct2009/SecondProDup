package tk.cct49.secondprodup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

// 8/4/2017 - 6:12PM
// change ShowTimerPack to extend from AppCompatActivity
public class ShowTimerPack extends AppCompatActivity {

    public static final String PACKNAME[] = {
            "My Timers", "Sample Timers", "Dummy 3","Dummy 4","Dummy multiline \nSecond line ,I will clone this app and make it the first app","Dummy6",
            "Dummy 7","Dummy 8 Wow \\n can use for new line good.\nSecond Line again","Dummy9"
    };
    public static final int TIMERCOUNTS[] = { 5, 1,4,4,10,12,3,2,6};
    // private boolean[] mStarStates;
    private ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_timer_pack);
         lv = (ListView) findViewById(R.id.list);
        // The following code allows the Activity to restore its state after it
        // has been killed by the system (low memory condition, configuration
        // change, etc.)
        if (savedInstanceState != null) {
//            mStarStates = savedInstanceState.getBooleanArray(STAR_STATES);
        } else {
//            mStarStates = new boolean[CHEESES.length];
        }

        AccessoriesAdapter mAdapter = new AccessoriesAdapter();

        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShowTimerPack.this, ListTimers.class);
                intent.putExtra("packName",PACKNAME[position]);
                intent.putExtra("timerCounts",TIMERCOUNTS[position]);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putBooleanArray(STAR_STATES, mStarStates);
    }
/*
        private void onListItemClick(ListView l, View v, int position, long id) {
            showMessage(getString(R.string.you_want_info_about_format, CHEESES[position]));
        }
      */
    /**
     * A pretty basic ViewHolder used to keep references on children
     * {@link View}s.
     *
     * @author Cyril Mottier
     */
    private static class AccessoriesViewHolder {
        public TextView packName;
        public TextView timerCounts;
    }

    /**
     * The Adapter used in the demonstration.
     *
     * @author Cyril Mottier
     */
    private class AccessoriesAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return PACKNAME.length;
        }

        @Override
        public String getItem(int position) {
            return PACKNAME[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            AccessoriesViewHolder holder = null;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.timer_pack_item, parent, false);

                holder = new AccessoriesViewHolder();
                holder.packName= (TextView) convertView.findViewById(R.id.pack_name);
                holder.timerCounts= (TextView) convertView.findViewById(R.id.timer_counts);

                ((ImageButton) convertView.findViewById(R.id.edit_pack)).setOnClickListener(mEditClickListener);

                convertView.setTag(holder);
            } else {
                holder = (AccessoriesViewHolder) convertView.getTag();
            }

            /*
             * The Android API provides the OnCheckedChangeListener interface
             * and its onCheckedChanged(CompoundButton buttonView, boolean
             * isChecked) method. Unfortunately, this implementation suffers
             * from a big problem: you can't determine whether the checking
             * state changed from code or because of a user action. As a result
             * the only way we have is to prevent the CheckBox from callbacking
             * our listener by temporary removing the listener.
             */
            //   holder.star.setOnCheckedChangeListener(null);

            holder.packName.setText(PACKNAME[position]);
            StringBuilder s= new StringBuilder(20);
            s.append(TIMERCOUNTS[position]);
            s.append(" Time");
            if (TIMERCOUNTS[position] > 1 ) s.append("s");
            holder.timerCounts.setText(s);

            return convertView;
        }
    }

    /**
     * Quickly shows a message to the user using a {@link Toast}.
     *
     * @param message The message to show
     */
    private void showMessage(String message) {
        Toast.makeText(ShowTimerPack.this, message, Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener mEditClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int position = lv.getPositionForView(v);
            if (position != ListView.INVALID_POSITION) {

            }
        }
    };


}
