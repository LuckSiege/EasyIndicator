package indicator.luck.com.easyindicator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.luck.indicator.EasyIndicator;

public class RecyclerViewActivity extends AppCompatActivity {
    public final static String TAG = "RecyclerViewActivity";
    private EasyIndicator easy_indicator;
    private RecyclerView recyclerView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mContext = this;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        easy_indicator = (EasyIndicator) findViewById(R.id.easy_indicator);
        easy_indicator.setTabTitles(new String[]{"已完成", "已评价"});
        easy_indicator.setOnTabClickListener(new EasyIndicator.onTabClickListener() {
            @Override
            public void onTabClick(String title, int position) {
                Log.i(TAG, title + "::" + position);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecycleViewDivider(
                mContext, LinearLayoutManager.HORIZONTAL, dip2px(mContext, 0.5f), ContextCompat.getColor(mContext, R.color.line_color)));
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MyAdapter(mContext));
    }

    /**
     * dpתpx
     */
    public static int dip2px(Context ctx, float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
