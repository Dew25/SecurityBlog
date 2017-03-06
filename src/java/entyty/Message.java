/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entyty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jvm
 */
@Entity
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private RegUser author;
    private String titleMsg;
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date addMessageTime;
    @ManyToOne(cascade = CascadeType.ALL)
    private Article article;

    public Message() {
    }

    public Message(RegUser author, String titleMsg, String message, Date addMessageTime) {
        this.author = author;
        this.titleMsg = titleMsg;
        this.message = message;
        this.addMessageTime = addMessageTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public RegUser getAuthor() {
        return author;
    }

    public String getTitleMsg() {
        return titleMsg;
    }

    public String getMessage() {
        return message;
    }

    public Date getAddMessageTime() {
        return addMessageTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.author);
        hash = 97 * hash + Objects.hashCode(this.titleMsg);
        hash = 97 * hash + Objects.hashCode(this.message);
        hash = 97 * hash + Objects.hashCode(this.addMessageTime);
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
        final Message other = (Message) obj;
        if (!Objects.equals(this.titleMsg, other.titleMsg)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.addMessageTime, other.addMessageTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", author=" + author.getLogin() + ", titleMsg=" + titleMsg + ", message=" + message + ", addMessageTime=" + addMessageTime.toString() + '}';
    }
    
      
    
}
