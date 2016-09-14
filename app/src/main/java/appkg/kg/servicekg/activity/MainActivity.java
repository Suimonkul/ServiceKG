package appkg.kg.servicekg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import appkg.kg.servicekg.R;
import appkg.kg.servicekg.adapter.TabLayoutAdapter;

/**
 * Created by Suimonkul on 26.06.2016.
 */
public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;
    private static final int STYLE = R.style.AppDefault;

    boolean doubleBackToExitPressedOnce = false;

    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(STYLE);
        setContentView(LAYOUT);

        initTabs();
        initToolbar();


    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        TabLayout.Tab backPressed = tabLayout.getTabAt(0);
        assert backPressed != null;
        backPressed.select();
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 800);
    }


    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        TabLayoutAdapter adapter = new TabLayoutAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.dark));
        toolbar.inflateMenu(R.menu.about_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.about_opener:
                        Intent intent = new Intent(MainActivity.this, ADVListActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                }
                return false;
            }
        });
    }


}
