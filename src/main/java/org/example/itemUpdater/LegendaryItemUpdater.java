package org.example.itemUpdater;

import org.example.Item;

public class LegendaryItemUpdater extends BaseItemUpdater {
    @Override
    public void dailyItemUpdate(Item item) {
        //Legendary items do not change in quality or remainingDays
    }
}
