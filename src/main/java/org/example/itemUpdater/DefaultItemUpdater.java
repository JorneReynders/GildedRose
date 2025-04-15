package org.example.itemUpdater;

import org.example.Item;

public class DefaultItemUpdater extends BaseItemUpdater {
    @Override
    public void dailyItemUpdate(Item item) {
        decrementSellIn(item);
        decreaseQuality(item, 1);
        if (isExpired(item)) decreaseQuality(item, 1);
    }

}
