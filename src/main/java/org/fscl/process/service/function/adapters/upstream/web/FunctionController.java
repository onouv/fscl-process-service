package org.fscl.process.service.function.adapters.upstream.web;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.fscl.core.domain.entity.id.FsclEntityId;
import ono.fscl.core.domain.function.FunctionCode;
import org.fscl.core.ports.upstream.web.id.FsclEntityIdDto;
import org.fscl.core.ports.upstream.web.lifecycle.CreateFunctionCmdDto;
import org.fscl.process.service.function.domain.commands.CreateFunctionCmd;

import org.fscl.process.service.function.ports.upstream.web.FunctionLifeCycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/fscl/process/v0.1/functions")
public class FunctionController {


    @Autowired
    private FunctionLifeCycleService functionService;

    @PostMapping(
        value="/create",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    @RequestBody()
    public ResponseEntity<FsclEntityIdDto> createFunctions(
         @RequestBody(required = true) CreateFunctionCmdDto cmd) {
        cmd.getFunction().forEach(dto -> {

            FunctionCode code = FunctionCode.builder().w
            FsclEntityId<FunctionCode> id = new FsclEntityId<FunctionCode>(dto.getCode(), dto.getProject());
            CreateFunctionCmd domainCommand = new CreateFunctionCmd(id, dto.getName(), dto.getDescription());
            commandGateway.send(domainCommand)
        });
    }
}