package pl.mb.shared;

public class EntityId {

    private Long id;

    public EntityId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityId entityId = (EntityId) o;

        return id.equals(entityId.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
