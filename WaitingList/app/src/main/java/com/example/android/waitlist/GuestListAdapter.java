package com.example.android.waitlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.waitlist.data.WaitlistContract;


public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {


    private Cursor mCursor;
    private Context mContext;


    public GuestListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.guest_list_item, parent, false);
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position))
            return;

        String name = mCursor.getString(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME));
        int partySize = mCursor.getInt(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE));

        long id = mCursor.getLong(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry._ID));

        holder.nameTextView.setText(name);
        holder.partySizeTextView.setText(String.valueOf(partySize));
        holder.itemView.setTag(id);

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(mContext);
        String color=sharedPreferences.getString("color","");

        if(color.equals("blue")){
            GradientDrawable gradientDrawable = (GradientDrawable)holder.partySizeTextView.getBackground().mutate();
            gradientDrawable.setColor(Color.BLUE);
        }else if (color.equals("green")){
            GradientDrawable gradientDrawable = (GradientDrawable)holder.partySizeTextView.getBackground().mutate();
            gradientDrawable.setColor(Color.GREEN);
        }else {
            GradientDrawable gradientDrawable = (GradientDrawable)holder.partySizeTextView.getBackground().mutate();
            gradientDrawable.setColor(Color.RED);
        }
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {

        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {

            this.notifyDataSetChanged();
        }
    }


    class GuestViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;

        TextView partySizeTextView;

        public GuestViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            partySizeTextView = (TextView) itemView.findViewById(R.id.party_size_text_view);
        }

    }
}