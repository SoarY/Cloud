package com.soar.cloud.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.soar.cloud.R;
import com.soar.cloud.bean.AndroidBean;
import com.soar.cloud.databinding.ItemEverydayOneBinding;
import com.soar.cloud.databinding.ItemEverydayThreeBinding;
import com.soar.cloud.databinding.ItemEverydayTitleBinding;
import com.soar.cloud.databinding.ItemEverydayTwoBinding;
import com.soar.cloud.utils.CommonUtils;

import java.util.List;

/**
 * NAME：YONG_
 * Created at: 2019/1/7
 * Describe:
 */
public class EverydayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //Type
    private static final int TYPE_TITLE = 1; // Title
    private static final int TYPE_ONE = 2;// 一张图
    private static final int TYPE_TWO = 3;// 二张图
    private static final int TYPE_THREE = 4;// 三张图

    public List<List<AndroidBean>> datas;
    private ItemClickListener itemClickListener;

    public void setData(List<List<AndroidBean>> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public List<AndroidBean> getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (!TextUtils.isEmpty(datas.get(position).get(0).getType_title()))
            return TYPE_TITLE;
        else if (datas.get(position).size() == 1)
            return TYPE_ONE;
        else if (datas.get(position).size() == 2)
            return TYPE_TWO;
        else if (datas.get(position).size() == 3)
            return TYPE_THREE;
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                return new TitleHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_everyday_title, parent, false));
            case TYPE_ONE:
                return new OneHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_everyday_one, parent, false));
            case TYPE_TWO:
                return new TwoHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_everyday_two, parent, false));
            default:
                return new ThreeHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_everyday_three, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_TITLE:
                bindTitleHolder((TitleHolder) holder, position);
                break;
            case TYPE_ONE:
                bindOneHolder((OneHolder) holder, position);
                break;
            case TYPE_TWO:
                bindTwoHolder((TwoHolder) holder, position);
                break;
            default:
                bindThreeHolder((ThreeHolder) holder, position);
        }
        if (itemClickListener!=null)
        holder.itemView.setOnClickListener(v->itemClickListener.onItemClick(position));
    }

    private void bindTitleHolder(TitleHolder holder, int position) {
        String title = getItem(position).get(0).getType_title();
        holder.binding.tvTitleType.setText(title);
        holder.binding.viewLine.setVisibility(position != 0 ? View.VISIBLE : View.GONE);
        holder.binding.executePendingBindings();
    }

    private void bindOneHolder(OneHolder holder, int position) {
        List<AndroidBean> item = getItem(position);
        Glide.with(holder.binding.ivOnePhoto.getContext())
                .load(item.get(0).getImage_url())
                .placeholder(R.mipmap.ic_item_one)
                .error(R.mipmap.ic_item_one)
                .crossFade(1500)
                .into(holder.binding.ivOnePhoto);
        holder.binding.tvOnePhotoTitle.setText(item.get(0).getDesc());
        holder.binding.executePendingBindings();
    }

    private void bindTwoHolder(TwoHolder holder, int position) {
        List<AndroidBean> item = getItem(position);
        Glide.with(holder.binding.ivTwoOneOne.getContext())
                .load(item.get(0).getImage_url())
                .placeholder(R.mipmap.ic_item_one)
                .error(R.mipmap.ic_item_one)
                .crossFade(1500)
                .into(holder.binding.ivTwoOneOne);
        Glide.with(holder.binding.ivTwoOneTwo.getContext())
                .load(item.get(1).getImage_url())
                .placeholder(R.mipmap.ic_item_one)
                .error(R.mipmap.ic_item_one)
                .crossFade(1500)
                .into(holder.binding.ivTwoOneTwo);
        holder.binding.tvTwoOneOneTitle.setText(item.get(0).getDesc());
        holder.binding.tvTwoOneTwoTitle.setText(item.get(1).getDesc());
        holder.binding.executePendingBindings();
    }

    private void bindThreeHolder(ThreeHolder holder, int position) {
        List<AndroidBean> item = getItem(position);
        Glide.with(holder.binding.ivThreeOneOne.getContext())
                .load(item.get(0).getImage_url())
                .placeholder(R.mipmap.ic_item_one)
                .error(R.mipmap.ic_item_one)
                .dontAnimate()
                .into(holder.binding.ivThreeOneOne);
        Glide.with(holder.binding.ivThreeOneTwo.getContext())
                .load(item.get(1).getImage_url())
                .placeholder(R.mipmap.ic_item_one)
                .error(R.mipmap.ic_item_one)
                .dontAnimate()
                .into(holder.binding.ivThreeOneTwo);
        Glide.with(holder.binding.ivThreeOneThree.getContext())
                .load(item.get(2).getImage_url())
                .placeholder(R.mipmap.ic_item_one)
                .error(R.mipmap.ic_item_one)
                .dontAnimate()
                .into(holder.binding.ivThreeOneThree);
        holder.binding.tvThreeOneOneTitle.setText(item.get(0).getDesc());
        holder.binding.tvThreeOneTwoTitle.setText(item.get(1).getDesc());
        holder.binding.tvThreeOneThreeTitle.setText(item.get(2).getDesc());
        holder.binding.executePendingBindings();
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class TitleHolder extends RecyclerView.ViewHolder {
        public ItemEverydayTitleBinding binding;

        public TitleHolder(ItemEverydayTitleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class OneHolder extends RecyclerView.ViewHolder {
        public ItemEverydayOneBinding binding;

        public OneHolder(ItemEverydayOneBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class TwoHolder extends RecyclerView.ViewHolder {
        public ItemEverydayTwoBinding binding;

        public TwoHolder(ItemEverydayTwoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class ThreeHolder extends RecyclerView.ViewHolder {
        public ItemEverydayThreeBinding binding;

        public ThreeHolder(ItemEverydayThreeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
