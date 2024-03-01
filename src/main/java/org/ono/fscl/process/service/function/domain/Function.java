package org.ono.fscl.process.service.function.domain;


import ono.fscl.core.domain.entity.id.FsclEntityId;
import ono.fscl.core.domain.function.FunctionBase;
import ono.fscl.core.domain.function.FunctionCode;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;


@Aggregate
public class Function extends FunctionBase {

    // INFO: @SuperBuilder does not work here because of the generic type parameters,
    // so we need to roll our own dice...
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends FunctionBase.Builder<Function> {

        private String project = "test";

        public Builder withProject(String project) {
            this.project = project;
            return this;
        }
        @Override
        public Function build() {
            FunctionCode code = FunctionCode.builder().withbuild();
            return new Function(this.identifier, this.project, this.parent, this.name, this.description);
        }
    }

    private Function(FsclEntityId<FunctionCode> id, Function parent, String name, String description) {
        super(new FsclEntityId<FunctionCode>(id, project), parent, name, description);
    }

    @Override
    @AggregateIdentifier
    public FsclEntityId<FunctionCode> getIdentifier() {
        return this.identifier;
    }
}
