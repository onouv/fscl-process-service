package org.ono.fscl.process.service.function.adapters.upstream.web;

import ono.fscl.core.domain.entity.id.FsclEntityId;
import ono.fscl.core.domain.function.FunctionCode;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.ono.fscl.process.service.function.domain.commands.CreateFunctionCmd;

import org.springframework.stereotype.Component;


@Component
public class FunctionControllerDelegate implements OasFunctionApiDelegate {

    private final CommandGateway commandGateway;

    public FunctionControllerDelegate(CommandGateway gateway) {
        this.commandGateway = gateway;
    }

    @Override
    public ResponseEntity<OasFsclEntityIdDto> createFunctions(OasCreateFunctionsCmdDto cmd) {
        cmd.getFunctions().forEach(dto -> {
            FunctionCode code = FunctionCode.builder()
                    .withPostfix(dto.getId().getPostfix())
                    .withPrefix(dto.getId().getPrefix())
                    .withSeparator(dto.getId().getSeparator())
                    .withCode(dto.getId().getCode()).build();
            FsclEntityId<FunctionCode> id = new FsclEntityId<FunctionCode>(new Fundto.getId());
            CreateFunctionCmd domainCommand = new CreateFunctionCmd(id, dto.getName(), dto.getDescription());
            commandGateway.send(domainCommand)
        });
    }
}