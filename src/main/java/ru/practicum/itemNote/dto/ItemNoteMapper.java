package ru.practicum.itemNote.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemNoteMapper {
    private Long id;
    //private Long itemId;
    private String text;
    //private String creationDate;
    //private String itemUrl;
}
/*
private Long id; //id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    private String text; // text VARCHAR(2000),

    private Item item; // item_id BIGINT,

    private Instant creationDate = Instant.now();
 */