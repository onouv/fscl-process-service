package org.fscl.process.adapters.driving.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fscl.core.commons.entity.FsclEntityId;
import org.fscl.process.application.FunctionDataMapper;
import org.fscl.process.domain.Function;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FunctionRepository implements PanacheRepository<FunctionJpaDto> {

	public Optional<Function> findById(FsclEntityId id) throws PersistenceException {
		final PanacheQuery<FunctionJpaDto> q = find("project = ?1 and code = ?2", id.project(), id.code());
		Optional<FunctionJpaDto> opt = q.firstResultOptional();

		return opt.map(fd -> FunctionDataMapper.INSTANCE.inwards(fd));
	}

	public List<Function> findAllForProject(String projectId) {
		final PanacheQuery<FunctionJpaDto> q = find("project = ?1", projectId);
		List<FunctionJpaDto> dtos = q.list();

		List<Function> functions = dtos.stream()
			.map(dto -> FunctionDataMapper.INSTANCE.inwards(dto))
			.collect(Collectors.toList());

		return functions;
	}

	public void persist(Function f) {
		FunctionJpaDto dto = FunctionDataMapper.INSTANCE.outwards(f);

		persist(dto);
	}

}
