package indicator.luck.com.easyindicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author：luck
 * project：EasyIndicator
 * package：com.luck.indicator
 * email：893855882@qq.com
 * data：2017/4/1
 */

public class FragmentTab1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fc_tab1, container, false);
        return inflate;
    }
}
