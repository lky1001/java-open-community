package com.devband.opencomm.user.model;

import com.devband.opencomm.answer.model.AnswerLikeModel;
import com.devband.opencomm.answer.model.AnswerModel;
import com.devband.opencomm.post.model.PostModel;
import com.devband.opencomm.post.model.PostVoteModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "profile_image")
    private String profileImage;

    private int point;

    private int level;

    @Enumerated(EnumType.STRING)
    private EnumUserType type;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PostModel> postList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PostVoteModel> postVoteList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AnswerLikeModel> answerLikeList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AnswerModel> answerList;

    private Date created;

    private Date updated;
}
