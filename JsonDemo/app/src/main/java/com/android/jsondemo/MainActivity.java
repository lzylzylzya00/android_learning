package com.android.jsondemo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.jsondemo.bean.Clothing;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        json();
        gson();

    }


    private void json() {
        // ------------------json的创建begin--------------------
        // 1.字符串创建
        String jsonStr = "{\"name\":\"T恤\",\"price\":99.9,\"tags\":[\"秋冬\",\"加绒\",\"宽松\"],\"details\":{\"color\":\"深灰色\",\"size\":\"XL\"}}";
        try {
            JSONObject strJsonObject = new JSONObject(jsonStr);
            Log.d(TAG, " jsonObject =" + strJsonObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        // 2.手动创建
        JSONObject handsObject = new JSONObject();
        try {
            // 创建json数组
            JSONArray jsonArray = new JSONArray();
            jsonArray.put("秋冬");
            jsonArray.put("加绒");
            jsonArray.put("宽松");

            //创建 details json对象
            JSONObject detailsObject = new JSONObject();
            detailsObject.put("color", "深灰色");
            detailsObject.put("size", "XL");

            // 创建handsObject 对象
            handsObject.put("name", "T恤");
            handsObject.put("price", 99.9);
            handsObject.put("tags", jsonArray);
            handsObject.put("details", detailsObject);

            Log.d(TAG, " handsObject = " + handsObject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        // ------------------json的创建end--------------------

        // ------------------json的读取字段begin--------------------
        // 字段 有 String jsonStr = "{\"name\":\"T恤\",\"price\":99.9,\"tags\":[\"秋冬\",\"加绒\",\"宽松\"],\"details\":{\"color\":\"深灰色\",\"size\":\"XL\"}}"
        try {
            // 取字符串
            String name = handsObject.getString("name");
            Log.d(TAG, " name =" + name);

            // 取数字
            double price = handsObject.getDouble("price");
            Log.d(TAG, " price =" + price);

            // 取数组
            JSONArray tags = handsObject.getJSONArray("tags");
            for (int i = 0; i < tags.length(); i++) {
                Log.d(TAG, " tags[" + i + "]" + tags.getString(i));
            }
            Object tags_0 = tags.get(0);
            Object tags_1 = tags.get(1);
            Object tags_2 = tags.get(2);


            // 取嵌套对象
            JSONObject details = handsObject.getJSONObject("details");
            String color = details.getString("color");
            String size = details.getString("size");
            Log.d(TAG, " color =" + color);
            Log.d(TAG, " size =" + size);
            // ------------------json的读取字段end--------------------

            /**
             *  总结
             * .getString("key")
             * 获取字符串
             * 是（key 不存在或类型错）
             *
             * .getDouble("key")
             * 获取 double
             * 是
             *
             * .getBoolean("key")
             * 获取布尔值
             * 是
             *
             * .getJSONObject("key")
             * 获取嵌套对象
             * 是
             *
             * .getJSONArray("key")
             * 获取数组
             * 是
             *
             * .has("key")
             * 判断是否存在该 key
             * 否
             *
             * .isNull("key")
             * 判断值是否为 JSON null
             * 否
             */

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }

    private void gson() {
        // JSON 字符串 → Java 对象
        String jsonStr = "{\"name\":\"T恤\",\"price\":99.9,\"tags\":[\"秋冬\",\"加绒\",\"宽松\"],\"details\":{\"color\":\"深灰色\",\"size\":\"XL\"}}";
        Gson gson = new Gson();
        Clothing clothing = gson.fromJson(jsonStr, Clothing.class);
        Log.d(TAG, "clothing.getName() " + clothing.getName());
        Log.d(TAG, "clothing.getPrice() " + clothing.getPrice());

        Log.d(TAG, "clothing.getTags()[0] " + clothing.getTags()[0]);
        Log.d(TAG, "clothing.getTags()[1] " + clothing.getTags()[1]);
        Log.d(TAG, "clothing.getTags()[2] " + clothing.getTags()[2]);

        Log.d(TAG, "clothing.getDetails().getColor() " + clothing.getDetails().getColor());
        Log.d(TAG, "clothing.getDetails().getSize() " + clothing.getDetails().getSize());


        // Java 对象 → JSON 字符串（生成）
        String json = gson.toJson(clothing);
        Log.d(TAG," json = " + json);

        /**
         * 注意 ： Clothing字段要和jsonStr的key一样且区分大小写，不一样会赋值不了
         */

        // 解析泛型集合 List<Clothing>
        String jsonArry = "[{\"name\":\"T恤\"}, {\"name\":\"卫衣\"}]";
        Type listType = new TypeToken<List<Clothing>>() {}.getType();
        List<Clothing> clothingList = gson.fromJson(jsonArry,listType);
        Clothing clothing1 = clothingList.get(0);
        Clothing clothing2 = clothingList.get(1);
        Log.d(TAG, "clothing1.getName() =" + clothing1.getName());
        Log.d(TAG, "clothing1.getPrice() =" + clothing1.getPrice());
        Log.d(TAG, "clothing1.getDetails() =" + clothing1.getDetails());

        Log.d(TAG, "clothing2.getName() =" + clothing2.getName());
        Log.d(TAG, "clothing2.getPrice() =" + clothing2.getPrice());

        // 解析泛型集合 Map<String, Clothing>
        String jsonMap = "{\"Map1\":{\"name\":\"T恤2\"}, \"Map2\":{\"name\":\"卫衣\"}}";
        Type mapType = new TypeToken<Map<String, Clothing>>(){}.getType();
        Map<String, Clothing> map = gson.fromJson(jsonMap, mapType);
        Set<String> strings = map.keySet();
        Log.d(TAG, "gson: strings =" + strings.toString());
        Log.d(TAG, "map.get(Map1).getName() = " + map.get("Map1").getName());


    }

}