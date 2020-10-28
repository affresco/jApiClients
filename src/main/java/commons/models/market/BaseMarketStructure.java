package commons.models.market;

public abstract class BaseMarketStructure {

    protected BaseMarketStructure(Builder<?> builder){ }

    public abstract static class Builder<T extends Builder<T>>{

        protected abstract BaseMarketStructure build();
        protected abstract T self();
    }

}
