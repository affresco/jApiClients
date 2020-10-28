package commons.models.fees;

public abstract class BaseFeeStructure {

    protected BaseFeeStructure(Builder<?> builder){ }

    public abstract static class Builder<T extends Builder<T>>{

        protected abstract BaseFeeStructure build();
        protected abstract T self();
    }

}
