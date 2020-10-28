package commons.models.positions;

public abstract class BasePosition {

    protected BasePosition(Builder<?> builder){ }

    public abstract static class Builder<T extends Builder<T>>{

        protected abstract BasePosition build();
        protected abstract T self();
    }

}
