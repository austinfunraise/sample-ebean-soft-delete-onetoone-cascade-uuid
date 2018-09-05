@OneToOne Soft Delete Tests
=====================

This shows an example where three entities are joined together in the following relationship

- A `Parent` record exists
- One or more `Child` records can exist, each pointing to the `Parent` (`ManyToOne` with Parent)
- Each `Child` has a `ChildSibling` record (`OneToOne` with Child)
- The `Child` records Id type is `UUID`

If a `Parent` record has a tree with a `Parent`, a `Child`, and a `ChildSibling` record 
and the parent is deleted, an exception is thrown when the delete cascades to the 
`ChildSibling` record

```
Query threw SQLException:ERROR: operator does not exist: uuid = character varying
  Hint: No operator matches the given name and argument type(s). You might need to add explicit type casts.
  Position: 51 Bind values:[Array[1]={977db952-1363-451d-b8af-757b526677b4}] Query was:select t0.id from child_sibling t0 where child_id = any(?)
	at org.example.domain.SoftDeleteCascadeTest.testDeleteParent(SoftDeleteCascadeTest.java:30)
Caused by: org.postgresql.util.PSQLException: 
ERROR: operator does not exist: uuid = character varying
  Hint: No operator matches the given name and argument type(s). You might need to add explicit type casts.
  Position: 51
	at org.example.domain.SoftDeleteCascadeTest.testDeleteParent(SoftDeleteCascadeTest.java:30)
```

