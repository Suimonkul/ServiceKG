package appkg.kg.servicekg.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import appkg.kg.servicekg.R;
import appkg.kg.servicekg.database.DataHelper;
import appkg.kg.servicekg.model.Info;

/**
 * Created by Suimonkul on 26.06.2016.
 */

public class DetailActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_detail;
    private static final int STYLE = R.style.AppDefault;
    private static final String PREFIX_PHONE = "0";
    private boolean isExist;
    private DataHelper dataHelper;


    private TextView tvName, tvDescription, tvOrder, tvNumber;
    private Button btnCall;
    private ImageView detail_close;
    private ImageButton share, add_favorite;

    private String name, description, phone;
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
        info = (Info) getIntent().getSerializableExtra("info");
        dataHelper = new DataHelper(this);
        initLogic();
        initView();


        isExist = dataHelper.isExist(info.getId());
        if (isExist) {
            add_favorite.setImageResource(R.drawable.ic_star_active);
        } else {
            add_favorite.setImageResource(R.drawable.ic_star_unactive);
        }

    }

    private void initLogic() {


        name = info.getTitle();
        description = info.getDescription();
        phone = info.getPhone();
        order = info.getOrder();

    }


    private void initView() {

        detail_close = (ImageView) findViewById(R.id.detail_close);

        tvName = (TextView) findViewById(R.id.tvName);
        tvDescription = (TextView) findViewById(R.id.tvDetailDescription);
        btnCall = (Button) findViewById(R.id.btnCall);
        tvOrder = (TextView) findViewById(R.id.tvOrder);
        tvNumber = (TextView) findViewById(R.id.tvNumber);
        share = (ImageButton) findViewById(R.id.share_advert);
        add_favorite = (ImageButton) findViewById(R.id.add_favorite);

        tvName.setText(name);
        tvDescription.setText(description);
        tvOrder.setText(String.valueOf(order) + " сом");
        tvNumber.setText(phone);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //request permission from user if the app hasn't got the required permission
                    ActivityCompat.requestPermissions(DetailActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                            10);
                    return;
                } else {     //have got permission
                    try {
                        startActivity(intent);  //call activity and make phone call
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
                    }
                }
                startActivity(intent);

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "ServiceKG");
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, name + "\n Номер: " + phone + "\n\n\n\n" + "Где я нашел эту информацию!?\nСкачай приложение ServiceKG");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Поделиться"));
            }
        });


        add_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_favorite.setEnabled(false);
                if (isExist) {
                    dataHelper.removeAdvert(info.getId());
                    add_favorite.setImageResource(R.drawable.ic_star_unactive);
                    isExist = false;
                } else {
                    dataHelper.saveAdvert(info);
                    add_favorite.setImageResource(R.drawable.ic_star_active);
                    isExist = true;
                }
                add_favorite.setEnabled(true);
            }
        });

        detail_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_down_up_close_enter, R.anim.activity_down_up_close_exit);
    }


}