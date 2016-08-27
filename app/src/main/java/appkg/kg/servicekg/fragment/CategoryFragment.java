package appkg.kg.servicekg.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import appkg.kg.servicekg.R;
import appkg.kg.servicekg.adapter.ExpandableAdapter;

/**
 * Created by Suimonkul on 28-Jul-16.
 */
public class CategoryFragment extends AbstractTabsFragment implements View.OnClickListener {
    private static final int LAYOUT = R.layout.fragment_category;

    ExpandableListView lv;
    private String[] groups;
    private String[][] children;

    Button cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10;

    Context context;

    String urlNew = "";

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
        groups = new String[]{"Услуги", "Сдаю", "Красота и здоровье", "Ищу работу", "Торжества", "Обучение",
                "Бюро находок", "Сниму", "Требуется"};

        children = new String[][]{
                {"Транспортные услуги",
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
                {"1-к. квартиры",
                        "2-к. квартиры",
                        "3-к. квартиры",
                        "4-,5-к. квартиры",
                        "Гостинницы"
                }
        };
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(LAYOUT, container, false);


//

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

    @Override
    public void onClick(View v) {

    }

    private class DDTCategory extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }
}
