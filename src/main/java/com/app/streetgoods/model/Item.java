package com.app.streetgoods.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Item {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    private String name;
    private String description;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "item_keywords")
    private String[] keywords;
    @Column(name = "image")
    private byte[] image;
    @Column(name = "pickedup")
    private Boolean pickedUp;
    @Embedded
    private Coordinates coordinates;

    public Item(){}
}