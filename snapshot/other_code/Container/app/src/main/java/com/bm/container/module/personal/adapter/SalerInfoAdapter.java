package com.bm.container.module.personal.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.container.R;
import com.bm.container.http.BaseBean;

import java.util.List;


public class SalerInfoAdapter extends RecyclerView.Adapter<SalerInfoAdapter.ViewHolder> {
    private List<BaseBean> col;

    public SalerInfoAdapter(List<BaseBean> col) {
        this.col = col;

    }

    @Override
    public int getItemCount() {
//		return col.isEmpty() ? 0 : col.size();
        return 10;
    }

    @SuppressLint("InflateParams")
    @Override
    public SalerInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saler_info, null);
        SalerInfoAdapter.ViewHolder vh = new SalerInfoAdapter.ViewHolder(view);

        return vh;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final SalerInfoAdapter.ViewHolder viewHolder, final int i) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
