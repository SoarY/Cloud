package com.soar.cloud.adapter;

import com.soar.cloud.BR;
import com.soar.cloud.R;
import com.soar.cloud.base.BaseAdapter;
import com.soar.cloud.bean.GankIoDataBean;
import com.soar.cloud.databinding.ItemWelfareBinding;

/**
 * NAME：YONG_
 * Created at: 2019/1/7
 * Describe:
 */
public class WelfareAdapter extends BaseAdapter<GankIoDataBean.ResultBean, ItemWelfareBinding> {
    @Override
    protected int initLayoutId() {
        return R.layout.item_welfare;
    }

    @Override
    protected void onBindView(RecyclerHolder<ItemWelfareBinding> holder, GankIoDataBean.ResultBean data, int position) {
        holder.binding.setVariable(BR.item, data);
    }
}
