package ru.practicum.itemNote;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemNoteServiceImpl implements ItemNoteService {
    private final ItemNoteRepository itemNoteRepository;

    @Override
    public ItemNote addItemNote(ItemNote itemNote) {
        return itemNoteRepository.save(itemNote);
    }

    @Override
    public List<ItemNote> getItemNotesListsByUserIdAndUrl(Long userId, String url) {
        System.out.println("userId=" + userId + " url=" + url);
        return itemNoteRepository.findAllByItemUserIdAndItemUrlContaining(userId, url);
    }

    @Override
    public List<ItemNote> getItemNotesListsByUserIdAndItemTag(Long userId, String tag) {
        System.out.println("userId=" + userId + " tag=" + tag);
        return itemNoteRepository.findItemNotesListsByUserIdAndItemTag(userId, tag);
    }

    @Override
    public List<ItemNote> listAllItemsWithNotes(long userId, int from, int size) {
        System.out.println("userId=" + userId + " from=" + from + " size=" + size);
        return itemNoteRepository.listAllItemsWithNotes(userId, PageRequest.of(from > 0 ? from / size : 0, size));
    }
}
