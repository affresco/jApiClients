package commons.models.positions;

public abstract class Position {

    protected Position(Builder<?> builder){ }

    public abstract static class Builder<T extends Builder<T>>{

        protected abstract Position build();
        protected abstract T self();
    }

}
