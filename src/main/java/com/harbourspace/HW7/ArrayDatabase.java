package com.harbourspace.HW7;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArrayDatabase implements IDatabase{

    private final ArrayList<ListItem> Items = new ArrayList<> ( List.of(
        new ListItem(1, 1, 4, "item1", "20/4/2023"),
        new ListItem(2, 1, 4, "item2", "21/5/2023"),
        new ListItem(3, 2, 3, "item3", "22/6/2023"),
        new ListItem(4, 2, 1, "item4", "23/7/2023")
    ));

    @Override
    public void addItem(ListItem item) {
        Items.add(item);
    }

    @Override
    public ListItem getItem(int id) throws Exception {
        var filtered = Items.stream().filter(v -> v.id() == id).toList();
        if (filtered.isEmpty()) {
            throw new Exception("Item not found");
        }
        return filtered.get(0);
    }

    @Override
    public ArrayList<ListItem> getAll() {
        return Items;
    }
}
