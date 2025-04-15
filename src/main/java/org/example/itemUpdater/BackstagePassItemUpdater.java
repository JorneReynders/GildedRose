package org.example.itemUpdater;

import org.example.Item;

public class BackstagePassItemUpdater extends BaseItemUpdater {
    private static final int CLOSE_DAYS_THRESHOLD = 10;
    private static final int VERY_CLOSE_DAYS_THRESHOLD = 5;

    @Override
    public void dailyItemUpdate(Item item) {
        decrementSellIn(item);
        if (isExpired(item)) {
            item.quality = 0;
            return;
        }

        increaseQuality(item, calculateQualityIncrease(item.sellIn));
    }

    private int calculateQualityIncrease(int remainingDays) {
        int increase = 1;
        if (remainingDays < CLOSE_DAYS_THRESHOLD) increase++;
        if (remainingDays < VERY_CLOSE_DAYS_THRESHOLD) increase++;
        return increase;
    }
}
