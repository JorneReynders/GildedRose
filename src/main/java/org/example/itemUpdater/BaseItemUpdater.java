package org.example.itemUpdater;

import org.example.Item;

//Would normally place this logic in Item, considered using a wrapper class but decided against it.
public abstract class BaseItemUpdater implements ItemUpdater {
    protected static final int MAX_QUALITY = 50;
    protected static final int MIN_QUALITY = 0;

    protected void increaseQuality(Item item, int amount) {
        item.quality = Math.min(MAX_QUALITY, item.quality + amount);
    }

    protected void decreaseQuality(Item item, int amount) {
        item.quality = Math.max(MIN_QUALITY, item.quality - amount);
    }

    protected boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    protected void decrementSellIn(Item item) {
        item.sellIn--;
    }
}
