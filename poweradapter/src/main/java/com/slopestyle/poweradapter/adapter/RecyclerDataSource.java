package com.slopestyle.poweradapter.adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.support.annotation.LayoutRes;
import android.support.annotation.VisibleForTesting;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import com.slopestyle.poweradapter.item.ItemRenderer;
import com.slopestyle.poweradapter.item.RecyclerItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerDataSource {

    private final Map<String, ItemRenderer<? extends RecyclerItem>> renderers;
    @SuppressLint("UseSparseArrays")
    private final Map<Integer, String> viewTypeToRenderKeyMay = new HashMap<>();
    private final List<RecyclerItem> data = new ArrayList<>();

    private WeakReference<RecyclerView.Adapter> adapterReference = new WeakReference<>(null);

    public RecyclerDataSource(Map<String, ItemRenderer<? extends RecyclerItem>> renderers) {
        this.renderers = renderers;

        for (Map.Entry<String, ItemRenderer<? extends RecyclerItem>> entry : renderers.entrySet()) {
            viewTypeToRenderKeyMay.put(entry.getValue().layoutRes(), entry.getKey());
        }
    }

    public void setData(List<? extends RecyclerItem> newData) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new RecyclerDiffCallback(data, newData));
        data.clear();
        data.addAll(newData);
        RecyclerView.Adapter adapter = adapterReference.get();
        if(adapter != null) {
            diffResult.dispatchUpdatesTo(adapter);
        }
    }

    ItemRenderer<RecyclerItem> rendererForType(int viewType) {
        // noinspection unchecked
        return (ItemRenderer<RecyclerItem>) renderers.get(viewTypeToRenderKeyMay.get(viewType));
    }

    @LayoutRes
    int viewResourceForPosition(int position) {
        return renderers.get(data.get(position).renderKey()).layoutRes();
    }

    int getCount() {
        return data.size();
    }

    RecyclerItem getItem(int position) {
        return data.get(position);
    }

    void attachToAdapter(RecyclerView.Adapter adapter) {
        adapterReference = new WeakReference<>(adapter);
    }

    /**
     * Allows us to set data without invoking DiffUtils which would throw an exception during unit testing.
     */
    @VisibleForTesting
    void seedData(List<RecyclerItem> data) {
        this.data.clear();
        this.data.addAll(data);
    }
}
