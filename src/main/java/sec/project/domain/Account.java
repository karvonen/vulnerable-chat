package sec.project.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String username;
    private String password;
    
//    @OneToMany(mappedBy = "account")
//    private List<Message> messages;

    public Account() {
        super();
    }

    public Account(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<Message> getMessages() {
//        return messages;
//    }
//
//    public void addMessage(Message msg) {
//        messages.add(msg);
//    }
//
//    public void setMessages(ArrayList<Message> messages) {
//        this.messages = messages;
//    }
    
    
}
