package appkg.kg.servicekg.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import appkg.kg.servicekg.R;
import appkg.kg.servicekg.adapter.CategoryAdapter;
import appkg.kg.servicekg.model.Category;

/**
 * Created by Suimonkul on 26.06.2016.
 */
public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;
    private static final int STYLE = R.style.AppDefault;

    boolean doubleBackToExitPressedOnce = false;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTheme(STYLE);
        setContentView(LAYOUT);


        initNavigationDrawer();
        initActionBar();
        initCategory();


    }

    private void initActionBar() {

        ImageView open_menu = (ImageView) findViewById(R.id.open_menu);
        ImageView open_add = (ImageView) findViewById(R.id.open_add);

        assert open_add != null;
        open_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Add Button", Toast.LENGTH_SHORT).show();
            }
        });

        assert open_menu != null;
        open_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

    }

    private void initNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        assert navigationView != null;
        navigationView.inflateHeaderView(R.layout.navigation_header);

    }

    public void onClickHeader(View v) {
        drawerLayout.closeDrawers();
        switch (v.getId()) {
            case R.id.add_advert:
                Toast.makeText(this, "Nv Header", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void initCategory() {

        ArrayList<Category> categories = new ArrayList<>();

        Category category_uslugi = new Category(1, "Услуги", R.drawable.dad, "YSaja");
        categories.add(category_uslugi);
        Category category_business = new Category(2, "Бизнес", R.drawable.ic_menu, "YSaja");
        categories.add(category_business);
        Category category_service = new Category(3, "Сервис", R.color.green_dark, "YSaja");
        categories.add(category_service);
        categories.add(category_uslugi);
        categories.add(category_service);
        categories.add(category_uslugi);
        categories.add(category_service);

        RecyclerView rvCategory = (RecyclerView) findViewById(R.id.rvCategory);
        final CategoryAdapter adapter = new CategoryAdapter(categories);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        assert rvCategory != null;
        rvCategory.setLayoutManager(manager);
        rvCategory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            Toast.makeText(MainActivity.this, "Нажмите еще раз", Toast.LENGTH_SHORT).show();
            this.doubleBackToExitPressedOnce = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;

                }
            }, 1000);
        }

    }


}
