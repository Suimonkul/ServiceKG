package appkg.kg.servicekg.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import appkg.kg.servicekg.R;

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


    private TextView tvName, tvDescription, tvCategory;
    private Button btnCall, btnOrder, btnAvatar;

    private String name, description, phone, phone_two, phone_three, category;
    private int order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(STYLE);
        setContentView(LAYOUT);
        initDefApp();
        initLogic();
        initView();


    }

    private void initLogic() {
        Intent data = getIntent();

        name = data.getStringExtra("name");
        description = data.getStringExtra("desc");
        phone = data.getStringExtra("phone");
        phone_two = data.getStringExtra("phone_two");
        phone_three = data.getStringExtra("phone_three");
        order = data.getIntExtra("order", 0);
        category = data.getStringExtra("category");

        Log.d("Cat123", "" + category);

    }


    private void initView() {

        tvName = (TextView) findViewById(R.id.tvName);
        tvDescription = (TextView) findViewById(R.id.tvDetailDescription);
        tvCategory = (TextView) findViewById(R.id.tvCategory);
        btnAvatar = (Button) findViewById(R.id.btnAvatar);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnOrder = (Button) findViewById(R.id.btnOrder);

        tvName.setText(name);
        tvDescription.setText(description);
        tvCategory.setText(category);
        btnAvatar.setText(name.toUpperCase());
        btnOrder.setText(String.valueOf(order) + " сом");

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(v);
                openContextMenu(v);
                unregisterForContextMenu(v);
            }
        });


    }


    private void initDefApp() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDet);
        setSupportActionBar(toolbar);
        assert toolbar != null;
        toolbar.setTitleTextColor(getResources().getColor(R.color.dark));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case SECOND:
                calling.setData(Uri.parse("tel:" + PREFIX_PHONE + phone_two));
                startActivity(calling);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case THIRD:
                calling.setData(Uri.parse("tel:" + PREFIX_PHONE + phone_three));
                startActivity(calling);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
        return false;
    }

}