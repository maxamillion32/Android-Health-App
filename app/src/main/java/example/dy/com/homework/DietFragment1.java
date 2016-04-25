package example.dy.com.homework;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import example.dy.com.homework.entity.JsonFood;
import example.dy.com.homework.entity.JsonUser;
import example.dy.com.homework.entity.ONutrient;
import example.dy.com.homework.entity.OReslut;
import example.dy.com.homework.myUtil.ConnectionUtils;
import example.dy.com.homework.myUtil.DatabaseHelper;
import example.dy.com.homework.myUtil.StringUtils;

/**
 * Created by dy on 2016/4/22.
 */
public class DietFragment1 extends Fragment {
    private View vDiet;
    private ListView dietListView;
    private Button button;
    private DatabaseHelper databaseHelper;
    private JsonUser u;
    private FragmentManager manager;
    private FragmentTransaction ft;
    private int selectedPosition = -1;
    private String[] list;
    private String[] listId;
    private CategoryAdapter adapter;
    private static final String IP = StringUtils.IPString;
    final static String URL = "http://" + IP + "/SportServer/webresources/com.dy.entity.food/findByServing";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vDiet = inflater.inflate(R.layout.fragment_diet1, container, false);

        dietListView = (ListView) vDiet.findViewById(R.id.category_listview);
        button = (Button) vDiet.findViewById(R.id.select_diet_button);

        databaseHelper = new DatabaseHelper(vDiet.getContext());
        manager = getFragmentManager();

        u = this.getArguments().getParcelable("user");
//        System.out.println("setp->u" + u);

        list = new String[]{"Pork Products", "Fruits and Fruit Juices", "Meals, Entrees, and Side Dishes", "Vegetables and Vegetable Products", "Soups, Sauces, and Gravies", "Breakfast Cereals"};
        listId = new String[]{"1000","0900","2200","1100","0600","0800"};
        adapter = new CategoryAdapter(vDiet.getContext(), list);
        dietListView.setAdapter(adapter);

        dietListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.select(position);
                selectedPosition = position;

            }
        });

        //after submitting return this view
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("click" + selectedPosition);
                String s = list[selectedPosition].replace(' ','&');

                //update all food cal
//                final String cateUrl = "http://" + IP + "/SportServer/webresources/com.dy.entity.food/findByServing";
//                new ConnectionUtils(cateUrl, new ConnectionUtils.ConnectionCallback() {
//                    @Override
//                    public void onSuccess(Object result) {
//                        System.out.println("all food reslut " + result);
//                        Gson gson = new Gson();
//                        //Json object array [{..},{}]
//
//                        List<JsonFood> foods = gson.fromJson(result.toString(), new TypeToken<List<JsonFood>>() {
//                        }.getType());
//
////                        final JsonFood ffoodItem =  foods.get(0);
//
//                        for (final JsonFood ffoodItem: foods) {
//
//                            System.out.println("cur food->"+ffoodItem);
//                            String url = "http://api.nal.usda.gov/ndb/reports/?ndbno=" + ffoodItem.getId() +
//                                    "&type=f&format=json&api_key=p5aLD8nQMekwaaiWIlAQZu3Lr9LSdL75YEMK0CIP";
//                            System.out.println("api url->" + url);
//
//
//                            new ConnectionUtils(url, new ConnectionUtils.ConnectionCallback() {
//                                @Override
//                                public void onSuccess(Object result) {
//                                    System.out.println("api reslut" + result);
//                                    Gson gson = new Gson();
//                                    //Json object array [{..},{}]
//
//                                    OReslut apiResult = gson.fromJson(result.toString(), new TypeToken<OReslut>() {
//                                    }.getType());
//
//                                    List<ONutrient> nutrients = apiResult.getReport().getFood().getNutrients();
//                                    int[] nutrientList = new int[]{268, 203, 204, 205, 269};
//                                    String[] nutrientName = new String[]{"Energy", "Protein", "Total lipid (fat)", "Carbohydrate", "Sugars total"};
//                                    String cal = "0.0";
//                                    for (ONutrient tmp : nutrients) {
////                            System.out.println(tmp);
//                                        for (int i = 0; i < nutrientList.length; i++) {
//                                            if (tmp.getNutrient_id() == nutrientList[i]) {
//                                                System.out.println("get->" + tmp.getNutrient_id() + "," + tmp.getName() + "," + tmp.getValue() + "," + tmp.getUnit());
//                                            }
//                                            if (tmp.getNutrient_id() == 268) {
//                                                String uuu =  "http://" + IP + "/SportServer/webresources/com.dy.entity.food/updateFood";
//                                                cal = String.valueOf(tmp.getValue());
//
//                                                new ConnectionUtils(uuu, new ConnectionUtils.ConnectionCallback() {
//                                                    @Override
//                                                    public void onSuccess(Object result) {
//                                                        System.out.println("reslut" + result);
//                                                        Gson gson = new Gson();
//                                                        //Json object array [{..},{}]
//
//                                                        JsonFood ttfood = gson.fromJson(result.toString(), new TypeToken<JsonFood>() {
//                                                        }.getType());
//                                                        System.out.println("food"+ttfood);
//
//                                                    }
//
//                                                    @Override
//                                                    public void onFail() {
//                                                        System.out.println("cannot find food in api");
//
//                                                    }
//                                                }, ffoodItem.getId(), cal);
//
//                                            }
//                                        }
//                                    }
//
//                                }
//
//                                @Override
//                                public void onFail() {
//                                    System.out.println("cannot find food in api");
//
//                                }
//                            }, new String[]{});
//
//                        }
//
//
//
//
//
//
//
//                    }
//
//                    @Override
//                    public void onFail() {
//                        System.out.println("cannot find food in server");
//
//                    }
//                }, s);


                DietFragment2 dietFragment2 = new DietFragment2();
                Bundle bundle = new Bundle();
                bundle.putParcelable("user", u);
                bundle.putString("category", s);
//                System.out.println("category in 1"+s);
                dietFragment2.setArguments(bundle);


                ft = manager.beginTransaction();
                //the old fragment exist.
//                ft.addToBackStack();
                ft.replace(R.id.content_frame, dietFragment2);
//                ft.addToBackStack(dietFragment2.getTag());
                ft.commit();

            }
        });


        return vDiet;
    }
}
