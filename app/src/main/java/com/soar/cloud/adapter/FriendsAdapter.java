package com.soar.cloud.adapter;

import android.graphics.Color;

import com.soar.cloud.BR;
import com.soar.cloud.MyApplication;
import com.soar.cloud.R;
import com.soar.cloud.base.BaseAdapter;
import com.soar.cloud.bean.PersonBean;
import com.soar.cloud.bean.SubjectsBean;
import com.soar.cloud.databinding.ItemFriendsBinding;

import java.util.List;
import java.util.Random;

/**
 * NAME：YONG_
 * Created at: 2019/1/7
 * Describe:
 */
public class FriendsAdapter extends BaseAdapter<SubjectsBean, ItemFriendsBinding> {
    @Override
    protected int initLayoutId() {
        return R.layout.item_friends;
    }

    @Override
    protected void onBindView(RecyclerHolder<ItemFriendsBinding> holder, SubjectsBean data, int position) {
        holder.binding.setVariable(BR.item, data);
        //        holder.itemView.animate().scaleX(0.8f).scaleY(0.8f).setDuration(350).start();
    }

    /**
     * 格式化导演、主演名字
     */
    public static StringBuilder formatName(List<PersonBean> casts) {
        StringBuilder stringBuilder = new StringBuilder();
        if (casts == null || casts.size() == 0)
            return stringBuilder.append(MyApplication.getContext().getString(R.string.movie_nameless));
        for (int i = 0; i < casts.size(); i++)
            stringBuilder.append(casts.get(i).name).append(i < casts.size() - 1 ? " / " : "");
        return stringBuilder;
    }

    /**
     * 格式化电影类型
     */
    public static StringBuilder formatGenres(List<String> genres) {
        StringBuilder stringBuilder = new StringBuilder();
        if (genres == null || genres.size() == 0)
            return stringBuilder.append(MyApplication.getContext().getString(R.string.movie_nameless_type));
        for (int i = 0; i < genres.size(); i++)
            stringBuilder.append(genres.get(i)).append(i < genres.size() - 1 ? " / " : "");
        return stringBuilder;
    }

    /**
     * 随机颜色
     */
    public static int randomColor() {
        Random random = new Random();
        int red = random.nextInt(150) + 50;//50-199
        int green = random.nextInt(150) + 50;//50-199
        int blue = random.nextInt(150) + 50;//50-199
        return Color.rgb(red, green, blue);
    }
}
