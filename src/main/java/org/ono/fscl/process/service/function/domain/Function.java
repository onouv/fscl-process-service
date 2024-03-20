package org.ono.fscl.process.service.function.domain;


import ono.fscl.core.domain.entity.id.FsclEntityId;
import ono.fscl.core.domain.entity.id.SegmentFormatException;
import ono.fscl.core.domain.function.FunctionBase;
import ono.fscl.core.domain.function.FunctionCode;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.ono.fscl.process.service.function.domain.commands.CreateFunctionCmd;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


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
            try {
                FunctionCode code = FunctionCode.builder().build();
                return new Function(code, "huhu", null, "Feedwater Supply", "");
            } catch (SegmentFormatException e) {
                return null;
            }
        }
    }

    @CommandHandler
    public handle(CreateFunctionCmd cmd) {


    }

    private Function(FunctionCode code, String project, Function parent, String name, String description) {
        super(new FsclEntityId<FunctionCode>(code, project), parent, name, description);
    }

    @Override
    @AggregateIdentifier
    public FsclEntityId<FunctionCode> getIdentifier() {
        return this.identifier;
    }
}
