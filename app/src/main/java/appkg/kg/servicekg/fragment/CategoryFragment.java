package appkg.kg.servicekg.fragment;

import android.content.Context;
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


//        cat1 = (Button) rootView.findViewById(R.id.cat1);
//        cat2 = (Button) rootView.findViewById(R.id.cat2);
//        cat3 = (Button) rootView.findViewById(R.id.cat3);
//        cat4 = (Button) rootView.findViewById(R.id.cat4);
//        cat5 = (Button) rootView.findViewById(R.id.cat5);
//        cat6 = (Button) rootView.findViewById(R.id.cat6);
//        cat7 = (Button) rootView.findViewById(R.id.cat7);
//        cat8 = (Button) rootView.findViewById(R.id.cat8);
//        cat9 = (Button) rootView.findViewById(R.id.cat9);
//        cat10 = (Button) rootView.findViewById(R.id.cat10);

//        cat1.setOnClickListener(this);
//        cat2.setOnClickListener(this);
//        cat3.setOnClickListener(this);
//        cat4.setOnClickListener(this);
//        cat5.setOnClickListener(this);
//        cat6.setOnClickListener(this);
//        cat7.setOnClickListener(this);
//        cat8.setOnClickListener(this);
//        cat9.setOnClickListener(this);
//        cat10.setOnClickListener(this);


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

//        switch (v.getId()) {
//            case R.id.cat1:
//                UrlChangeDispatcher.getInstance().notifyListeners("http://192.168.0.100/api/v1/advert/?category__name=%D0%9A%D0%BE%D0%BC%D0%BF%D1%8C%D1%8E%D1%82%D0%B5%D1%80%D0%BD%D1%8B%D0%B5%20%D1%83%D1%81%D0%BB%D1%83%D0%B3%D0%B8&format=json");
//                break;
//            case R.id.cat2:
//                UrlChangeDispatcher.getInstance().notifyListeners("http://192.168.0.100/api/v1/advert/?category__name=STL&format=json");
//                break;
//            case R.id.cat3:
//                UrlChangeDispatcher.getInstance().notifyListeners("http://192.168.0.100/api/v1/advert/?category__name=Minecraft&format=json");
//                break;
//            case R.id.cat4:
//                UrlChangeDispatcher.getInstance().notifyListeners("http://192.168.0.100/api/v1/advert/?category__name=House&format=json");
//                break;
//            case R.id.cat5:
//                UrlChangeDispatcher.getInstance().notifyListeners("http://192.168.0.100/api/v1/advert/?category__name=Dishes&format=json");
//                break;
//            case R.id.cat6:
//                UrlChangeDispatcher.getInstance().notifyListeners("http://192.168.0.100/api/v1/advert/?category__name=Button&format=json");
//                break;
//            case R.id.cat7:
//                UrlChangeDispatcher.getInstance().notifyListeners("http://192.168.0.100/api/v1/advert/?category__name=Avto&format=json");
//                break;
//            case R.id.cat8:
//                UrlChangeDispatcher.getInstance().notifyListeners("http://192.168.0.100/api/v1/advert/?category__name=Repair&format=json");
//                break;
//            case R.id.cat9:
//                UrlChangeDispatcher.getInstance().notifyListeners("http://192.168.0.100/api/v1/advert/?category__name=Weapon&format=json");
//                break;
//            case R.id.cat10:
//                UrlChangeDispatcher.getInstance().notifyListeners("http://192.168.0.100/api/v1/advert/?category__name=Weapon&format=json");
//                break;
    }
}
