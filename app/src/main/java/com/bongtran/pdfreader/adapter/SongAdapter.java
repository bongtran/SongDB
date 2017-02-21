package com.bongtran.pdfreader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bongtran.pdfreader.R;
import com.bongtran.pdfreader.model.SongModel;

import java.util.ArrayList;

/**
 * Created by ASUS on 2/21/2017.
 */

public class SongAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<SongModel> data = new ArrayList<>();

    public Context getContext() {
        return context;
    }

    public void setContext(Context mContext) {
        this.context = mContext;
    }

    @Override
    public void clear() {
        super.clear();
        data.clear();
    }

    public SongAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        setContext(context);
    }


    public void addItem(SongModel object){
        data.add(object);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        data.remove(position);
        notifyDataSetChanged();
    }

    public ArrayList<SongModel> getData(){
        return data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final SongModel item = getItem(position);
        View row = convertView;
        Holder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(R.layout.view_item_song, parent, false);
            holder = new Holder();
            holder.name = (TextView) row.findViewById(R.id.tv_item_song_name);
//            holder.description = (TextView) row.findViewById(R.id.tv_description);
            holder.llItem = (LinearLayout) row.findViewById(R.id.ll_item);
//            holder.checkbox = (CheckBox) row.findViewById(R.id.cb_category);
//            holder.image = (ImageView) row.findViewById(R.id.img_category);
            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }

//        if (!TextUtils.isEmpty(item.getImageUrl())) {
//            DownloadImageAsyncTask task = new DownloadImageAsyncTask(holder.image, R.drawable.ic_nopic_40);
//            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,item.getImageUrl());
//        } else {
//            holder.image.setVisibility(View.GONE);
//        }

        holder.name.setText(item.getName());

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _listener.onClick(position, item);
            }
        });

        return row;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public SongModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class Holder {
        TextView name;
        TextView description;
//        CheckBox checkbox;
        LinearLayout llItem;
//        ImageView image;
    }

    protected SongListener _listener;

    public void setTaskListener(SongListener listener) {
        _listener = listener;
    }

    public interface SongListener {

        void onClick(int position, SongModel object);

    }
}
