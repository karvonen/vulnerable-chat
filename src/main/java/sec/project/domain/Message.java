
package sec.project.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Message extends AbstractPersistable<Long>{
    
    private String content;
    
    @ManyToOne
    private Account account;

    public Message() {
        super();
    }

    
    
    public Message(String content, Account account) {
        this.content = content;
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public Account getAccount() {
        return account;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    
}
