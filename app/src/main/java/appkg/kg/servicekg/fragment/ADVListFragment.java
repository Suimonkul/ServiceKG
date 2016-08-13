package appkg.kg.servicekg.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import appkg.kg.servicekg.R;
import appkg.kg.servicekg.adapter.ListAbstractAdapter;
import appkg.kg.servicekg.dispatcher.UrlChangeDispatcher;
import appkg.kg.servicekg.dispatcher.UrlChangeListener;
import appkg.kg.servicekg.helpers.RecyclerScrollListener;
import appkg.kg.servicekg.helpers.Utils;
import appkg.kg.servicekg.model.Info;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Suimonkul on 26-Jul-16.
 */
public class ADVListFragment extends AbstractTabsFragment implements UrlChangeListener {
    private static final int LAYOUT = R.layout.fragment_list;
    private DDT ddt;
    private RecyclerScrollListener listener;
    String id;
    String name;
    String description;
    String phone;
    String phone_two;
    String phone_three;
    String order;
    RecyclerView recyclerView;
    ListAbstractAdapter adapter;
    ArrayList<Info> list = new ArrayList<>();
    View nonConnection;
    String defUrl;
    int current_page;
    Button btnRefresh;


    public static ADVListFragment getInstance(Context context, String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        ADVListFragment fragment = new ADVListFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.res_tab1));
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListAbstractAdapter(list);
        nonConnection = view.findViewById(R.id.noInternet);
        defUrl = getArguments().getString("url");
        recyclerView.setAdapter(adapter);
        btnRefresh = (Button) view.findViewById(R.id.btnRefresh);

        listener = new RecyclerScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                DDT_load(current_page);
            }
        };
        recyclerView.addOnScrollListener(listener);
        checkConnect();
        DDT_load(0);
        return view;
    }

    @Override
    public void onUrlChanged(String newUrl) {
        list.clear();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        defUrl = newUrl;
        new DDT(recyclerView, defUrl, current_page).execute();
    }


    private void checkConnect() {
        if (!Utils.isConnected(getContext())) {
            Toast.makeText(getContext(), "Нет интернет соединения!", Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
            nonConnection.setVisibility(View.VISIBLE);
        }
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isConnected(context)) {
                    list.clear();
                    recyclerView.setAdapter(adapter);
                    DDT_load(current_page);
                    new DDT(recyclerView, defUrl, current_page).onPreExecute();
                    nonConnection.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                }

            }
        });
    }


    public ADVListFragment() {
        UrlChangeDispatcher.getInstance().addListener(this);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void DDT_load(int current_page) {
        Log.d("CurrentPage", current_page + "ADV");
        if (Utils.isConnected(context)) {
            if (ddt != null) ddt.cancel(true);
            ddt = new DDT(recyclerView, defUrl, current_page);
            ddt.execute();
        } else {
            recyclerView.setVisibility(View.GONE);
            nonConnection.setVisibility(View.VISIBLE);

        }

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
            Toast.makeText(context, "Download", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            Log.d("CurrentPage", url + limits + page);
            Request request = new Request.Builder()
                    .url(url + limits + page)
                    .build();

            Response response = null;

            try {
                response = client.newCall(request).execute();
                assert response != null;
                dataJsonObj = new JSONObject(response.body().string());
                JSONArray jsonArray = dataJsonObj.getJSONArray("objects");


                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    id = jsonObject.getString("id");
                    name = jsonObject.getString("name");
                    description = jsonObject.getString("description");
                    phone = jsonObject.getString("phone");
                    phone_two = jsonObject.getString("phone_two");
                    phone_three = jsonObject.getString("phone_three");
                    order = jsonObject.getString("order");

                    Info info = new Info(id, name, description, phone, phone_two, phone_three, order);

                    String[] phones = {"01 = " + phone + ", 02 = " + phone_two + ", 03 = " + phone_three};
                    Log.d("GET REQUEST", "GET REQUEST : " + "ID = " + id + ", NAME = " + name + ", DESCRIPTION = "
                            + description.length() + ", PHONES = " + phones + ", ORDER = " + order);
                    list.add(info);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            if (list.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                nonConnection.setVisibility(View.VISIBLE);
            } else if (!isCancelled() && adapter != null) {
                recyclerView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }

        }


    }

}
