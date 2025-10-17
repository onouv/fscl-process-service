package org.fscl.process.function.application;

import org.fscl.process.function.adapters.persistence.driving.FunctionJpaData;
import org.fscl.process.function.domain.Function;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FunctionDataMapper {

	FunctionDataMapper INSTANCE = Mappers.getMapper(FunctionDataMapper.class);
	
	FunctionJpaData domain2Data(Function domain);
	
	@BeanMapping( resultType = Function.class)
	Function data2Domain(FunctionJpaData data);
}
