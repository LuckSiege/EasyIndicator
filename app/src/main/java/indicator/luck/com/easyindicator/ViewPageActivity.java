package indicator.luck.com.easyindicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.luck.indicator.EasyIndicator;

public class ViewPageActivity extends AppCompatActivity {
    public final static String TAG = "ViewPageActivity";
    private ViewPager vp;
    private EasyIndicator easy_indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage_view);
        vp = (ViewPager) findViewById(R.id.vp);
        easy_indicator = (EasyIndicator) findViewById(R.id.easy_indicator);
        easy_indicator.setTabTitles(new String[]{"Tab1", "Tab2", "Tab3", "Tab4"});

        // 自定义设置
        easy_indicator.setViewPage(vp, new FragmentAdapter(getSupportFragmentManager(),
                new Fragment[]{new FragmentTab1(), new FragmentTab2(),
                        new FragmentTab3(), new FragmentTab4()}));

        // 自带设置
//        easy_indicator.setViewPage(new FragmentAdapter(getSupportFragmentManager(),
//                new Fragment[]{new FragmentTab1(), new FragmentTab2(),
//                        new FragmentTab3(), new FragmentTab4()}));


        easy_indicator.setOnTabClickListener(new EasyIndicator.onTabClickListener() {
            @Override
            public void onTabClick(String title, int position) {
                Log.i(TAG, title + "::" + position);
            }
        });
    }
}
