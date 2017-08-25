package com.example.day2Test.Domains;

import javax.persistence.*;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="login_id")
    private Long loginId;

    @Column(name="cookie")
    private String cookie;

    @ManyToOne
    @JoinColumn(name="userId",insertable=true,updatable=true,nullable = false)
    private Users users;

    public Login() {
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
