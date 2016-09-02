package appkg.kg.servicekg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import appkg.kg.servicekg.R;

public class AboutActivity extends AppCompatActivity {

    String share_text;
    Intent share_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initView();
        initLogic();


    }

    private void initLogic() {
        share_intent.setType("text/plain");
        share_intent.putExtra(Intent.EXTRA_TEXT, share_text);
        startActivity(Intent.createChooser(share_intent, "Select for Share"));
    }

    private void initView() {

    }
}
