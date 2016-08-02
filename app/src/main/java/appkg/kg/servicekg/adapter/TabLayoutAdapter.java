package appkg.kg.servicekg.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import appkg.kg.servicekg.fragment.ADVListFragment;
import appkg.kg.servicekg.fragment.AbstractTabsFragment;
import appkg.kg.servicekg.fragment.CategoryFragment;

/**
 * Created by Suimonkul on 13.07.2016.
 */
public class TabLayoutAdapter extends FragmentPagerAdapter {

    Map<Integer, AbstractTabsFragment> tabs;
    private Context context;

    public TabLayoutAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initTabMap();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabMap() {
        String url = "http://192.168.0.101/api/v1/advert/?format=json";
//        String url_category = "http://192.168.0.102/api/v1/category/?format=json";
        tabs = new HashMap<>();
        tabs.put(1, ADVListFragment.getInstance(context, url));
        tabs.put(0, CategoryFragment.getInstance(context));
    }
}
