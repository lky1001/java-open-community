package com.devband.opencomm.post.model;

import com.devband.opencomm.category.model.CategoryModel;
import com.devband.opencomm.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class PostModel {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    private Date created;

    private Date updated;
}
