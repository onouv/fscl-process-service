package org.fscl.process.adapters.driving.persistence;

import java.util.List;
import java.util.Optional;

import org.fscl.core.adapters.driving.persistence.entity.EntityIdJpaDto;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FunctionRepository implements PanacheRepository<FunctionJpaDto> {

	public Optional<FunctionJpaDto> findById(EntityIdJpaDto id) throws PersistenceException {
		final PanacheQuery<FunctionJpaDto> q = find("project = ?1 and code = ?2", id.getProject(), id.getCode());

		return q.firstResultOptional();
	}

	public List<FunctionJpaDto> findAllForProject(String projectId) {
		final PanacheQuery<FunctionJpaDto> q = find("project = ?1", projectId);

		return q.list();
	}

}
