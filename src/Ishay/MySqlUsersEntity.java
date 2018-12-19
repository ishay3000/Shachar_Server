package Ishay;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "chat_schema")
public class MySqlUsersEntity {

    private int userid;
    private String forename;
    private String surname;
    private String password;
    private String email;

    public MySqlUsersEntity() {
    }

    public MySqlUsersEntity(int userid, String forename, String surname, String password, String email) {
        this.userid = userid;
        this.forename = forename;
        this.surname = surname;
        this.password = password;
        this.email = email;
    }

    @Id
    @Column(name = "userid", nullable = false)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "forename", nullable = true, length = 45)
    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    @Basic
    @Column(name = "surname", nullable = true, length = 45)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySqlUsersEntity that = (MySqlUsersEntity) o;
        return userid == that.userid &&
                Objects.equals(forename, that.forename) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, forename, surname, password, email);
    }

    @Override
    public String toString() {
        return "MySqlUsersEntity{" +
                "userid=" + userid +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
