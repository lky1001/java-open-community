package com.devband.opencomm.user.model;

import com.devband.opencomm.answer.model.AnswerModel;
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
    private List<AnswerModel> answerList;

    private Date created;

    private Date updated;
}
