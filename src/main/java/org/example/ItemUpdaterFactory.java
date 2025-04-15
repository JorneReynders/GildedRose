package org.example;

import org.example.itemUpdater.*;

import java.util.Map;

public class ItemUpdaterFactory {
    //Initialize all the updaters
    private static final ItemUpdater DEFAULT_UPDATER = new DefaultItemUpdater();
    private static final Map<String, ItemUpdater> matchingUpdaterMap = Map.of(
            "Aged Brie", new AgedBrieUpdater(),
            "Sulfuras, Hand of Ragnaros", new LegendaryItemUpdater()
    );
    private static final Map<String, ItemUpdater> prefixUpdaterMap = Map.of(
            "Backstage passes ", new BackstagePassItemUpdater(),
            "Conjured ", new ConjuredItemUpdater()
    );


    public static ItemUpdater getUpdaterFor(Item item) {
        ItemUpdater updater = matchingUpdaterMap.get(item.name);
        if (updater != null) {
            return updater;
        }

        for (String prefix : prefixUpdaterMap.keySet()) {
            if (item.name.startsWith(prefix)) {
                return prefixUpdaterMap.get(prefix);
            }
        }
        return DEFAULT_UPDATER;
    }
}

