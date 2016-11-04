package appkg.kg.servicekg.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import appkg.kg.servicekg.R;
import appkg.kg.servicekg.model.Info;

/**
 * Created by Suimonkul on 26.06.2016.
 */

public class DetailActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_detail;
    private static final int STYLE = R.style.AppDefault;
    private static final String PREFIX_PHONE = "0";
    final int FIRST = 0;
    final int SECOND = 1;
    final int THIRD = 2;
    final int FOUR = 3;
    Intent calling = new Intent(Intent.ACTION_VIEW);


    private TextView tvName, tvDescription, tvOrder;
    private Button btnCall;

    private String name, description, phone, phone_two, phone_three, category;
    private int order;

    private Info info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTheme(STYLE);
        setContentView(LAYOUT);
        initLogic();
        initView();


    }

    private void initLogic() {
        info = (Info) getIntent().getSerializableExtra("info");

        name = info.getTitle();
        description = info.getDescription();
        phone = info.getPhone();
        phone_two = info.getPhone_two();
        phone_three = info.getPhone_three();
        order = info.getOrder();

        Log.d("Cat123", "" + category);

    }


    private void initView() {

        tvName = (TextView) findViewById(R.id.tvName);
        tvDescription = (TextView) findViewById(R.id.tvDetailDescription);
        btnCall = (Button) findViewById(R.id.btnCall);
        tvOrder = (TextView) findViewById(R.id.tvOrder);

        tvName.setText(name);
        tvDescription.setText(description);
        tvOrder.setText(String.valueOf(order) + " сом");

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(v);
                openContextMenu(v);
                unregisterForContextMenu(v);
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_down_up_close_enter, R.anim.activity_down_up_close_exit);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.activity_down_up_close_enter, R.anim.activity_down_up_close_exit);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Выберите номер телефона:");

        if (phone.equals("null")) {
            menu.removeItem(FIRST);
        } else {
            menu.add(Menu.NONE, FIRST, 0, PREFIX_PHONE + phone);
        }
        if (phone_two.equals("null")) {
            Toast.makeText(DetailActivity.this, "Phone 2 is null", Toast.LENGTH_SHORT).show();
            menu.removeItem(SECOND);
        } else {
            menu.add(0, SECOND, 0, PREFIX_PHONE + phone_two);
        }
        if (phone_three.equals("null")) {
            Toast.makeText(DetailActivity.this, "Phone 3 is null", Toast.LENGTH_SHORT).show();
            menu.removeItem(THIRD);
        } else {
            menu.add(0, THIRD, 0, PREFIX_PHONE + phone_three);
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case FIRST:
                calling.setData(Uri.parse("tel:" + PREFIX_PHONE + phone));
                startActivity(calling);
                overridePendingTransition(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit);
                break;
            case SECOND:
                calling.setData(Uri.parse("tel:" + PREFIX_PHONE + phone_two));
                startActivity(calling);
                overridePendingTransition(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit);
                break;
            case THIRD:
                calling.setData(Uri.parse("tel:" + PREFIX_PHONE + phone_three));
                startActivity(calling);
                overridePendingTransition(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit);
                break;
        }
        return false;
    }

}