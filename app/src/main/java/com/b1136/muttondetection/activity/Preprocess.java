package com.b1136.muttondetection.activity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.b1136.muttondetection.R;
import com.b1136.muttondetection.tool.TestWekaJar;

import java.io.InputStream;

public class Preprocess extends AppCompatActivity {
    private Button preAndFea;
    private Button predict;
    private EditText para1;
    private EditText para2;
    private TextView status;
    private Button cancel;
    private class PicPreprocessTask extends AsyncTask<String, Bitmap, double[]> {

        // 方法1：onPreExecute（）
        // 作用：执行 线程任务前的操作
        @Override
        protected void onPreExecute() {
            // 执行前显示提示
            status.setText("启动预处理");
        }


        // 方法2：doInBackground（）
        // 作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
        // 此处通过计算从而模拟“加载进度”的情况
        @Override
        protected double[] doInBackground(String... params) {


            return null;
        }

        // 方法3：onProgressUpdate（）
        // 作用：在主线程 显示线程任务执行的进度
        @Override
        protected void onProgressUpdate(Bitmap... progresses) {

        }

        // 方法4：onPostExecute（）
        // 作用：接收线程任务执行结果、将执行结果显示到UI组件
        @Override
        protected void onPostExecute(double[] result) {
            // 执行完毕后，则更新UI

            status.setText("处理完毕");
        }

        // 方法5：onCancelled()
        // 作用：将异步任务设置为：取消状态
        @Override
        protected void onCancelled() {
            status.setText("已取消");
        }
    }

    private PicPreprocessTask  picPreprocessTask= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preprocess);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.preToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        preAndFea = (Button)findViewById(R.id.preAndFea);
        predict = (Button)findViewById(R.id.predict);
        status = (TextView)findViewById(R.id.status);
        para1 = (EditText)findViewById(R.id.para1);
        para2 = (EditText)findViewById(R.id.para2);
        cancel = (Button)findViewById(R.id.cancel);
        preAndFea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(picPreprocessTask == null || picPreprocessTask.getStatus() == AsyncTask.Status.FINISHED)
                {
                    picPreprocessTask = new PicPreprocessTask();
                    picPreprocessTask.execute("");
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(picPreprocessTask!=null && picPreprocessTask.getStatus() != AsyncTask.Status.FINISHED){
                    picPreprocessTask.cancel(true);
                }
            }
        });
        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double [] a = {0.971934,0.042001,2.006766,63.552752,14.826509,16.234356,0.046106,0.406729,0.36819,140.156456,80.087101,61.422068};
                try {
                    InputStream reader = getResources().getAssets().open("bp.model");
                    String label = TestWekaJar.predictOneStance(a,reader);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
