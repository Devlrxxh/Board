package dev.acentra.queue;

import lombok.Getter;

@Getter
public class QueueEntry {
    private final boolean ranked;

    public QueueEntry(boolean ranked) {
        this.ranked = ranked;
    }


    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        QueueEntry other = (QueueEntry) obj;
        return (this.ranked == other.ranked);
    }
}
