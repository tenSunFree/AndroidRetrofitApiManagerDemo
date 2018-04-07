package com.example.administrator.androidretrofitapimanagerdemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.androidretrofitapimanagerdemo.R;
import com.example.administrator.androidretrofitapimanagerdemo.adapter.ContributionAdapter;
import com.example.administrator.androidretrofitapimanagerdemo.api.ApiClient;
import com.example.administrator.androidretrofitapimanagerdemo.api.ApiManager;
import com.example.administrator.androidretrofitapimanagerdemo.interfaces.ApiService;
import com.example.administrator.androidretrofitapimanagerdemo.interfaces.OnResponse;
import com.example.administrator.androidretrofitapimanagerdemo.model.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static boolean isRecreate = false;
    public static boolean isOpenApplicationForTheFirstTime = true;
    public static int ContributionAdapterParentMeasuredHeight = 0;
    public static int ContributionAdapterParentMeasuredWidth = 0;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private LinearLayout progressbarLinearLayout;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        progressbarLinearLayout = findViewById(R.id.progressbarLinearLayout);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.getIndeterminateDrawable().setColorFilter(                                      // change indeterminate ProgressBar color
                getResources().getColor(R.color.colorProgressBar),
                android.graphics.PorterDuff.Mode.SRC_IN);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));                       // 设置布局管理器
        recyclerView.setItemAnimator(new DefaultItemAnimator());                                    // 设置Item添加和移除的动画
        recyclerView.addItemDecoration(
                new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));            // 设置Item之间间隔样式(分割线)


        /** 添加分隔线 */
        // final int gridMargin = getResources().getDimensionPixelOffset(R.dimen.recycleView_padding);    // 获取资源文件中定义的尺寸大小
        // recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
        //     /** Item的偏移量 */
        //     @Override
        //     public void getItemOffsets(Rect outRect, View view,
        //                                RecyclerView parent, RecyclerView.State state) {
        //         outRect.set(gridMargin, gridMargin, gridMargin, gridMargin);
        //     }
        // });

        /** 創建要去請求API的Client Service */
        Call<List<Contributor>> call = ApiClient.getClient().create(ApiService.class).
                contributors("bumptech", "glide");

        /** 發出請求(異步) */
        ApiManager.requestToServer(this, call, new OnResponse() {
            @Override
            public void onSuccess(Response response) {
                System.out.println("response " + response.body());
                List<Contributor> contributorList = (List<Contributor>) response.body();            // 使用response.body()得到結果
                if (contributorList.size() > 0) {
                    final ContributionAdapter adapter =
                            new ContributionAdapter(MainActivity.this, contributorList);
                    recyclerView.setAdapter(adapter);                                               // 设置adapter
                    progressBar.setVisibility(View.GONE);
                    progressbarLinearLayout.setVisibility(View.GONE);

                    if (isRecreate == false) {
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                recreate();
                            }
                        }, 200);
                        isRecreate = true;
                    }
                }
            }

            @Override
            public void onFailure(Response response) {
                progressBar.setVisibility(View.GONE);
                progressbarLinearLayout.setVisibility(View.GONE);
                Toast.makeText(context, "onFailure()", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
