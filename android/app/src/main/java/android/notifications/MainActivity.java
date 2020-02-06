package android.notifications;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends Activity {

    ListView list;
    CustomListAdapter adapter;
    ArrayList<Model> modelList;

    OkHttpClient client = new OkHttpClient();

    void post(String url, RequestBody data) throws IOException {
        Log.e("network", "start...");
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("search", "a")
//                .addFormDataPart("model", "1")
//                .addFormDataPart("in", "1")
//                .addFormDataPart("id", "1")
//                .build();
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(data)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("response", myResponse);
                        }
                    });
                }
            }
        });

        //        Request request = new Request.Builder()
//        .url(url).post(data).build();
//        try (Response response = client.newCall(request).execute()) {
//            Log.e("network", response.body().string());
////            return response.body().string();
//        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelList = new ArrayList<Model>();
        adapter = new CustomListAdapter(getApplicationContext(), modelList);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(
                        "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private BroadcastReceiver onNotice= new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
           // String pack = intent.getStringExtra("package");
            String title = intent.getStringExtra("title");
            String text = intent.getStringExtra("text");
            //int id = intent.getIntExtra("icon",0);

            Context remotePackageContext = null;
            try {
//                remotePackageContext = getApplicationContext().createPackageContext(pack, 0);
//                Drawable icon = remotePackageContext.getResources().getDrawable(id);
//                if(icon !=null) {
//                    ((ImageView) findViewById(R.id.imageView)).setBackground(icon);
//                }
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("title", title)
                        .addFormDataPart("text", text)
                        .build();
                post("https://www.maxbase.org/service/notification/?title=test&text=hi", requestBody);

//                byte[] byteArray =intent.getByteArrayExtra("icon");
//                Bitmap bmp = null;
//                if(byteArray !=null) {
//                    bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//                }
//                Model model = new Model();
//                model.setName(title +" " +text);
//                model.setImage(bmp);
//                Log.v("Notification:", title + " " + text);
//
//                if(modelList !=null) {
//                    modelList.add(model);
//                    adapter.notifyDataSetChanged();
//                }else {
//                    modelList = new ArrayList<Model>();
//                    modelList.add(model);
//                    adapter = new CustomListAdapter(getApplicationContext(), modelList);
//                    list=(ListView)findViewById(R.id.list);
//                    list.setAdapter(adapter);
//                }

            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    };
}
