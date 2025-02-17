package com.example.listview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.listview.R;
import com.example.listview.bean.NewsBean;

import java.util.List;

/**
 * 根据type 不同显示不同的item
 * @author: laizhiyu
 * @date: 2025/2/17
 * desc:
 */
public class ListView2Adapter extends BaseAdapter {

    private List<NewsBean> datas;
    private Context context;

    public ListView2Adapter(Context context, List<NewsBean> datas){
        this.context = context;
        this.datas = datas;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 3;  // 没关系
    }

    @Override
    public int getItemViewType(int position) {

        return datas.get(position).getItemType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        int type = getItemViewType(position);
        if (convertView == null){
            holder = new ViewHolder();
            switch (type) {
                case 0:
                    convertView = LayoutInflater.from(context).inflate(R.layout.listview2_world_list_item, parent, false);
                    holder.descriptionText = convertView.findViewById(R.id.ad_news_description);
                    break;
                case 1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.listview2_world_image_list_item, parent, false);
                    holder.image0 = convertView.findViewById(R.id.ad_news_image0);
                    break;
                case 2:
                    convertView = LayoutInflater.from(context).inflate(R.layout.listview2_image_list_item, parent, false);
                    holder.image0 = convertView.findViewById(R.id.ad_news_image0);
                    holder.image1 = convertView.findViewById(R.id.ad_news_image1);
                    holder.image2 = convertView.findViewById(R.id.ad_news_image2);
                    break;

                default:
                    break;

            }

            // 公共的
            holder.line = convertView.findViewById(R.id.ad_news_line);
            holder.nameText = convertView.findViewById(R.id.ad_news_postfrom);
            holder.postFromText = convertView.
                    findViewById(R.id.ad_news_postfrom);
            holder.readViewsText = convertView.
                    findViewById(R.id.ad_news_views);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }


    class ViewHolder{
        TextView nameText;
        TextView descriptionText;
        TextView postFromText;
        TextView readViewsText;
        View line;
        ImageView image0;
        ImageView image1;
        ImageView image2;

    }
}
