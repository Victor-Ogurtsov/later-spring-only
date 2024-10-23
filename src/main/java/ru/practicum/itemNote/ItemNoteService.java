package ru.practicum.itemNote;

import java.util.List;

public interface ItemNoteService {
    ItemNote addItemNote(ItemNote itemNote);

    List<ItemNote> getItemNotesListsByUserIdAndUrl(Long userId, String url);
    List<ItemNote> getItemNotesListsByUserIdAndItemTag(Long userId, String tag);

    List<ItemNote> listAllItemsWithNotes(long userId, int from, int size);
}
