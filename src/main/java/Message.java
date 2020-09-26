

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "messages")
public class Message  implements Serializable {

    public Message() {
    }

    public Message(int id, int from, int to, String message) {
        this.id = id;
        this.fromid = from;
        this.toid = to;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", from=" + fromid +
                ", to=" + toid +
                ", message='" + message + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "fromId")
    int fromid;
    @Column(name = "toId")
    int toid;
    @Column(name = "message")
    String message;

    public int getFrom() {
        return fromid;
    }

    public void setFrom(int from) {
        this.fromid = from;
    }

    public int getTo() {
        return toid;
    }

    public void setTo(int to) {
        this.toid = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(int from, int to, String message) {
        this.fromid = from;
        this.toid = to;
        this.message = message;
    }
}
