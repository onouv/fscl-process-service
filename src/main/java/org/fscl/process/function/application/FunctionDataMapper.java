package org.fscl.process.function.application;

import org.fscl.process.function.adapters.driving.persistence.FunctionJpaData;
import org.fscl.process.function.domain.Function;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FunctionDataMapper {

	FunctionDataMapper INSTANCE = Mappers.getMapper(FunctionDataMapper.class);
	
	public FunctionJpaData domain2Data(Function domain);
	public Function data2Domain(FunctionJpaData data);
}
