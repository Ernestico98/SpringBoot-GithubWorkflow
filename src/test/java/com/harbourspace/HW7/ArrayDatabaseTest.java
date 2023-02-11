package com.harbourspace.HW7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArrayDatabaseTest {

    @Autowired
    IDatabase DB;

    @BeforeEach
    public void setUp() {
        DB = new ArrayDatabase();
    }

    @Test
    public void getAllShouldReturnFOurItemsByDefault() throws Exception{
        var itemList = DB.getAll();
        var size = itemList.stream().toList().size();
        assertEquals(size, 4);
    }

    @Test
    public void GetItemReturnsElement() throws Exception {
        var item = DB.getItem(1);
        assertEquals(item.id(), 1);
        assertEquals(item.description(), "item1");
    }

    @Test
    public void getItemThrowsExceptionOnNonExistingId() throws Exception {
        assertThrows(Exception.class, () -> DB.getItem(10));
    }

    @Test
    public void addItemIncreaseAmountOfItems() throws Exception {

        var newItem = new ListItem(5, 2, 5, "Item5", "20/12/2004");
        DB.addItem(newItem);

        assertEquals(DB.getAll().toArray().length, 5);

    }
}