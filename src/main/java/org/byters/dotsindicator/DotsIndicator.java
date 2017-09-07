package org.byters.dotsindicator;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DotsIndicator {
    private final FrameLayout flIndicator;

    private LinearLayout llDots;
    private int savedPos;

    public DotsIndicator(FrameLayout flIndicator) {
        this.flIndicator = flIndicator;
        savedPos = -1;
    }

    public void init() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        llDots = new LinearLayout(flIndicator.getContext());
        llDots.setLayoutParams(params);
        this.flIndicator.addView(llDots);
    }

    public void initDots() {
        savedPos = -1;
        updateDots(0);
    }

    private void updateDots(int pos) {
        if (savedPos == pos) return;
        savedPos = pos;
        for (int i = 0; i < llDots.getChildCount(); ++i)
            ((ImageView) llDots.getChildAt(i)).setImageResource(i == pos ? R.drawable.dot_accent : R.drawable.dot_grey);
    }

    public void setDots(int itemCount) {
        if (itemCount <= 0)
            return;

        llDots.removeAllViews();

        int margin = (int) llDots.getContext().getResources().getDimension(R.dimen.dot_photo_list_margin);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, margin, 0, margin);

        for (int i = 0; i < itemCount; ++i) {

            ImageView imageView = new ImageView(llDots.getContext());
            imageView.setImageResource(R.drawable.dot_accent);
            imageView.setLayoutParams(params);
            llDots.addView(imageView);
        }
    }

    public void setVisibility(int visibility) {
        flIndicator.setVisibility(visibility);
    }

    void onScroll(int pos, int right) {
        if (right < llDots.getContext().getResources().getDisplayMetrics().widthPixels / 2) pos += 1;
        updateDots(pos);
    }

    @Deprecated
    public void onScroll(int pos, View v) {
        onScroll(pos, v.getRight());
    }
}
