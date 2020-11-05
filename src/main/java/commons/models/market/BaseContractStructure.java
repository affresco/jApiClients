package commons.models.market;

public abstract class BaseContractStructure {

    protected BaseContractStructure(Builder<?> builder){ }

    public BaseContractStructure(BaseContractStructure contractStructure){ }

    public abstract static class Builder<T extends Builder<T>>{

        protected abstract BaseContractStructure build();
        protected abstract T self();
    }

}
