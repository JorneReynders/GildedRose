package org.example;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void updateQuality_normalItem() {
        GildedRose app = appWithSingleItem("normalItem", 10, 20);

        app.updateQuality();

        assertEquals("normalItem", app.items[0].name);
        assertEquals(19, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void updateQuality_higherDegradationAfterSellDate() {
        GildedRose app = appWithSingleItem("normalItem", 1, 20);

        app.updateQuality();
        assertEquals(19, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(17, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void updateQuality_qualityDoesNotGoNegative() {
        GildedRose app = appWithSingleItem("normalItem", 5, 1);


        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }

    @Test
    void updateQuality_agedBrieIncreasesQuality() {
        GildedRose app = appWithSingleItem("Aged Brie", 2, 10);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
        app.updateQuality();
        assertEquals(14, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    @Disabled
    void intializeItems_itemQualityIsNotAbove50() {
        //Currently only checked when updating Aged Brie/Backstage passes
        GildedRose app = appWithSingleItem("normalItem", 2, 100);
        assertEquals(50, app.items[0].quality);
        assertEquals(2, app.items[0].sellIn);
    }

    @Test
    void updateQuality_itemQualityIsNotAbove50() {
        GildedRose app = new GildedRose(new Item[]{new Item("Aged Brie", 2, 50), new Item("Backstage passes to a TAFKAL80ETC concert", 2, 50)});

        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
        assertEquals(50, app.items[1].quality);
        assertEquals(1, app.items[1].sellIn);
    }

    @Test
    void updateQuality_sulfurasItemsAreImmutable() {
        GildedRose app = appWithSingleItem("Sulfuras, Hand of Ragnaros", 2, 50);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(2, app.items[0].sellIn);
    }

    @Test
    void updateQuality_backstagePassesIncreaseCorrectlyBeforeConcert() {
        GildedRose app = appWithSingleItem("Backstage passes to a TAFKAL80ETC concert", 11, 20);

        //+1 For remainingDays >10
        app.updateQuality();

        assertEquals(21, app.items[0].quality);
        assertEquals(10, app.items[0].sellIn);

        //+2 For remainingDays <10 >5
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertEquals(31, app.items[0].quality);
        assertEquals(5, app.items[0].sellIn);

        //+3 For remainingDays <5
        app.updateQuality();

        assertEquals(34, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }

    @Test
    void updateQuality_backstagePassesRemoveQualityOnceExpired() {
        GildedRose app = appWithSingleItem("Backstage passes to a TAFKAL80ETC concert", 0, 20);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void updateQuality_conjuredItemsDegradeTwiceAsFast() {
        GildedRose app = appWithSingleItem("Conjured Mana Cake", 10, 20);

        app.updateQuality();

        assertEquals(9, app.items[0].sellIn);
        assertEquals(18, app.items[0].quality);
    }

    @Test
    void updateQuality_conjuredItemsDegradeTwiceAsFastOnceExpired() {
        GildedRose app = appWithSingleItem("Conjured Mana Cake", 0, 20);

        app.updateQuality();

        assertEquals(-1, app.items[0].sellIn);
        assertEquals(16, app.items[0].quality);
    }

    private GildedRose appWithSingleItem(String name, int sellIn, int quality) {
        return new GildedRose(new Item[]{new Item(name, sellIn, quality)});
    }
}