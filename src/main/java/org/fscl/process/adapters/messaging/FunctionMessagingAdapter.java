package org.fscl.process.adapters.messaging;

import org.fscl.core.adapters.messaging.FunctionMessage;
import org.fscl.core.ports.driving.messaging.FunctionEventDto;
import org.fscl.core.ports.driving.messaging.FunctionMessagingPort;
import org.fscl.core.ports.driving.messaging.MessagingException;
import org.fscl.core.ports.driving.messaging.PublishingFailedException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.ObserverException;
import jakarta.inject.Inject;

@ApplicationScoped
public class FunctionMessagingAdapter implements FunctionMessagingPort {

	@Inject
	private FunctionMessageMapper mapper;

	@Inject
	private Event<FunctionMessage> event;

	@Override
	public void publish(FunctionEventDto dto) throws MessagingException {
		FunctionMessage message = mapper.outwards(dto);
		try {
			event.fire(message);
		} catch (ObserverException ox) {
			throw new PublishingFailedException(ox.getCause().getMessage());
		} catch (IllegalArgumentException iax) {
			throw new PublishingFailedException(iax.getMessage());
		}
	}
}
