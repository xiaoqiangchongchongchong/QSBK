package com.example.qiangxu.qsbk.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiangxu.qsbk.DetailActivity;
import com.example.qiangxu.qsbk.R;
import com.example.qiangxu.qsbk.domain.Suggest;
import com.example.qiangxu.qsbk.utils.getIcon;
import com.example.qiangxu.qsbk.utils.getImage;
import com.example.qiangxu.qsbk.views.CircleTransFormation;
import com.example.qiangxu.qsbk.views.VideoDraw;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by QiangXu on 2015/12/29.
 */
public class GongXiangAdapter extends BaseAdapter implements  View.OnClickListener {

    private Context context;
    private List<Suggest.ItemsEntity> list;
    private Suggest.ItemsEntity item;

    public GongXiangAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        LinearLayout clickToDetailLayout = (LinearLayout) convertView.findViewById(R.id.clickToDetail);
        ImageButton pingLunClick = (ImageButton) convertView.findViewById(R.id.btnpinglun);
        pingLunClick.setTag(position);
        pingLunClick.setOnClickListener(this);

        clickToDetailLayout.setTag(position);
        clickToDetailLayout.setOnClickListener(this);
        item = list.get(position);

        final ViewHolder holder = (ViewHolder) convertView.getTag();
        if(item.getUser() != null){
            holder.user_name.setText(item.getUser().getLogin());
            if(item.getUser().getIcon() != "") {
                Picasso.with(context).load(getIcon.getIconURL(item.getUser().getId(),
                        item.getUser().getIcon()))
                        .transform(new CircleTransFormation())
                        .into(holder.user_icon);
            }else{
                Picasso.with(context).load(R.mipmap.ic_launcher)
                        .transform(new CircleTransFormation())
                        .into(holder.user_icon);
            }

        }else{
            holder.user_name.setText("匿名用户");
            Picasso.with(context).load(R.mipmap.ic_launcher)
                    .transform(new CircleTransFormation())
                    .into(holder.user_icon);
        }
        if(item.getType() != null) {
            if (item.getType().equals("hot")) {
                holder.jingxuan.setText("热门");
                holder.type_icon.setImageResource(R.mipmap.ic_rss_hot);
            }
        }

        holder.content.setText(item.getContent().trim());
        if(item.getImage() == null){
            if(item.getPic_url() == null){
                holder.image.setVisibility(View.GONE);
            }else {
                holder.image.setVisibility(View.VISIBLE);
                Picasso.with(context)
                        .load(item.getPic_url())
                        .resize(parent.getWidth(), 0)
                        .transform(new VideoDraw(context))
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(holder.image);
            }
        }else{
            holder.image.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(getImage.getImageURL((String) item.getImage()))
                    .resize(parent.getWidth(), 0)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.image);
        }
        holder.pinglun.setText(item.getComments_count() + "");
        holder.fenxiang.setText(item.getShare_count() + "");



        holder.btnhaoxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btnhaoxiao.setImageResource(R.mipmap.operation_support_press);
                holder.btnbuhaoxiao.setImageResource(R.mipmap.operation_unsupport);
            }
        });

        holder.btnbuhaoxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btnhaoxiao.setImageResource(R.mipmap.operation_support);
                holder.btnbuhaoxiao.setImageResource(R.mipmap.operation_unsupport_press);
            }
        });
        return convertView;
    }

    public void addAll(Collection<? extends Suggest.ItemsEntity> collection){
        list.addAll(collection);
        notifyDataSetChanged();
    }

    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        int Clickposition = (int) v.getTag();
        bundle.putSerializable("item", list.get(Clickposition));
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private static class ViewHolder{

        private ImageView user_icon;
        private TextView user_name;
        private TextView jingxuan;
        private TextView content;
        private ImageView image;
        private ImageView type_icon;
        private TextView haoxiao;
        private TextView pinglun;
        private TextView fenxiang;
        private ImageButton btnhaoxiao;
        private ImageButton btnbuhaoxiao;

        public ViewHolder(View itemView){
            user_icon = (ImageView) itemView.findViewById(R.id.user_icon);
            user_name = (TextView) itemView.findViewById(R.id.user_name);
            jingxuan = (TextView) itemView.findViewById(R.id.jingxuan);
            content = (TextView) itemView.findViewById(R.id.content);
            image = (ImageView) itemView.findViewById(R.id.image);
            type_icon = (ImageView) itemView.findViewById(R.id.type_icon);
            haoxiao = (TextView) itemView.findViewById(R.id.haoxiao);
            pinglun = (TextView) itemView.findViewById(R.id.pinglun);
            fenxiang = (TextView) itemView.findViewById(R.id.fenxiang);
            btnhaoxiao = (ImageButton) itemView.findViewById(R.id.btnhaoxiao);
            btnbuhaoxiao = (ImageButton) itemView.findViewById(R.id.btnbuhaoxiao);
        }


    }

}
