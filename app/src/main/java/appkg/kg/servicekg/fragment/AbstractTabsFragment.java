package appkg.kg.servicekg.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Suimonkul on 13.07.2016.
 */
public class AbstractTabsFragment extends Fragment {
    private String title;
    protected Context context;
    protected View view;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

