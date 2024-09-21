package ru.practicum.item;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ItemService {
    List<Item> getItems( long userId);

    Item addNewItem(Long userId, Item item);

    void deleteItem(long userId, long itemId);
}
