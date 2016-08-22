package appkg.kg.servicekg.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import appkg.kg.servicekg.R;

public class RegisterActivity extends AppCompatActivity {


    private static final int LAYOUT = R.layout.activity_register;
    private static final int STYLE = R.style.AppDefault;

    SharedPreferences sharedPreferences;

    EditText edfullname, edtitle, eddescription, edmail, ednumber;
    Button btnSent;
    String fullname, title, description, mail, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(STYLE);
        setContentView(LAYOUT);

        initUI();
        initToolbar();
        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLogic();
                saveDATA();
            }
        });
        loadDATA();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveDATA();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    private void initGET() {
        fullname = String.valueOf(edfullname.getText());
        title = String.valueOf(edtitle.getText());
        description = String.valueOf(eddescription.getText());
        mail = String.valueOf(edmail.getText());
        number = String.valueOf(ednumber.getText());
    }

    private void initLogic() {
        initGET();
        Intent push = new Intent(Intent.ACTION_VIEW);
        String post = "Ф.И.О : " +
                fullname + "\nЗаголовок : "
                + title + "\nОписание : " +
                description + "\nMail : "
                + mail + "\nНомер тел. : " + number;
        push.setData(Uri.parse("mailto:ermekturumbekov@gmail.com"));
        push.putExtra(Intent.EXTRA_SUBJECT, "Заявка на добавление рекламы в ServiceKG");
        push.putExtra(Intent.EXTRA_TEXT, post);
        startActivity(push);
    }

    private void initUI() {
        edfullname = (EditText) findViewById(R.id.edName);
        edtitle = (EditText) findViewById(R.id.edTitle);
        eddescription = (EditText) findViewById(R.id.edDescription);
        edmail = (EditText) findViewById(R.id.edMail);
        ednumber = (EditText) findViewById(R.id.edNumber);

        btnSent = (Button) findViewById(R.id.btnSent);

    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarReg);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Заявка");
    }

    private void saveDATA() {
        initGET();
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString("name", fullname);
        ed.putString("title", title);
        ed.putString("desc", description);
        ed.putString("mail", mail);
        ed.putString("number", number);
        ed.apply();
    }

    private void loadDATA() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        edfullname.setText(sharedPreferences.getString("name", ""));
        edtitle.setText(sharedPreferences.getString("title", ""));
        eddescription.setText(sharedPreferences.getString("desc", ""));
        edmail.setText(sharedPreferences.getString("mail", ""));
        ednumber.setText(sharedPreferences.getString("number", ""));
    }

    @Override
    protected void onDestroy() {
        saveDATA();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                saveDATA();
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
