package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.item.ItemDto.ItemDto;
import ru.practicum.item.ItemDto.ItemMapper;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping
    public List<ItemDto> get(@RequestHeader("X-Later-User-Id") Long userId) {

        return itemMapper.toItemDtoList(itemService.getItems(userId));
    }

    @PostMapping
    public ItemDto add(@RequestHeader("X-Later-User-Id") Long userId,
                    @RequestBody Item item) {

        return itemMapper.toItemDto(itemService.addNewItem(userId, item));
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@RequestHeader("X-Later-User-Id") long userId,
                           @PathVariable(name="itemId") long itemId) {
        System.out.println("Удаляему айтем");
        itemService.deleteItem(userId, itemId);
        System.out.println("Удален айтем");
    }
}
