package com.practicelink;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.practicelink.model.EmployeeDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);
        new Details().execute();
    }

    public class Details extends AsyncTask<String, String, List<EmployeeDetails>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<EmployeeDetails> doInBackground(String... params) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("https://new-linky.herokuapp.com/users.json");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream steam = httpURLConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(steam));

                StringBuilder buffer = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String str = buffer.toString();

                JSONArray jsonArray = new JSONArray(str);
                List<EmployeeDetails> ModelList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    EmployeeDetails Model = new EmployeeDetails();
                    Model.setId(jsonObject.getString("id"));
                    Model.setFirst_name(jsonObject.getString("first_name"));
                    Model.setLast_name(jsonObject.getString("last_name"));
                    Model.setEmail(jsonObject.getString("email"));
                    Model.setMobile(jsonObject.getString("mobile"));
                    Model.setPassword(jsonObject.getString("password"));
                    Model.setCreated_at(jsonObject.getString("created_at"));
                    Model.setUpdated_at(jsonObject.getString("updated_at"));
                    Model.setUrl(jsonObject.getString("url"));

                    ModelList.add(Model);
                }

                return ModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(List<EmployeeDetails> s) {
            super.onPostExecute(s);
            EmployeeAdapter adapter = new EmployeeAdapter(getApplicationContext(), R.layout.list_item, s);
            list.setAdapter(adapter);
        }

        public class EmployeeAdapter extends ArrayAdapter {

            private List<EmployeeDetails> ModelList;
            private int resource;
            private LayoutInflater inflater;

            public EmployeeAdapter(Context context, int resource, List<EmployeeDetails> objects) {
                super(context, resource, objects);
                ModelList = objects;
                this.resource = resource;
                inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                ViewHolder holder;

                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = inflater.inflate(resource, null);
                    holder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
                    holder.tv_first_name = (TextView) convertView.findViewById(R.id.tv_first_name);
                    holder.tv_last_name = (TextView) convertView.findViewById(R.id.tv_last_name);
                    holder.tv_email = (TextView) convertView.findViewById(R.id.tv_email);
                    holder.tv_mobile = (TextView) convertView.findViewById(R.id.tv_mobile);
                    holder.tv_password = (TextView) convertView.findViewById(R.id.tv_password);
                    holder.tv_created_at = (TextView) convertView.findViewById(R.id.tv_created_at);
                    holder.tv_updated_at = (TextView) convertView.findViewById(R.id.tv_updated_at);
                    holder.tv_url = (TextView) convertView.findViewById(R.id.tv_url);

                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.tv_id.setText("id: " + ModelList.get(position).getId());
                holder.tv_first_name.setText("first_name: " + ModelList.get(position).getFirst_name());
                holder.tv_last_name.setText("last_name: " + ModelList.get(position).getLast_name());
                holder.tv_email.setText("email: " + ModelList.get(position).getEmail());
                holder.tv_mobile.setText("mobile: " + ModelList.get(position).getMobile());
                holder.tv_password.setText("password: " + ModelList.get(position).getPassword());
                holder.tv_created_at.setText("created_at: " + ModelList.get(position).getCreated_at());
                holder.tv_updated_at.setText("updated_at: " + ModelList.get(position).getUpdated_at());
                holder.tv_url.setText("url: " + ModelList.get(position).getUrl());

                return convertView;
            }

            class ViewHolder {
                private TextView tv_id, tv_first_name, tv_last_name, tv_email, tv_mobile, tv_password, tv_created_at, tv_updated_at, tv_url;
            }
        }
    }
}
