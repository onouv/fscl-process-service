package org.fscl.process.adapters.driving.persistence;

import java.util.List;
import java.util.Optional;

import org.fscl.core.commons.ResourceIdDataDto;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FunctionRepository implements PanacheRepository<FunctionDataDto> {

	public Optional<FunctionDataDto> findById(ResourceIdDataDto id) throws PersistenceException {
		final PanacheQuery<FunctionDataDto> q = find("project = ?1 and code = ?2", id.getProject(), id.getCode());

		return q.firstResultOptional();
	}

	public List<FunctionDataDto> findAllForProject(String projectId) {
		final PanacheQuery<FunctionDataDto> q = find("project = ?1", projectId);

		return q.list();
	}

}
