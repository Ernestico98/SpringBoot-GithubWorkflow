package com.harbourspace.HW7;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface IDatabase {

    void addItem(ListItem item);
    ListItem getItem(int id) throws Exception;

    ArrayList<ListItem> getAll();
}
