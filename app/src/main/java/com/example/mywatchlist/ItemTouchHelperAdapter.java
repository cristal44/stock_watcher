package com.example.mywatchlist;

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);
    void onItemSwiped (int position);
}
