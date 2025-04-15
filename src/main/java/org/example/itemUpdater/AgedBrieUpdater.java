package org.example.itemUpdater;

import org.example.Item;

public class AgedBrieUpdater extends BaseItemUpdater {

    @Override
    public void dailyItemUpdate(Item item) {
        decrementSellIn(item);
        increaseQuality(item, 1);
        if (isExpired(item)) increaseQuality(item, 1);
    }

}
