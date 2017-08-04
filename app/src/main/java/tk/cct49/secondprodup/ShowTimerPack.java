package tk.cct49.secondprodup;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowTimerPack extends ListActivity {

    private static final String STAR_STATES = "listviewtipsandtricks:star_states";
    public static final String CHEESES[] = {
            "My Timer multiple line abasdfasfdasfa  1123234234  new line and you", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
            "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan"

    };
    private boolean[] mStarStates;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_timer_pack);

        // The following code allows the Activity to restore its state after it
        // has been killed by the system (low memory condition, configuration
        // change, etc.)
        if (savedInstanceState != null) {
//            mStarStates = savedInstanceState.getBooleanArray(STAR_STATES);
        } else {
//            mStarStates = new boolean[CHEESES.length];
        }

        AccessoriesAdapter mAdapter = new AccessoriesAdapter();
        setListAdapter(mAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putBooleanArray(STAR_STATES, mStarStates);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        showMessage(getString(R.string.you_want_info_about_format, CHEESES[position]));
    }

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
            return CHEESES.length;
        }

        @Override
        public String getItem(int position) {
            return CHEESES[position];
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
                ImageButton img = (ImageButton) convertView.findViewById(R.id.edit_pack);

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

            holder.packName.setText(CHEESES[position]);
            holder.timerCounts.setText("1 Times");

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
            final int position = getListView().getPositionForView(v);
            if (position != ListView.INVALID_POSITION) {
                showMessage(getString(R.string.you_want_to_buy_format, CHEESES[position]));
            }
        }
    };


}
