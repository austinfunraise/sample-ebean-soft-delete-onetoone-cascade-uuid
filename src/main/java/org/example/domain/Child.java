package org.example.domain;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.SoftDelete;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Child extends Model {

    private static final long serialVersionUID = 738194912181571389L;

    @Id
    private UUID id;

    @SoftDelete
    private boolean deleted;

    @OneToOne(mappedBy = "child", cascade = CascadeType.REMOVE)
    private ChildSibling childSibling;

    @ManyToOne
    private Parent parent;

    public static Finder<UUID, Child> find = new Finder<>(Child.class);

    public Child() {}

    public Child(Parent parent) {
        this.parent = parent;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public ChildSibling getChildSibling() {
        return childSibling;
    }

    public Child setChildSibling(ChildSibling childSibling) {
        this.childSibling = childSibling;
        return this;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
