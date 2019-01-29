package com.soar.cloud.adapter;

import com.soar.cloud.BR;
import com.soar.cloud.R;
import com.soar.cloud.base.BaseAdapter;
import com.soar.cloud.bean.NaviBean;
import com.soar.cloud.databinding.ItemNaviBinding;

/**
 * NAME：YONG_
 * Created at: 2019/1/11
 * Describe:
 */
public class NaviAdapter extends BaseAdapter<NaviBean, ItemNaviBinding> {

    @Override
    protected int initLayoutId() {
        return R.layout.item_navi;
    }

    @Override
    protected void onBindView(RecyclerHolder<ItemNaviBinding> holder, NaviBean data, int position) {
        holder.binding.setVariable(BR.item, data);
        holder.binding.tvTitle.setSelected(data.selected);
        holder.binding.tvTitle.setOnClickListener(v -> {
            setSelected(position);
            if (listener != null)
                listener.onSelected(position);
        });
    }

    public void setSelected(int position) {
        for (int i = 0; i < datas.size(); i++)
            datas.get(i).selected = i == position;
        notifyDataSetChanged();
    }

    private OnSelectListener listener;

    public void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }

    public interface OnSelectListener {
        void onSelected(int position);
    }
}
