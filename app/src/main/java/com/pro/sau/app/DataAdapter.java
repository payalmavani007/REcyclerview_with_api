package com.pro.sau.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
Context context;
    JSONArray jsonArray;
    String name, picture;
    private static final String TAG = "DataAdapter";



    public DataAdapter(Context context, JSONArray jsonArray) {
        this.context= context;
        this.jsonArray = jsonArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.data, viewGroup, false);
        return new DataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {

        try {
            JSONObject o = (JSONObject) jsonArray.get(position);
           String name = (String) o.get("name");
            Log.d(TAG, "onBindViewHolder: "+name);
           String picture = (String) o.get("picture");
            holder.txt.setText(name);
            Picasso.get().load("http://192.168.1.200/Mr.Gill/public/uploads/"+picture)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.img);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txt = itemView.findViewById(R.id.txt);
        }
    }
}
