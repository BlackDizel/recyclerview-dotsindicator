package org.byters.dotsindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ViewDotsIndicator extends LinearLayout {

    private int drawableResSelected, drawableResBackground;

    @Nullable
    private LinearLayoutManager layoutManager;
    private int savedPos;

    @Nullable
    private ViewDotsIndicatorListener listener;

    public ViewDotsIndicator(@NonNull Context context) {
        super(context);
        initView(null);
    }

    public ViewDotsIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }


    public ViewDotsIndicator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        savedPos = -1;

        initDrawables(attrs);

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
        updateDots(0);
    }

    private void initDrawables(AttributeSet attrs) {
        if (attrs == null) {
            drawableResSelected = R.drawable.dot_accent;
            drawableResBackground = R.drawable.dot_grey;

        } else {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.dotsindicator,
                    0, 0);

            drawableResSelected = a.getResourceId(R.styleable.dotsindicator_drawableSelected, R.drawable.dot_accent);
            drawableResBackground = a.getResourceId(R.styleable.dotsindicator_drawableBackground, R.drawable.dot_grey);
            a.recycle();
        }
    }

    public void init(RecyclerView recyclerView) {

        if (!(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
            android.util.Log.e("dotsindicator", "only linear layout manager supported");
            return;
        }

        recyclerView.addOnScrollListener(new ScrollListener());

        this.layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        if (recyclerView.getAdapter() != null)
            updateData(recyclerView.getAdapter().getItemCount(), 0);

    }

    public void updateData(int itemCount, int currentPos) {
        setDots(itemCount);
        savedPos = -1;
        updateDots(currentPos);
    }

    private void setDots(int itemCount) {
        if (itemCount <= 0)
            return;

        removeAllViews();

        int margin = (int) getContext().getResources().getDimension(R.dimen.dot_photo_list_margin);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, margin, 0, margin);

        for (int i = 0; i < itemCount; ++i) {

            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(drawableResSelected);
            imageView.setLayoutParams(params);
            addView(imageView);
        }

    }

    private void updateDots(int pos) {
        if (savedPos == pos) return;
        notifyPos(pos);
        savedPos = pos;
        for (int i = 0; i < getChildCount(); ++i)
            ((ImageView) getChildAt(i)).setImageResource(i == pos ? drawableResSelected : drawableResBackground);
    }

    public void setListener(ViewDotsIndicatorListener listener) {
        this.listener = listener;
    }

    private void notifyPos(int pos) {
        if (listener == null) return;
        listener.onDotActive(pos);
    }

    private class ScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (layoutManager == null) return;

            int pos = layoutManager.findFirstVisibleItemPosition();

            if (layoutManager.findViewByPosition(pos).getRight() < getContext().getResources().getDisplayMetrics().widthPixels / 2)
                pos += 1;

            updateDots(pos);
        }
    }


}
