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
import android.widget.Spinner;
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

    EditText edfullname, edtitle, eddescription, edmail, edphone, edphoneTwo, edphoneThree, edorder;
    Button btnSent;
    Spinner spinner_category;
    String full_name, title, description, mail, phone, phone_two, phone_three, order, category;

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
        edphone = (EditText) rootView.findViewById(R.id.edNumber);
        edphoneTwo = (EditText) rootView.findViewById(R.id.edNumberTwo);
        edphoneThree = (EditText) rootView.findViewById(R.id.edNumberThree);
        edorder = (EditText) rootView.findViewById(R.id.edOrder);

        btnSent = (Button) rootView.findViewById(R.id.btnSent);
        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (initGET()) {
                    new PostTask().execute();
                }

//                initLogic();
            }
        });
        return rootView;
    }

    private boolean initGET() {
        try {
            full_name = String.valueOf(edfullname.getText());
            title = String.valueOf(edtitle.getText());
            description = String.valueOf(eddescription.getText());
            mail = String.valueOf(edmail.getText());
            phone = String.valueOf(edphone.getText());
            if (edphoneTwo.getText().toString().isEmpty()) {
                phone_two = "null";
            } else {
                phone_two = String.valueOf(edphoneTwo.getText());
            }
            if (edphoneThree.getText().toString().isEmpty()) {
                phone_three = "null";
            } else {
                phone_three = String.valueOf(edphoneThree.getText());
            }
            order = String.valueOf(edorder.getText());


//            category = String.valueOf(spinner_category.getId());
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(context, "Заполните все поля пожалуйста", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void initLogic() {
        initGET();
        Intent push = new Intent(Intent.ACTION_VIEW);
        String post = "Ф.И.О : " +
                full_name + "\nЗаголовок : "
                + title + "\nОписание : " +
                description + "\nMail : "
                + mail + "\nНомер тел. : " + phone;
        push.setData(Uri.parse("mailto:ermekturumbekov@gmail.com"));
        push.putExtra(Intent.EXTRA_SUBJECT, "Заявка на добавление рекламы в ServiceKG");
        push.putExtra(Intent.EXTRA_TEXT, post);
        startActivity(push);
    }


    public void setContext(Context context) {
        this.context = context;
    }

    private class PostTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

            Toast.makeText(context, "Запрос принят, обработка данных ...", Toast.LENGTH_SHORT).show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("name", full_name)
                    .add("phone", phone)
                    .add("title", title)
                    .add("description", description)
                    .add("email", mail)
                    .add("order", order)
                    .add("phone_two", phone_two)
                    .add("phone_three", phone_three)
                    .add("category_id", "20")
                    .add("position", "100")
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.0.100/register/")
                    .post(formBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
//                if (response.isSuccessful()) {
//                    Toast.makeText(context, "Добвляем рекламу ...", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
//                }
                Log.d("POST", "" + response);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(context, "Ваша реклама добавлена", Toast.LENGTH_SHORT).show();
        }
    }
}