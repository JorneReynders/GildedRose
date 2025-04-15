package org.example;

import org.example.itemUpdater.ItemUpdater;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ItemUpdaterFactoryTest {

    @ParameterizedTest(name = "Item name: \"{0}\" should map to {1}")
    @CsvSource(delimiter = ';', value = {
            "Aged Brie; AgedBrieUpdater",
            "Backstage passes to a concert; BackstagePassItemUpdater",
            "Sulfuras, Hand of Ragnaros; LegendaryItemUpdater",
            "Conjured Random Item; ConjuredItemUpdater",
            "Normal Item; DefaultItemUpdater"
    })
    void getUpdaterFor_MatchItemNameCorrectlyWithExpectedClass(String itemName, String expectedUpdaterSimpleClassName) {
        Item item = new Item(itemName, 5, 10);
        ItemUpdater updater = ItemUpdaterFactory.getUpdaterFor(item);
        assertEquals(updater.getClass().getSimpleName(), expectedUpdaterSimpleClassName);
    }
}