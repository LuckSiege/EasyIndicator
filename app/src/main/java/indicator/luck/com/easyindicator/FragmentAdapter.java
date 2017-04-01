package indicator.luck.com.easyindicator;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * author：luck
 * project：EasyIndicator
 * package：com.luck.indicator
 * email：893855882@qq.com
 * data：2017/4/1
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;

    public FragmentAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
