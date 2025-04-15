package org.example.itemUpdater;

import org.example.Item;

public class ConjuredItemUpdater extends BaseItemUpdater {
    @Override
    public void dailyItemUpdate(Item item) {
        decrementSellIn(item);
        decreaseQuality(item, 2);
        if (isExpired(item)) decreaseQuality(item, 2);
    }

}
