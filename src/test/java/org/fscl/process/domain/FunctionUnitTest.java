package org.fscl.process.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fscl.core.commons.ResourceId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class FunctionUnitTest {

	@Nested
	@DisplayName("GIVEN code and project name")
	class GivenIdentifier {

		final String project = "Babagurka";
		final String code = "Bruhaha";
		final ResourceId id = new ResourceId(project, code);

		@Nested
		@DisplayName("WHEN created")
		class WhenCreated {

			private Function func = null;

			@BeforeEach
			void setup() {
				func = Function.builder().resourceId(id).build();
			}

			@Test
			@DisplayName("THEN must have correct identifier")
			void mustHaveIdentifier() {
				assertEquals(func.getResourceId(), id);
			}
		}
	}
}
