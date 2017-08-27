package com.example.torey.projectlogin.view.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.view.fragment.HeroListFragment;

public class HeroListActivity extends AppCompatActivity {
    HeroListFragment heroListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_list);

        heroListFragment = HeroListFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_hero_list, heroListFragment)
                .commit();
//        new LonginAsyncTask().execute("http://coemygroup.fr/test-mobile/iOS/json/test2.json");
    }
//    private class  LonginAsyncTask extends AsyncTask<String,Void,String> {
//        String response = "";
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(getBaseContext(), "Loading Data......", Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//                URL url = new URL(params[0]);
//                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//                httpCon.setRequestMethod("POST");
//                httpCon.setDoInput(true);
//                httpCon.setDoOutput(true);
//                httpCon.connect();
//
//                DataOutputStream outputStream = new DataOutputStream(httpCon.getOutputStream());
//                outputStream.flush();
//                outputStream.close();
//                InputStream inputStream = httpCon.getInputStream();
//                Scanner scanner = new Scanner(inputStream,"Windows-874");
//                response = scanner.useDelimiter("\\A").next();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return response;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            Intent intent = new Intent(getBaseContext(),DetailActivity.class);
//            intent.putExtra("Json_data",s);
//            startActivity(intent);
//            Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
//        }
//    }
}
