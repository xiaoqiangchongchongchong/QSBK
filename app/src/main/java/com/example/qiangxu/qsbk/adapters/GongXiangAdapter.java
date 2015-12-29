package com.example.qiangxu.qsbk.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qiangxu.qsbk.R;
import com.example.qiangxu.qsbk.domain.Suggest;
import com.example.qiangxu.qsbk.utils.getIcon;
import com.example.qiangxu.qsbk.utils.getImage;
import com.example.qiangxu.qsbk.views.CircleTransFormation;
import com.example.qiangxu.qsbk.views.VideoDraw;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by QiangXu on 2015/12/29.
 */
public class GongXiangAdapter extends BaseAdapter {

    private Context context;
    private List<Suggest.ItemsEntity> list;

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
        Suggest.ItemsEntity item = list.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if(item.getUser() != null){
            holder.user_name.setText(item.getUser().getLogin());
            Picasso.with(context).load(getIcon.getIconURL(item.getUser().getId(),
                    item.getUser().getIcon()))
                    .transform(new CircleTransFormation())
                    .into(holder.user_icon);
        }else{
            holder.user_name.setText("匿名用户");
            holder.user_icon.setImageResource(R.mipmap.ic_launcher);
        }
        if(item.getType() != null) {
            if (item.getType().equals("hot")) {
                holder.jingxuan.setText("热门");
                holder.type_icon.setImageResource(R.mipmap.ic_rss_hot);
            }
        }

        holder.content.setText(item.getContent().trim());

//        if(item.getImage() == null && item.getPic_url() != null){
//            System.out.println(item.getPic_url() + "============================");
//            Log.d("text", item.getPic_url());
//
//            Picasso.with(context)
//                    .load(item.getPic_url())
//                    .resize(parent.getWidth()/2, 0)
//                    .placeholder(R.mipmap.ic_launcher)
//                    .error(R.mipmap.ic_launcher)
//                    .into(holder.image);
//        }
        if(item.getImage() == null){
            //Log.d("text", "no Url");
            if(item.getPic_url() != null){
                holder.image.setVisibility(View.VISIBLE);
                Picasso.with(context)
                        .load(item.getPic_url())
                        .resize(parent.getWidth(), 0)
                        .transform(new VideoDraw(context))
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(holder.image);
            }else {
                holder.image.setVisibility(View.INVISIBLE);
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
        return convertView;
    }

    public void addAll(Collection<? extends Suggest.ItemsEntity> collection){
        list.addAll(collection);
        notifyDataSetChanged();
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
        }


    }

}
