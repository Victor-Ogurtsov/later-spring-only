package ru.practicum.itemNote;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class ItemNoteController {
    private final ItemNoteService itemNoteService;

    @PostMapping
    ItemNote addItemNote(@RequestBody ItemNote itemNote) {
        return itemNoteService.addItemNote(itemNote);
    }

    @GetMapping(params = "url")
    List<ItemNote> getItemNotesListsByUserIdAndUrl(@RequestHeader("X-Later-User-Id") Long userId, @RequestParam String url) {
        return itemNoteService.getItemNotesListsByUserIdAndUrl(userId, url);
    }

    @GetMapping("/{tag}")
    List<ItemNote> getItemNotesListsByUserIdAndItemTag(@RequestHeader("X-Later-User-Id") Long userId, @PathVariable(name = "tag") String tag) {
        return itemNoteService.getItemNotesListsByUserIdAndItemTag(userId, tag);
    }

    @GetMapping
    public List<ItemNote> listAllNotes(@RequestHeader("X-Later-User-Id") long userId,
                                          @RequestParam(name = "from", defaultValue = "0") int from,
                                          @RequestParam(name = "size", defaultValue = "10") int size) {
        // возвращает набор пользовательских заметок, соответствующий указанным параметрам пагинации
        return itemNoteService.listAllItemsWithNotes(userId, from, size);
    }

}
