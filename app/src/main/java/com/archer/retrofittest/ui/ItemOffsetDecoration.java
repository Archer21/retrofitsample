package com.archer.retrofittest.ui;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {


    //pixeles de espaciado
    private int mItemOffset;

    public ItemOffsetDecoration(Context context, @IntegerRes int integerResId) {
        int itemOffsetDp = context.getResources().getInteger(integerResId);
        //se le asigna los dp a la variable de espaciado
        mItemOffset = convertToPx(itemOffsetDp, context.getResources().getDisplayMetrics());
    }

    //convertir px a dp para que se adapte a diferentes pantallas
    public int convertToPx(int offSetDp, DisplayMetrics metrics) {
        return offSetDp * (metrics.densityDpi / 160);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //espaciado entre los objetos de la lista delos items
        outRect.set(mItemOffset,mItemOffset,mItemOffset,mItemOffset);
    }
}