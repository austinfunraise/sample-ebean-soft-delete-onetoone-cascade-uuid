package org.example.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.util.UUID;
import org.junit.Test;

public class SoftDeleteCascadeTest {

    @Test
    public void testDeleteParent() {
        // Insert + link records
        Parent parent = new Parent();
        parent.save();

        Child child = new Child(parent);
        child.save();

        ChildSibling childSibling = new ChildSibling(child);
        childSibling.save();

        Long parentId = parent.getId();
        UUID childId = child.getId();
        Long childSiblingId = childSibling.getId();

        assertBefore(parent, child, childSibling);
        parent.delete();
        assertAfter(parentId, childId, childSiblingId);
    }

    private void assertBefore(Parent parent, Child child, ChildSibling childSibling) {
        parent.refresh();

        assertThat("Parent should have one child loaded",
            parent.getChildren().size(),
            is(1)
        );

        assertThat("Parent should have correct child loaded",
            parent.getChildren().get(0).getId(),
            is(child.getId())
        );

        assertThat( "Child that was loaded should have its sibling available",
            parent.getChildren().get(0).getChildSibling(),
            not(nullValue())
        );

        assertThat(
            "Child that was loaded should have loaded correct sibling",
            parent.getChildren().get(0).getChildSibling().getId(),
            is(childSibling.getId())
        );
    }

    private void assertAfter(Long parentId, UUID childId, Long childSiblingId) {
        assertThat(Parent.find.byId(parentId), is(nullValue()));
        assertThat(Child.find.byId(childId), is(nullValue()));
        assertThat(ChildSibling.find.byId(childSiblingId), is(nullValue()));
    }
}
