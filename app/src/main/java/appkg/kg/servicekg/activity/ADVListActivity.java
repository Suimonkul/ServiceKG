package appkg.kg.servicekg.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import appkg.kg.servicekg.R;
import appkg.kg.servicekg.adapter.ListAbstractAdapter;
import appkg.kg.servicekg.helpers.RecyclerScrollListener;
import appkg.kg.servicekg.helpers.Utils;
import appkg.kg.servicekg.model.Info;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Suimonkul on 26-Jul-16.
 */
public class ADVListActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_advlist;
    private static final int STYLE = R.style.AppDefault;
    int id;
    String title;
    String description;
    String phone;
    String phone_two;
    String phone_three;
    int order;
    String name;
    boolean active;
    int position;
    RecyclerView recyclerView;
    ListAbstractAdapter adapter;
    ArrayList<Info> list = new ArrayList<>();
    View nonConnection;
    View progress;
    String defUrl;
    int current_page;
    Button btnRefresh;
    int total_count;
    ProgressBar progressBar;
    private DDT ddt;
    private RecyclerScrollListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(LAYOUT);
        setTheme(STYLE);

//        initToolbar();
        initViews();

        String lorem = getResources().getString(R.string.lorem_ipsum);

        Info map = new Info(1, "Title", lorem, "708517908", "708198686", "null", 2000, "Name");
        list.add(map);

        Intent getUrl = getIntent();
        String newUrl = getUrl.getStringExtra("url_change");


//        UrlChanged(newUrl);
        checkConnect();
        DDT_load(0);
    }

    private void initViews() {
        LinearLayout ln = (LinearLayout) findViewById(R.id.lnAdv);
        assert ln != null;
        ln.setBackgroundColor(getResources().getColor(R.color.mainBackground));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListAbstractAdapter(list);
        nonConnection = findViewById(R.id.noInternet);
        progress = findViewById(R.id.progress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView.setAdapter(adapter);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        listener = new RecyclerScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                DDT_load(current_page);
            }
        };
        recyclerView.addOnScrollListener(listener);
    }

//    private void initToolbar() {
//        ImageView toolbar =
//        setSupportActionBar(toolbar);
//        assert toolbar != null;
//        toolbar.setTitleTextColor(getResources().getColor(R.color.dark));
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Реклама");
//    }


    public void UrlChanged(String newUrl) {
//        Log.d("URLCHANGE", newUrl);
        list.clear();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        defUrl = newUrl;
        new DDT(recyclerView, defUrl, current_page).execute();
    }


    private void checkConnect() {
        if (!Utils.isConnected(this)) {
            Toast.makeText(this, "Нет интернет соединения!", Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
            nonConnection.setVisibility(View.VISIBLE);
        }
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isConnected(ADVListActivity.this)) {
                    list.clear();
                    recyclerView.setAdapter(adapter);
                    DDT_load(current_page);
                    new DDT(recyclerView, defUrl, current_page);
                    nonConnection.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);

                }

            }
        });
    }


    private void DDT_load(int current_page) {
        Log.d("CurrentPage", current_page + "ADV");
        if (Utils.isConnected(this)) {
            if (ddt != null) ddt.cancel(true);
            ddt = new DDT(recyclerView, defUrl, current_page);
            ddt.execute();
        } else {
            recyclerView.setVisibility(View.GONE);
            nonConnection.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onBackPressed() {
        Log.d("TAG", "Task Stop Back");
        finish();
        overridePendingTransition(R.anim.activity_down_up_close_enter, R.anim.activity_down_up_close_exit);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                new DDT(recyclerView, "", 0).cancel(true);
                finish();
                overridePendingTransition(R.anim.activity_down_up_close_enter, R.anim.activity_down_up_close_exit);
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    private class DDT extends AsyncTask<Void, Void, Void> {

        RecyclerView recyclerView;
        String url;
        OkHttpClient client = new OkHttpClient();
        JSONObject dataJsonObj = null;
        int page;
        String limits = "&limit=5&offset=";

        public DDT(RecyclerView recyclerView, String url, int current_page) {
            this.recyclerView = recyclerView;
            this.url = url;
            page = current_page;

        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(ADVListActivity.this, "Download", Toast.LENGTH_SHORT).show();
            progress.setVisibility(View.VISIBLE);
            super.onPreExecute();
//            String backUrl = "\"https://guarded-retreat-76092.herokuapp.com/api/v1/advert/?format=json\"";
        }

        @Override
        protected Void doInBackground(Void... params) {

//            Log.d("CurrentPage", url);
            Request request = new Request.Builder()
                    .url("http://7clicks.net/api/posts?page=" + page)
                    .build();

            Response response = null;


            try {
                response = client.newCall(request).execute();
                dataJsonObj = new JSONObject(response.body().string());
                JSONArray jsonArray = dataJsonObj.getJSONArray("posts");
                JSONObject meta = dataJsonObj.getJSONObject("meta");
//                total_count = meta.getInt("total_count");
                Log.d("adada", "start");
                Log.d("APILOG", dataJsonObj + "ada" + meta + response);


                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

//                    active = jsonObject.getBoolean("active");

//                    if (active) {
                    id = jsonObject.getInt("id");
                    title = jsonObject.getString("title");
                    description = jsonObject.getString("body");
                    phone = jsonObject.getString("phone_number");
//                    phone_two = jsonObject.getString("phone_two");
//                    phone_three = jsonObject.getString("phone_three");
//                    order = jsonObject.getInt("order");
//                    JSONArray img = jsonObject.getJSONArray("images");
//
//
//                    for (int id = 0; id < img.length(); id++) {
//                        JSONObject url = img.getJSONObject(id);
//
//
//                        name = url.getString("url");
//                        Log.d("IMG", name);
//
//
//                    }


                    Info info = new Info(id, title, description, phone, phone_two, phone_three, order, name);
                    list.add(info);
//                    }


                }


            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
//            long seed = System.nanoTime();
//            Collections.shuffle(list, new Random(seed));
            if (list.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);
                nonConnection.setVisibility(View.VISIBLE);
            } else if (!isCancelled() && adapter != null) {
                progress.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }
            Log.d("TAG", "Task Stop");
        }

    }
}
