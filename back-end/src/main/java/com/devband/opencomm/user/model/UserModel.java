package com.devband.opencomm.user.model;

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

    private int point;

    private int level;

    @Enumerated(EnumType.STRING)
    private EnumUserType type;

    @OneToMany(mappedBy = "user")
    private List<PostModel> postList;

    private Date created;

    private Date updated;
}
