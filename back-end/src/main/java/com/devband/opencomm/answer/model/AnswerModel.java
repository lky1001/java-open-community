package com.devband.opencomm.answer.model;

import com.devband.opencomm.post.model.PostModel;
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
@Table(name = "answers")
public class AnswerModel {

    @Id
    @GeneratedValue
    private Long id;

    private String answer;

    private int likes;

    private boolean adopt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostModel post;

    @OneToMany(mappedBy = "answer", fetch = FetchType.LAZY)
    private List<AnswerLikeModel> answerLinkList;

    private Date created;

    private Date updated;
}
