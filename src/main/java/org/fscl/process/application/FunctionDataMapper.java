package org.fscl.process.application;

import org.fscl.process.adapters.driving.persistence.FunctionJpaData;
import org.fscl.process.domain.Function;
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
