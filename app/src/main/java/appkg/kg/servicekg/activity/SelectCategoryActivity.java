//package appkg.kg.servicekg.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.ExpandableListView;
//
//import appkg.kg.servicekg.R;
//import appkg.kg.servicekg.adapter.SelectCategoryAdapter;
//
//public class SelectCategoryActivity extends AppCompatActivity {
//
//
//    ExpandableListView lv;
//
//    String[] groups;
//    String[][] children;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_select);
//
//        groups = new String[]
//                {
//                        "Услуги", "Сдаю", "Красота и здоровье", "Ищу работу", "Торжества", "Обучение",
//                        "Бюро находок", "Сниму", "Требуется"
//                }
//
//        ;
//
//        children = new String[][]
//
//                {
//                        {
//                                "Транспортные услуги",
//                                "Установка антенн",
//                                "Ремонт техники",
//                                "Ремонт. Отделка",
//                                "Строительство",
//                                "Сварка. Сантехника",
//                                "Электроработы",
//                                "Плотницкие",
//                                "Работа с металлом",
//                                "Памятники",
//                                "Юридические",
//                                "Риэлтерские",
//                                "Языковые переводы",
//                                "Швейные",
//                                "Медицинские",
//                                "Работа по дому",
//                                "Отдых. Турпоездки",
//                                "Прокат"
//                        },
//                        {
//                                "1-к. квартиры",
//                                "2-к. квартиры",
//                                "3-к. квартиры",
//                                "4-,5-к. квартиры",
//                                "Гостинницы"
//                        },
//                        {
//                                "Массаж",
//                                "Спортивные клубы",
//                                "Салоны красоты",
//                                "Медицинские центры"
//                        },
//                        {
//                                "Комп"
//                        },
//                        {
//                                "Тамада"
//                        },
//                        {
//                                "Английский язык"
//                        },
//                        {
//                                "Частные"
//                        },
//                        {
//                                "Дом"
//                        },
//                        {
//                                "Работники"
//                        }
//                };
//
//
//        lv = (ExpandableListView) findViewById(R.id.expListView_cat);
//        lv.setAdapter(new SelectCategoryAdapter(groups, children, getApplicationContext()));
//        lv.setGroupIndicator(null);
//
//
//        lv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//
//                if (groupPosition == 1) {
//                    Log.d("CAT_ID", "" + id);
//                    return true;
//                }
//
//                return false;
//            }
//        });
//
//        lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Intent resChild = new Intent(SelectCategoryActivity.this, MainActivity.class);
//
//                resChild.putExtra("resChild", id);
//
//                Log.d("CAT_CHILD", "" + id);
//
//                setResult(RESULT_OK, resChild);
//                finish();
//                return false;
//            }
//        });
//    }
//
//}
