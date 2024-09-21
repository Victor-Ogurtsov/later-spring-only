package ru.practicum.item;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final HashMap<Long, Item> items = new HashMap<Long, Item>();

    public ItemRepositoryImpl() {
        items.put(1L, new Item(1L, 1L, "https://practicum.yandex.ru"));
        items.put(2L, new Item(2L, 1L, "https://www.baeldung.com"));
        items.put(3L, new Item(2L, 2L, "https://github.com"));
    }

    @Override
    public List<Item> findByUserId(long userId) {
        return items.values().stream().filter(item -> item.getUserId() == userId).toList();
    }

    @Override
    public Item save(Item item) {
        Long newId = 1 + items.keySet().stream()
                .mapToLong(value -> value)
                .max()
                .orElse(1L);
        item.setId(newId);
        items.put(newId, item);
        return item;
    }

    @Override
    public void deleteByUserIdAndItemId(long userId, long itemId) {
        items.remove(itemId);
    }
}
