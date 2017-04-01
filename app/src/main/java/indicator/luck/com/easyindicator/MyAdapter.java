package indicator.luck.com.easyindicator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author：luck
 * project：LoveCare
 * package：com.tongyu.luck.lovecare.adapter
 * email：893855882@qq.com
 * data：16/8/12
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.DefaultViewHolder> {
    private Context mContext;

    public MyAdapter(Context context) {
        this.mContext = context;

    }

    @Override
    public DefaultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        return new DefaultViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition(), v);
            }
        }
    }

    public OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
