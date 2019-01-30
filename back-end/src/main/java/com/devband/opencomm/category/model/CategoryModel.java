package com.devband.opencomm.category.model;

import com.devband.opencomm.post.model.PostModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryModel {

    @Id
    @GeneratedValue
    private Integer id;

    private String category;

    @OneToMany(mappedBy = "category")
    List<PostModel> postList;

    private Date created;

    private Date updated;

    private Date deleted;
}
