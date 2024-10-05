package ru.practicum.item.ItemDto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.practicum.item.Item;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemMapper {
    ItemDto toItemDto(Item item);
    Item fromItemDto(ItemDto itemDto);
    List<ItemDto> toItemDtoList(List<Item> items);

}
