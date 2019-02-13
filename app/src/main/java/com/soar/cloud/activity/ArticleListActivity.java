package com.soar.cloud.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.soar.cloud.BR;
import com.soar.cloud.R;
import com.soar.cloud.base.BaseActivity;
import com.soar.cloud.constant.RouteConstants;
import com.soar.cloud.databinding.ActivityArticleListBinding;
import com.soar.cloud.vm.AndroidPlayViewModel;

/**
 * NAME：YONG_
 * Created at: 2019/1/10
 * Describe:
 */
@Route(path = RouteConstants.Music.ARTICLE)
public class ArticleListActivity extends BaseActivity<ActivityArticleListBinding, AndroidPlayViewModel> {

    public static final String CID = "cid";
    public static final String CHAPTER_NAME = "chapterName";

    @Autowired
    public int cid;
    @Autowired
    public String chapterName;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_article_list;
    }

    @Override
    public int initVariableId() {
        return BR.vm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        initView();
        initData();
    }

    private void initView() {
        binding.incToolbarTitle.toolbar.setTitle(chapterName);
        binding.incToolbarTitle.toolbar.setNavigationIcon(R.mipmap.icon_back);
        binding.incToolbarTitle.toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void initData() {
        viewModel.setCID(cid);
        viewModel.getHomeList(true);
    }
}
