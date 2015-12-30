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
import com.example.qiangxu.qsbk.domain.Common;
import com.example.qiangxu.qsbk.domain.Suggest;
import com.example.qiangxu.qsbk.utils.GetDate;
import com.example.qiangxu.qsbk.utils.getIcon;
import com.example.qiangxu.qsbk.views.CircleTransFormation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by QiangXu on 2015/12/30.
 */
public class CommonAdapter extends BaseAdapter {

    private Context context;
    private List<Common.ItemsEntity> list;
    private Common.ItemsEntity item;

    public CommonAdapter(Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.detail_common_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }

        item = list.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();

        if(item.getUser() != null){
            holder.common_user_name.setText(item.getUser().getLogin());
            if(item.getUser().getIcon() != "") {
                Picasso.with(context).load(getIcon.getIconURL(item.getUser().getId(),
                        item.getUser().getIcon()))
                        .transform(new CircleTransFormation())
                        .into(holder.common_user_icon);
            }else{
                Picasso.with(context).load(R.mipmap.ic_launcher)
                        .transform(new CircleTransFormation())
                        .into(holder.common_user_icon);
            }

        }else{
            holder.common_user_name.setText("匿名用户");
            Picasso.with(context).load(R.mipmap.ic_launcher)
                    .transform(new CircleTransFormation())
                    .into(holder.common_user_icon);
        }
        holder.common_content.setText(item.getContent().trim());
        String createTime = String.valueOf(item.getCreated_at());
        String timeLong = GetDate.getStandardDate(createTime);
        holder.common_time.setText(timeLong);
        String likeCount = String.valueOf(item.getLike_count());
        holder.dianzan_count.setText(likeCount);
        return convertView;
    }

    public void addAll(Collection<? extends Common.ItemsEntity> collection){
        list.addAll(collection);
        notifyDataSetChanged();
    }

    private static class ViewHolder{
        private ImageView common_user_icon;
        private TextView common_user_name;
        private TextView common_content;
        private TextView common_time;
        private TextView dianzan_count;

        public ViewHolder(View itemView){
            common_user_icon = (ImageView) itemView.findViewById(R.id.common_user_icon);
            common_user_name = (TextView) itemView.findViewById(R.id.common_user_name);
            common_content = (TextView) itemView.findViewById(R.id.common_content);
            common_time = (TextView) itemView.findViewById(R.id.common_time);
            common_time= (TextView) itemView.findViewById(R.id.common_time);
            dianzan_count = (TextView) itemView.findViewById(R.id.dianzan_count);
        }

    }

}
