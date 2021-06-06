package com.example.asia.country;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asia.MainActivity;
import com.example.asia.R;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.ArrayList;

public class contryAdapter extends RecyclerView.Adapter<contryAdapter.viewHolder> {

    ArrayList<countryModel> list;
    Context context;
    public contryAdapter(ArrayList<countryModel> list,Context context){
        this.list=list;
        this.context =context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_country , parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        countryModel model = list.get(position);

      //  holder.flag_ry_img.setImageResource(Integer.parseInt(model.getFlag()));
        holder.count_name.setText(model.getCountry_name());
        holder.count_capital.setText(model.getCountry_capital());
        holder.count_region.setText(model.getCountry_region());
        holder.count_popu.setText(model.getCountry_popul());
        holder.count_lang.setText(model.getCountry_lang());

//        Glide
       /* Glide.with(context)
                .load(model.getFlag())
                .apply(new RequestOptions().override(240,160))
                .into(holder.flag_ry_img);*/
       // Glide.with(context).load(model.getFlag()).into(holder.flag_ry_img);
      //  GlideToVectorYou.justLoadImageAsBackground(, Uri.encode(model.getFlag()),holder.flag_ry_img);
        GlideToVectorYou
                .init()
                .with(this.context)
                .load(Uri.parse(model.getFlag()), holder.flag_ry_img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView flag_ry_img;
        TextView count_name;
        TextView count_capital;
        TextView count_region;
        TextView count_popu;
        TextView count_lang;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            flag_ry_img = itemView.findViewById(R.id.country_flag);
            count_name = itemView.findViewById(R.id.Country_name);
            count_capital= itemView.findViewById(R.id.country_capital);
            count_region= itemView.findViewById(R.id.region_name);
            count_popu= itemView.findViewById(R.id.country_population);
            count_lang= itemView.findViewById(R.id.country_lang);

        }
    }
}
