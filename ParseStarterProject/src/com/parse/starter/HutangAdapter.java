package com.parse.starter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by rahadiankumang on 5/19/15.
 */
public class HutangAdapter  extends BaseAdapter {

    LayoutInflater inflater;
    List<ParseObject> hutangs;
    int mode;

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setHutangs(List<ParseObject> hutangs) {
        this.hutangs = hutangs;
        notifyDataSetChanged();
    }

    public HutangAdapter(LayoutInflater layoutInflater) {
        this.inflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return hutangs.size();
    }

    @Override
    public Object getItem(int i) {
        return hutangs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.hutangrow, null);
            HutangRowHolder rowHolder = new HutangRowHolder(view);
            view.setTag(rowHolder);
        }

        HutangRowHolder rowHolder = (HutangRowHolder)view.getTag();
        ParseObject hutang = (ParseObject)getItem(i);
        if (mode == 2) {
            rowHolder.captionNama.setText(hutang.getParseObject("yangDipinjam").getString("fullname"));
        } else {
            rowHolder.captionNama.setText(hutang.getParseObject("yangMeminjam").getString("fullname"));
        }
        rowHolder.captionTotal.setText("Rp. " + hutang.getNumber("jumlah") + "");

        return view;
    }

    class HutangRowHolder {
        TextView captionNama, captionTotal, captionTanggal;
        public HutangRowHolder(View v) {
            captionNama = (TextView)v.findViewById(R.id.captionNama);
            captionTotal = (TextView)v.findViewById(R.id.captionTotal);
            captionTanggal = (TextView)v.findViewById(R.id.captionTanggal);
        }
    }
}
