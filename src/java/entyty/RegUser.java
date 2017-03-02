/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entyty;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

/**
 *
 * @author jvm
 */
@Entity

public class RegUser extends Person{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 50)
    @Column(length = 50,unique = true,nullable = false)
    private String login;
    @Size(min = 5)
    @Column(length = 100, nullable = false)
    private String password;
    private String salts;
    
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "group_fk")
    private List<Group> groups;
    

    public RegUser() {
    }
    

    public RegUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public RegUser(String login, String password, String salts, List<Group> groups, String name, String surname, String phone, String email) {
        super(name, surname, phone, email);
        this.login = login;
        this.password = password;
        this.salts = salts;
        this.groups = groups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalts() {
        return salts;
    }

    public void setSalts(String salts) {
        this.salts = salts;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.login);
        hash = 31 * hash + Objects.hashCode(this.password);
        hash = 31 * hash + Objects.hashCode(this.salts);
        hash = 31 * hash + Objects.hashCode(this.groups);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegUser other = (RegUser) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.salts, other.salts)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.groups, other.groups)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder strGroups = new StringBuilder();
        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            strGroups.append(group.toString());
            
        }
        return "RegUser{" + "id=" + id + ", login=" + login + ", password=" + password + ", salts=" + salts + ", groups=" + strGroups.toString() + '}';
    }




}
