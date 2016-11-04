//package appkg.kg.servicekg.fragment;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import java.io.IOException;
//
//import appkg.kg.servicekg.R;
//import appkg.kg.servicekg.activity.SelectCategoryActivity;
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//public class RegisterFragment extends AbstractTabsFragment {
//
//
//    private static final int LAYOUT = R.layout.fragment_register;
//
//
//    EditText edfullname, edtitle, eddescription, edphone, edphoneTwo, edphoneThree, edorder;
//    Button btnSent, btnCategory;
//    String full_name, title, description, mail, phone, phone_two, phone_three, order;
//    int category, result_id;
//
//    Context context;
//
//    public static RegisterFragment getInstance(Context context) {
//        Bundle args = new Bundle();
//        RegisterFragment fragment = new RegisterFragment();
//        fragment.setArguments(args);
//        fragment.setContext(context);
//        fragment.setTitle(context.getString(R.string.res_tab1));
//
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(LAYOUT, container, false);
//        edfullname = (EditText) rootView.findViewById(R.id.edName);
//        edtitle = (EditText) rootView.findViewById(R.id.edTitle);
//        eddescription = (EditText) rootView.findViewById(R.id.edDescription);
//        edphone = (EditText) rootView.findViewById(R.id.edNumber);
//        edphoneTwo = (EditText) rootView.findViewById(R.id.edNumberTwo);
//        edphoneThree = (EditText) rootView.findViewById(R.id.edNumberThree);
//        edorder = (EditText) rootView.findViewById(R.id.edOrder);
//
//        btnCategory = (Button) rootView.findViewById(R.id.btnSelectCategory);
//        btnCategory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent selectData = new Intent(context, SelectCategoryActivity.class);
//                selectData.putExtra("select", true);
//                startActivityForResult(selectData, 1);
//            }
//        });
//
//
//        btnSent = (Button) rootView.findViewById(R.id.btnSent);
//        btnSent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (initGET()) {
//                    new PostTask().execute();
//                }
//            }
//        });
//
//        return rootView;
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data == null) {
//            return;
//        }
//        result_id = data.getIntExtra("id", 0);
//        Log.d("RESULT_CAT", result_id + "");
//
//
//    }
//
//    private boolean initGET() {
//
//        try {
//            full_name = String.valueOf(edfullname.getText());
//            title = String.valueOf(edtitle.getText());
//            description = String.valueOf(eddescription.getText());
//            phone = String.valueOf(edphone.getText());
//            if (edphoneTwo.getText().toString().isEmpty()) {
//                phone_two = "null";
//            } else {
//                phone_two = String.valueOf(edphoneTwo.getText());
//            }
//            if (edphoneThree.getText().toString().isEmpty()) {
//                phone_three = "null";
//            } else {
//                phone_three = String.valueOf(edphoneThree.getText());
//            }
//            order = String.valueOf(edorder.getText());
//            category = result_id;
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            Toast.makeText(context, "Заполните все поля пожалуйста", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        return true;
//    }
//
//    public void setContext(Context context) {
//        this.context = context;
//    }
//
//    private class PostTask extends AsyncTask<String, String, String> {
//
//        @Override
//        protected void onPreExecute() {
//
//            Toast.makeText(context, "Запрос принят, обработка данных ...", Toast.LENGTH_SHORT).show();
//
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            OkHttpClient client = new OkHttpClient();
//
//            RequestBody formBody = new FormBody.Builder()
//                    .add("name", full_name)
//                    .add("phone", phone)
//                    .add("active", "false")
//                    .add("title", title)
//                    .add("description", description)
//                    .add("order", order)
//                    .add("phone_two", phone_two)
//                    .add("phone_three", phone_three)
//                    .add("category_id", "1")
//                    .add("position", "100")
//                    .build();
//            Request request = new Request.Builder()
//                    .url("https://guarded-retreat-76092.herokuapp.com/register")
//                    .post(formBody)
//                    .build();
//
//            try {
//                Response response = client.newCall(request).execute();
//
//                Log.d("POST", "" + response);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            Toast.makeText(context, "Ваша реклама добавлена", Toast.LENGTH_SHORT).show();
//        }
//    }
//}