package com.example.asynctask_slominski_march17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView1;
    private String[] texts = {"Lots", "of", "words", "separated", "by", "separators", "that", "are",
            "recognized", "all", "over", "the", "world", "Lots", "of", "words", "separated", "by", "separators", "that", "are",
            "recognized", "all", "over", "the", "world", "Lots", "of", "words", "separated", "by", "separators", "that", "are",
            "recognized", "all", "over", "the", "world",};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);

        listView1 = (ListView) findViewById(R.id.listView1);
        listView1.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new ArrayList<String>()));

        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void,String,Void> {
        private ArrayAdapter<String> adapter;
        private int count = 0;

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>) listView1.getAdapter();
            setProgressBarIndeterminate(false);
            setProgressBarVisibility(true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (String item : texts) {
                publishProgress(item);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
            count++;
            setProgress((int)(((double)count/texts.length)*10000));
        }

        @Override
        protected void onPostExecute(Void result) {
           setProgressBarVisibility(false);

        }
    }
}
