package appkg.kg.servicekg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import appkg.kg.servicekg.R;

public class RegisterActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_register;
    private static final int STYLE = R.style.AppDefault;

    EditText fullname, title, description, mail, number;
    Button category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(STYLE);
        setContentView(LAYOUT);

        initUI();
    }

    private void initUI() {

    }
}
