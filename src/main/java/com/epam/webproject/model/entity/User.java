package com.epam.webproject.model.entity;

import java.util.Objects;

public class User extends Entity {
    private long id;
    private String login;
    private String email;
    private int countOfSolve;
    private Role roleType;
    private RatesType ratesType;
    private Status status;



    public User(long id, String login, String email, int countOfSolve, Role roleType, RatesType ratesType,Status status) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.countOfSolve = countOfSolve;
        this.roleType = roleType;
        this.ratesType = ratesType;
        this.status=status;
    }

    public User( String login, String email, Role roleType, RatesType ratesType,Status status) {
        this.login = login;
        this.email = email;
        this.roleType = roleType;
        this.ratesType = ratesType;
        this.status=status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCountOfSolve() {
        return countOfSolve;
    }

    public void setCountOfSolve(int countOfSolve) {
        this.countOfSolve = countOfSolve;
    }

    public Role getRoleType() {
        return roleType;
    }

    public void setRoleType(Role roleType) {
        this.roleType = roleType;
    }

    public RatesType getRatesType() {
        return ratesType;
    }

    public void setRatesType(RatesType ratesType) {
        this.ratesType = ratesType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && countOfSolve == user.countOfSolve && login.equals( user.login) &&email.equals( user.email)
                && roleType == user.roleType && ratesType == user.ratesType && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, countOfSolve, roleType, ratesType, status);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", countOfSolve=").append(countOfSolve);
        sb.append(", roleType=").append(roleType);
        sb.append(", ratesType=").append(ratesType);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}



