package tk.cct49.secondprodup;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TimerRun extends AppCompatActivity {

    private static class TimerBox {
        public String workoutName;
        public int seconds;
        public int color;

        public TimerBox(String box,int sec, int col)
        {
            workoutName = box; seconds = sec; color = col;
        }
    }
    private TimerRun.TimerBox[]  timerBox;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_run);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        // my <code>
        InitTimerBox();

        AccessoriesAdaptor mAdapter = new AccessoriesAdaptor();

        list = (ListView) findViewById(R.id.timeList);
        list.setAdapter(mAdapter);
        list.setDivider(new ColorDrawable(Color.WHITE));
        list.setDividerHeight(3);
    }


    private void InitTimerBox() {
        timerBox  = new TimerRun.TimerBox[10];
        timerBox[0] = new TimerRun.TimerBox("Stretch",60, Color.RED);
        timerBox[1] = new TimerRun.TimerBox("Stretch",60, Color.RED);
        timerBox[2] = new TimerRun.TimerBox("Stretch",66, Color.RED);
        timerBox[3] = new TimerRun.TimerBox("Warmup",120, Color.BLACK);
        timerBox[4] = new TimerRun.TimerBox("Jumping Rope",720, Color.GREEN);
        timerBox[5] = new TimerRun.TimerBox("Rest",120, Color.CYAN);
        timerBox[6] = new TimerRun.TimerBox("Jumping Rope",720, Color.GREEN);
        timerBox[7] = new TimerRun.TimerBox("Rest",120, Color.CYAN);
        timerBox[8] = new TimerRun.TimerBox("Jumping Rope",720, Color.GREEN);
        timerBox[9] = new TimerRun.TimerBox("Rest",120, Color.CYAN);
    }

    private class ViewHolder {
        public TextView workoutName;
        public TextView timeCount;
    }
    private class AccessoriesAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return timerBox[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_timer_run, parent,false);

                holder = new ViewHolder();
                holder.workoutName = (TextView) convertView.findViewById(R.id.workout_name);
                holder.timeCount = (TextView) convertView.findViewById(R.id.time_count);
                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.workoutName.setText(timerBox[position].workoutName);
            holder.timeCount.setText(String.format("%02d:%02d",timerBox[position].seconds/60,timerBox[position].seconds%60));
            convertView.setBackgroundColor(timerBox[position].color);

            return convertView;
        }
    }
}
