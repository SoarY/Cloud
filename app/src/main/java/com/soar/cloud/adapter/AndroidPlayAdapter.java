package com.soar.cloud.adapter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.soar.cloud.BR;
import com.soar.cloud.R;
import com.soar.cloud.base.BaseAdapter;
import com.soar.cloud.bean.ArticlesBean;
import com.soar.cloud.constant.RouteConstants;
import com.soar.cloud.databinding.ItemAndroidPlayBinding;

/**
 * NAME：YONG_
 * Created at: 2019/1/7
 * Describe:
 */
public class AndroidPlayAdapter extends BaseAdapter<ArticlesBean, ItemAndroidPlayBinding> {
    @Override
    protected int initLayoutId() {
        return R.layout.item_android_play;
    }

    @Override
    protected void onBindView(RecyclerHolder<ItemAndroidPlayBinding> holder, ArticlesBean data, int position) {
        holder.binding.setVariable(BR.item, data);
        holder.binding.tvClassify.setOnClickListener(v ->
                ARouter.getInstance()
                        .build(RouteConstants.AndroidPlay.ARTICLE)
                        .withInt("cid", data.getChapterId())
                        .withString("chapterName", data.getChapterName())
                        .navigation());
    }

}
