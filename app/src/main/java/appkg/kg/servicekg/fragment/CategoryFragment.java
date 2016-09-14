package appkg.kg.servicekg.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import appkg.kg.servicekg.R;
import appkg.kg.servicekg.adapter.ExpandableAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Suimonkul on 28-Jul-16.
 */
public class CategoryFragment extends AbstractTabsFragment {
    private static final int LAYOUT = R.layout.fragment_category;

    ExpandableListView lv;
    private String[] groups;
    private String[][] children;


    Context context;

    public static CategoryFragment getInstance(Context context) {
        Bundle args = new Bundle();
//        args.putString("url", url);
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.res_tab2));

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        groups = new String[]
                {
                        "Услуги", "Сдаю", "Красота и здоровье", "Ищу работу", "Торжества", "Обучение",
                        "Бюро находок", "Сниму", "Требуется"
                }

        ;

        children = new String[][]

                {
                        {
                                "Транспортные услуги",
                                "Установка антенн",
                                "Ремонт техники",
                                "Ремонт. Отделка",
                                "Строительство",
                                "Сварка. Сантехника",
                                "Электроработы",
                                "Плотницкие",
                                "Работа с металлом",
                                "Памятники",
                                "Юридические",
                                "Риэлтерские",
                                "Языковые переводы",
                                "Швейные",
                                "Медицинские",
                                "Работа по дому",
                                "Отдых. Турпоездки",
                                "Прокат"
                        },
                        {
                                "1-к. квартиры",
                                "2-к. квартиры",
                                "3-к. квартиры",
                                "4-,5-к. квартиры",
                                "Гостинницы"
                        },
                        {
                                "Массаж",
                                "Спортивные клубы",
                                "Салоны красоты",
                                "Медицинские центры"
                        },
                        {
                                "Комп"
                        },
                        {
                                "Тамада"
                        },
                        {
                                "Английский язык"
                        },
                        {
                                "Частные"
                        },
                        {
                                "Дом"
                        },
                        {
                                "Работники"
                        }
                };

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(LAYOUT, container, false);
//        new DDTCategory().execute();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv = (ExpandableListView) view.findViewById(R.id.expListView);
        lv.setAdapter(new ExpandableAdapter(groups, children, context));
        lv.setGroupIndicator(null);


    }

    public void setContext(Context context) {
        this.context = context;
    }


    private class DDTCategory extends AsyncTask<Void, Void, Void> {
        RecyclerView recyclerView;
        String url;
        OkHttpClient client = new OkHttpClient();
        JSONObject dataJsonObj = null;
        String category;
        int id;
        String subCategory;
        JSONArray childCategoriesArray;
        HashMap<String, ArrayList<String>> categories = new HashMap<>();
        ArrayList<String> subcategories = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... params) {

            Request request = new Request.Builder()
                    .url("http://192.168.0.101/api/v1/category/?format=json")
                    .build();

            Response response = null;


            try {
                response = client.newCall(request).execute();
                assert response != null;
                dataJsonObj = new JSONObject(response.body().string());
                JSONArray jsonArray = dataJsonObj.getJSONArray("objects");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    category = jsonObject.getString("name");
                    childCategoriesArray = jsonObject.getJSONArray("childCategories");


                    for (int a = 0; i < childCategoriesArray.length(); a++) {
                        JSONObject jsonObjectChild = childCategoriesArray.getJSONObject(a);
                        subCategory = jsonObjectChild.getString("name");
                        subcategories.add(subCategory);

                        Log.d("SUBCAT", subcategories + "");
                        categories.put(category, subcategories);
                        Log.d("ALLCAT", categories + "");
                    }

                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
