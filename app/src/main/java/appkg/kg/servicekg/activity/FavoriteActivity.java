package appkg.kg.servicekg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import appkg.kg.servicekg.R;

public class FavoriteActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_favorite;
    private static final int STYLE = R.style.AppTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(LAYOUT);
        setTheme(STYLE);

        initUI();
    }

    private void initUI() {



    }
}
