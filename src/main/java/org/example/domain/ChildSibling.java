package org.example.domain;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.SoftDelete;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ChildSibling extends Model {

    private static final long serialVersionUID = 738194912181571389L;

    @Id
    private Long id;

    @SoftDelete
    private boolean deleted;

    @OneToOne
    private Child child;

    public static Finder<Long, ChildSibling> find = new Finder<>(ChildSibling.class);

    public ChildSibling() {}

    public ChildSibling(Child child) {
        this.child = child;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Child getChild() {
        return child;
    }

    public ChildSibling setChild(Child child) {
        this.child = child;
        return this;
    }
}
