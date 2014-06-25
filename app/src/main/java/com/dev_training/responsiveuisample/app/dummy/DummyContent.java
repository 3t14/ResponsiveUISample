package com.dev_training.responsiveuisample.app.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("http://www.yahoo.co.jp", "Yahoo!Japan", "国内最大ポータルサイト"));
        addItem(new DummyItem("http://www.google.co.jp", "Google", "検索最大手"));
        addItem(new DummyItem("http://www.ics.co.jp", "株式会社アイシーエス", "自治体・医療機関向けなどのシステムインテグレーション事業を展開"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.url, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String url;
        public String title;
        public String description;

        public DummyItem(String url, String title, String description) {
            this.url = url;
            this.title = title;
            this.description = description;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
