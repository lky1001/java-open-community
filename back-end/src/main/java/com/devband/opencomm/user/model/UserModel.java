package com.devband.opencomm.user.model;

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

    private Date created;

    private Date updated;
}
