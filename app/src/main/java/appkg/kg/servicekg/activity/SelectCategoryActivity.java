package appkg.kg.servicekg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import appkg.kg.servicekg.R;

public class SelectCategoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initView();


    }

    private void initSelect() {

        LinearLayout ln_first = (LinearLayout) findViewById(R.id.ln_first);
        assert ln_first != null;
        ln_first.setVisibility(View.VISIBLE);


    }

    private void initView() {

    }

}
