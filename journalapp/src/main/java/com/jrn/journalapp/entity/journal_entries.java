package com.jrn.journalapp.entity;

import com.jrn.journalapp.Enum.Sentiment;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "journal_entries") //mapping krne k liye
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@EqualsAndHashCode*/
public class journal_entries {
    @Id //map as primary key
    private ObjectId id;
    @NonNull
    private String title;
    @NonNull
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;

    /*public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
*/

    public Object getContent(Object o) {
        return content;
    }

    public Object getTitle(Object o) {
        return title;
    }
}