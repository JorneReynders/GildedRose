package org.example;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdaterFactory.getUpdaterFor(item).dailyItemUpdate(item);
        }
    }
}