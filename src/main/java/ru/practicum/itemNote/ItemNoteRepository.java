package ru.practicum.itemNote;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemNoteRepository extends JpaRepository<ItemNote, Long> {
    List<ItemNote> findAllByItemUserIdAndItemUrlContaining(Long userId, String url);

    @Query(
                    "select in " +
                    "from ItemNote as in " +
                    "join in.item as i " +
                            "where i.userId = ?1 and ?2 member of i.tags"
    )
    List<ItemNote> findItemNotesListsByUserIdAndItemTag(Long userId, String tag);

    /*
    @Query("select it " +
            "from Item as it "+
            "join it.user as u " +
            "where u.lastName like concat(?1, '%') ")
List<Item> findItemsByLastNamePrefix(String lastNamePrefix);
     */


    @Query(
            "select in " +
            "from ItemNote as in " +
            "JOIN FETCH in.item as i " +
                    "JOIN FETCH i.tags as t " +
            "where i.userId = ?1"
    )// JOIN FETCH гарантирует немедленную выборку
    List<ItemNote> listAllItemsWithNotes(long userId, Pageable page);

    /*
    public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(" select p " +
            "from Post p " +
            "JOIN FETCH p.author")  // JOIN FETCH гарантирует немедленную выборку
    List<Post> findAllWithAuthors();
     */
}
