package com.devband.opencomm.post.model;

import com.devband.opencomm.answer.model.AnswerModel;
import com.devband.opencomm.category.model.CategoryModel;
import com.devband.opencomm.user.model.UserModel;
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
@Table(name = "posts")
public class PostModel {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    private int votes;

    private int answers;

    private int views;

    @Column(name = "category_id", insertable = false, updatable = false)
    private int categoryId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<AnswerModel> answerList;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostVoteModel> postVoteList;

    private Date created;

    private Date updated;
}
