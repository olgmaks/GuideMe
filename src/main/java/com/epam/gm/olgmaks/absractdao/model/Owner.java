package com.epam.gm.olgmaks.absractdao.model;

import com.epam.gm.olgmaks.absractdao.annotation.Column;
import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.annotation.OneToMany;
import com.epam.gm.olgmaks.absractdao.annotation.ForeignKey;

/**
 * Created by OLEG on 08.06.2015.
 */
@Entity("owner")
public class Owner {

    @Column("id")
    @ForeignKey("owner_id")
    private Integer id;

    @Column("f_name")
    private String firstName;

    @Column("l_name")
    private String lastName;

    @Column("ages")
    private Integer ages;

    @Column("role_id")
    private Integer roleId;

    @OneToMany(Role.class)
    private Role role;

    public Owner() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAges() {
        return ages;
    }

    public void setAges(Integer ages) {
        this.ages = ages;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ages=" + ages +
                ", roleId=" + roleId +
                ", role=" + role +
                '}';
    }
}
