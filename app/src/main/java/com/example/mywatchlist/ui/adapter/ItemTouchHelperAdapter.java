package com.example.mywatchlist.ui.adapter;

import android.content.Context;

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);
    void onItemSwiped (int position);

    Context getContext();
}
