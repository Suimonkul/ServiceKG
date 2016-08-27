package appkg.kg.servicekg.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import appkg.kg.servicekg.R;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterFragment extends AbstractTabsFragment {


    private static final int LAYOUT = R.layout.fragment_register;

    SharedPreferences sharedPreferences;

    EditText edfullname, edtitle, eddescription, edmail, ednumber;
    Button btnSent;
    String fullname, title, description, mail, number;

    Context context;

    public static RegisterFragment getInstance(Context context) {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.res_tab1));

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(LAYOUT, container, false);
        edfullname = (EditText) rootView.findViewById(R.id.edName);
        edtitle = (EditText) rootView.findViewById(R.id.edTitle);
        eddescription = (EditText) rootView.findViewById(R.id.edDescription);
        edmail = (EditText) rootView.findViewById(R.id.edMail);
        ednumber = (EditText) rootView.findViewById(R.id.edNumber);

        btnSent = (Button) rootView.findViewById(R.id.btnSent);
        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGET();
                new PostTask().execute();
//                initLogic();
//                saveDATA();
            }
        });
//        loadDATA();
        return rootView;
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


//    private void saveDATA() {
//        initGET();
//        sharedPreferences = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor ed = sharedPreferences.edit();
//        ed.putString("name", fullname);
//        ed.putString("title", title);
//        ed.putString("desc", description);
//        ed.putString("mail", mail);
//        ed.putString("number", number);
//        ed.apply();
//    }
//
//    private void loadDATA() {
//        sharedPreferences = getPreferences(MODE_PRIVATE);
//        edfullname.setText(sharedPreferences.getString("name", ""));
//        edtitle.setText(sharedPreferences.getString("title", ""));
//        eddescription.setText(sharedPreferences.getString("desc", ""));
//        edmail.setText(sharedPreferences.getString("mail", ""));
//        ednumber.setText(sharedPreferences.getString("number", ""));
//    }

    @Override
    public void onDestroy() {
//        saveDATA();
        super.onDestroy();
    }


    public void setContext(Context context) {
        this.context = context;
    }

    private class PostTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("name", fullname)
                    .add("phone", number)
                    .add("title", title)
                    .add("description", description)
                    .add("email", mail)
                    .add("order", "0")
                    .add("phone_two", "1341")
                    .add("phone_three", "241")
                    .add("position", "100")
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.0.106/register/")
                    .post(formBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                Log.d("POST", "" + response);
                // Do something with the response.
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("POST", "progress");

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(context, "Posted", Toast.LENGTH_SHORT).show();
        }
    }
}