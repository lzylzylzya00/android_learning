package com.example.listview.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.listview.R;
import com.example.listview.bean.GoodsBean;

import java.util.List;

/**
 * @author: laizhiyu
 * @date: 2025/2/17
 * desc:
 */
public class ListView3Adapter extends BaseAdapter {

    private List<GoodsBean> datas;
    private Context context;
    private PricesListener pricesListener;

    public ListView3Adapter(Context context, List<GoodsBean> datas){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            // 绑定view
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.listview3_list_item,parent,false);
            holder.checkBox = convertView.findViewById(R.id.ad_goods_checkbox);
            holder.addTxt = convertView.findViewById(R.id.ad_goods_add);
            holder.minusTxt = convertView.findViewById(R.id.ad_goods_minus);
            holder.editText = convertView.findViewById(R.id.ad_goods_edit);
            holder.priceTxt = convertView.findViewById(R.id.ad_goods_price);

            // 设置监听
            holder.addTxt.setOnClickListener(holder.addClickListener); // +
            holder.minusTxt.setOnClickListener(holder.minusClickListener); // -
            holder.editText.addTextChangedListener(holder.textWatcher);
            holder.checkBox.setOnCheckedChangeListener(holder.checkBoxChangeListener);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }


        GoodsBean goodsBean = datas.get(position);


        holder.priceTxt.setText("价格："+goodsBean.getPrice());
        holder.goodsBean = goodsBean;
        holder.editText.setText(goodsBean.getNumber()+"");
        holder.editText.setSelection((goodsBean.getNumber()+"").length());

        holder.checkBox.setChecked(goodsBean.isChoosed());

        return convertView;
    }


    class ViewHolder{
        CheckBox checkBox;
        TextView addTxt;
        TextView minusTxt;
        EditText editText;
        TextView priceTxt;

        AddClickListener addClickListener;
        MinusClickListener minusClickListener;
        NumberTextWatcher textWatcher;
        CheckBoxChangeListener checkBoxChangeListener;

        GoodsBean goodsBean;

        public ViewHolder(){
            addClickListener = new AddClickListener();
            minusClickListener = new MinusClickListener();
            textWatcher = new NumberTextWatcher();
            checkBoxChangeListener = new CheckBoxChangeListener();
        }


        class AddClickListener implements View.OnClickListener{

            @Override
            public void onClick(View v) {
                if (goodsBean == null){
                    return;
                }

                goodsBean.setNumber(goodsBean.getNumber() + 1);

                if (checkBox.isChecked() && pricesListener != null){
                    pricesListener.totalPrices();
                }

                notifyDataSetChanged();
            }
        }


        class MinusClickListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                if (goodsBean == null){
                    return;
                }

                int number = goodsBean.getNumber();
                if (number > 1){
                    goodsBean.setNumber(number -1);
                    if (checkBox.isChecked() && pricesListener != null){
                        pricesListener.totalPrices();
                    }
                }else {
                    return;
                }

                notifyDataSetChanged();


            }
        }

        class NumberTextWatcher implements TextWatcher{

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int number=0;
                try {
                    number = Integer.parseInt(s.toString());
                }catch (Exception e){

                }

                goodsBean.setNumber(number);
                if (checkBox.isChecked() && pricesListener != null){
                    pricesListener.totalPrices();
                }
            }
        }

        class  CheckBoxChangeListener implements CompoundButton.OnCheckedChangeListener {


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                goodsBean.setChoosed(isChecked);
                if (pricesListener!=null){
                    pricesListener.totalPrices();
                }

            }
        }

    }


    public void setPricesListener(PricesListener pricesListener) {
        this.pricesListener = pricesListener;
    }
    public interface PricesListener{
        void totalPrices();
    }
}
