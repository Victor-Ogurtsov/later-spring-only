package ru.practicum.itemNote;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.practicum.item.Item;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "item_notes")
@Getter @Setter @ToString
@NoArgsConstructor
public class ItemNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    @Column(name = "text")
    private String text; // text VARCHAR(2000),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; // item_id BIGINT,
    @Column(name = "creation_date")
    private Instant creationDate = Instant.now(); // creation_date timestamp

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemNote itemNote)) return false;
        return Objects.equals(getId(), itemNote.getId()) && Objects.equals(getText(), itemNote.getText()) && Objects.equals(getItem(), itemNote.getItem()) && Objects.equals(getCreationDate(), itemNote.getCreationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getItem(), getCreationDate());
    }
}
