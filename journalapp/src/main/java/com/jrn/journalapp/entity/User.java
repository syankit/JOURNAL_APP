package com.jrn.journalapp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;


@Document( collection = "User")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id //map as primary key
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    private String email;
    private boolean senti;
    @NonNull
    private String password;
    @DBRef
    private List<journal_entries> journal_entriesList= new ArrayList<>();
    private List<String> roles;
}