package appkg.kg.servicekg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import appkg.kg.servicekg.R;

public class FavoriteActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_favorite;
    private static final int STYLE = R.style.AppTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
    }
}
