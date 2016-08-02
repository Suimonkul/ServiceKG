package appkg.kg.servicekg.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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



    private TextView tvTitle, tvDescription;

    private String title, description, phone, phone_two, phone_three;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(STYLE);
        setContentView(LAYOUT);

        initLogic();
        initView();
        initDefApp();

    }

    private void initLogic() {
        Intent data = getIntent();

        title = data.getStringExtra("title");
        description = data.getStringExtra("desc");
        phone = data.getStringExtra("phone");
        phone_two = data.getStringExtra("phone_two");
        phone_three = data.getStringExtra("phone_three");

    }


    private void initView() {

        tvTitle = (TextView) findViewById(R.id.tvDetailTitle);
        tvDescription = (TextView) findViewById(R.id.tvDetailDescription);

        tvTitle.setText(title);
        tvDescription.setText(description);

        tvDescription.setLinksClickable(true);

    }


    private void initDefApp() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerForContextMenu(view);
                openContextMenu(view);
                unregisterForContextMenu(view);


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Выберите номер телефона:");

        if (phone.equals("0")) {
            menu.removeItem(FIRST);
        } else {
            menu.add(Menu.NONE, FIRST, 0, PREFIX_PHONE + phone);
        }
        if (phone_two.equals("0")) {
            Toast.makeText(DetailActivity.this, "Phone 2 is null", Toast.LENGTH_SHORT).show();
            menu.removeItem(SECOND);
        } else {
            menu.add(0, SECOND, 0, PREFIX_PHONE + phone_two);
        }
        if (phone_three.equals("0")) {
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