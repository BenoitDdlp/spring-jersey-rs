package fr.benoitddlp.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "todo")
@NamedQueries( { @NamedQuery(name = "Todo.findAll", query = "SELECT p FROM Todo p"),
        @NamedQuery(name = "Todo.find", query = "SELECT t FROM Todo t where t.summary=:summary and t.description=:description")
})
@XmlRootElement
public class Todo {
    private static final long serialVersionUID = -142950147106627845L;

    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    private int id;
    private String summary;
    private String description;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
