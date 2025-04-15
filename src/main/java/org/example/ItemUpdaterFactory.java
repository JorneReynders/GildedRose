package org.example;

import org.example.itemUpdater.*;

import java.util.Map;

public class ItemUpdaterFactory {
    //Initialize all the updaters
    private static final ItemUpdater DEFAULT_UPDATER = new DefaultItemUpdater();
    private static final Map<String, ItemUpdater> UPDATER_MAP = Map.of(
            "Aged Brie", new AgedBrieUpdater(),
            "Sulfuras, Hand of Ragnaros", new LegendaryItemUpdater()
    );
    private static final Map<String, ItemUpdater> PREFIX_UPDATER_MAP = Map.of(
            "Backstage passes ", new BackstagePassItemUpdater(),
            "Conjured ", new ConjuredItemUpdater()
    );


    public static ItemUpdater getUpdaterFor(Item item) {
        ItemUpdater updater = UPDATER_MAP.get(item.name);
        if (updater != null) {
            return updater;
        }

        for (String prefix : PREFIX_UPDATER_MAP.keySet()) {
            if (item.name.startsWith(prefix)) {
                return PREFIX_UPDATER_MAP.get(prefix);
            }
        }
        return DEFAULT_UPDATER;
    }
}

