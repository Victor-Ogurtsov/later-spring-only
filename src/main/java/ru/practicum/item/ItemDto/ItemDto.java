package ru.practicum.item.ItemDto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ItemDto {

    private Long id;

    private Long userId;

    private String url;

    private Set<String> tags = new HashSet<>();
}
