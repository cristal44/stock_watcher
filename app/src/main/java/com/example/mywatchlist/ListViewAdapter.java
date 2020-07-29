package com.example.mywatchlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mywatchlist.View.MainActivity;
import com.example.mywatchlist.data.StockName;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<StockName> list;
    private ArrayList<StockName> arrayList;

    public ListViewAdapter(Context context, List<StockName> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(list);
    }

    public class ViewHolder{
        TextView symbol, name;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.symbols, null);

            holder.symbol = view.findViewById(R.id.listViewSymbol);
            holder.name = view.findViewById(R.id.listViewCompanyName);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.symbol.setText(list.get(position).getSymbol());
        holder.name.setText(list.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StockName stockName = list.get(position);
                Intent intent = new Intent(context, MainActivity.class);


                intent.putExtra("SELECTED", stockName);
                context.startActivity(intent);
            }
        });

        return view;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0){
            list.addAll(arrayList);
        } else {
            for (StockName name : arrayList){
                if (name.getSymbol().toLowerCase(Locale.getDefault()).contains(charText) ||
                        name.getName().toLowerCase(Locale.getDefault()).contains(charText) ){
                    list.add(name);
                }
            }
        }

        notifyDataSetChanged();
    }
}
