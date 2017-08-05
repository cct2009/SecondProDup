package tk.cct49.secondprodup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class ListTimers extends AppCompatActivity {

    public static final String TIMERNAME[] = {
            "Jumping Rope 12 Mins", "Beginner HIT", "7 Minute Workout","Tabata This","Squid 10 Minutes","Boxing Time",
            "Running 20 Mins","Weight Training","Advance Exercise","Dummy Exercise 10","Dummy Exercise 11","Dummy Exercise 12"
    };
    public static final String TIMERDESC[] =
          { "HiT Timer / 12:00/ 8 Intervals","Circuit Timer / 43:30/ 12 Intervals","Tabata Timer / 37:00/ 21 Intervals","Tabata Timer / 32:50/ 5 Intervals","Compound Timer / 24:00/ 6 Intervals",
            "Tabata Timer / 42:50/ 7 Intervals","Compound Timer / 32:00/ 10 Intervals","Circuit Timer / 25:00/ 13 Intervals","HiT Timer / 12:00/ 8 Intervals","Tabata Timer / 8:00/ 10 Intervals",
            "HiT Timer / 33:30/ 15 Intervals","Circuit Timer / 27:00/ 18 Intervals" };

    private ListView lv;
    int curTimerCounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_timers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        curTimerCounts = getIntent().getIntExtra("timerCounts",1);
        final String packName = getIntent().getStringExtra("packName");
        getSupportActionBar().setTitle(packName);

        lv = (ListView) findViewById(R.id.list);
        ListTimers.AccessoriesAdapter mAdapter = new ListTimers.AccessoriesAdapter();

        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(ListTimers.this, ListTimers.class);
//                intent.putExtra("packName",TIMERNAME[position]);
//                intent.putExtra("timerCounts",TIMERDESC[position]);
//                startActivity(intent);
            }
        });


    }


    private static class AccessoriesViewHolder {
        public TextView timerName;
        public TextView timerDesc;
    }

    /**
     * The Adapter used in the demonstration.
     *
     * @author Cyril Mottier
     */
    private class AccessoriesAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return curTimerCounts;
        }

        @Override
        public String getItem(int position) {
            return TIMERNAME[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ListTimers.AccessoriesViewHolder holder = null;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.timer_pack_item, parent, false);

                holder = new ListTimers.AccessoriesViewHolder();
                holder.timerName = (TextView) convertView.findViewById(R.id.pack_name);
                holder.timerDesc = (TextView) convertView.findViewById(R.id.timer_counts);

                ((ImageButton) convertView.findViewById(R.id.edit_pack)).setOnClickListener(mEditClickListener);

                convertView.setTag(holder);
            } else {
                holder = (ListTimers.AccessoriesViewHolder) convertView.getTag();
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

            holder.timerName.setText(TIMERNAME[position]);
            holder.timerDesc.setText(TIMERDESC[position]);

            return convertView;
        }
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
