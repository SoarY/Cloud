package com.soar.cloud.adapter;

import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soar.cloud.BR;
import com.soar.cloud.R;
import com.soar.cloud.base.BaseAdapter;
import com.soar.cloud.bean.ArticlesBean;
import com.soar.cloud.bean.NaviBean;
import com.soar.cloud.databinding.ItemNaviContentBinding;
import com.soar.cloud.utils.CommonUtils;
import com.soar.cloud.utils.ToastUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * NAME：YONG_
 * Created at: 2019/1/11
 * Describe:
 */
public class NaviContentAdapter extends BaseAdapter<NaviBean, ItemNaviContentBinding> {

    @Override
    protected int initLayoutId() {
        return R.layout.item_navi_content;
    }

    @Override
    protected void onBindView(RecyclerHolder<ItemNaviContentBinding> holder, NaviBean data, int position) {
        holder.binding.setVariable(BR.item, data);
        showTagView(holder.binding.tflContent, data.articles);
    }

    private void showTagView(TagFlowLayout tflContent, List<ArticlesBean> articles) {
        tflContent.setAdapter(new TagAdapter<ArticlesBean>(articles) {
            @Override
            public View getView(FlowLayout parent, int position, ArticlesBean bean) {
                TextView textView = getTextView();
                textView.setText(Html.fromHtml(bean.getTitle()));
                return textView;
            }
        });
        tflContent.setOnTagClickListener((view, position, parent) -> {
            ToastUtils.showToast(articles.get(position).getTitle());
            return true;
        });
    }

    private TextView getTextView() {
        final TextView hotText = new TextView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        hotText.setLayoutParams(lp);
        hotText.setTextSize(13);
        int left, top, right, bottom;
        hotText.setMaxLines(1);
        left = top = right = bottom = CommonUtils.dip2px(5);
        hotText.setBackgroundResource(R.drawable.shape_navi_tag);
        hotText.setGravity(Gravity.CENTER);
        lp.setMargins(left, top, right, bottom);
        return hotText;
    }
}
