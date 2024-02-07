package org.ono.fscl.process.service.function.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import ono.fscl.shadow.domain.function.Function;
import ono.fscl.shadow.domain.parameters.Parameter;



@RequiredArgsConstructor
public class ProcessFunction extends Function{
    private List<Parameter<?>> parameters = new ArrayList<Parameter<?>>();
}
