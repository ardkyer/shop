package com.baeksoo.shop;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@Table(indexes = @Index(columnList = "title", name = "작명"))
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String title;
    public Integer price;


}
