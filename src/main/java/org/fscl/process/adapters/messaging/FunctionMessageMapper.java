package org.fscl.process.adapters.messaging;

import org.fscl.core.adapters.messaging.FunctionCreatedMessage;
import org.fscl.core.adapters.messaging.FunctionDeletedMessage;
import org.fscl.core.adapters.messaging.FunctionMessage;
import org.fscl.core.commons.ResourceEventType;
import org.fscl.core.ports.driving.messaging.DtoMappingFailedException;
import org.fscl.core.ports.driving.messaging.FunctionCreatedEventDto;
import org.fscl.core.ports.driving.messaging.FunctionDeletedEventDto;
import org.fscl.core.ports.driving.messaging.FunctionEventDto;
import org.fscl.core.ports.driving.messaging.MessagingException;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class FunctionMessageMapper {

	FunctionMessage outwards(FunctionEventDto dto) throws MessagingException {
		ResourceEventType eventType = dto.getEventType();

		switch (eventType) {
			case ResourceEventType.Created: {
				return FunctionCreatedMessage.of((FunctionCreatedEventDto) dto);
			}
			case ResourceEventType.Deleted: {
				return FunctionDeletedMessage.of((FunctionDeletedEventDto) dto);
			}
			default: {
				throw new DtoMappingFailedException("Cannot outwards-map unhandled event type");
			}
		}
	}
}
