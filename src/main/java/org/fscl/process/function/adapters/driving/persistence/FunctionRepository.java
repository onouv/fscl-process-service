package org.fscl.process.function.adapters.driving.persistence;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.process.function.domain.Function;

@ApplicationScoped
public class FunctionRepository implements PanacheRepository<Function> {

    public Optional<Function> findById(FsclEntityId id) throws PersistenceException {
        final PanacheQuery<Function> q = find("project = ?1 and code = ?2", id.project(), id.code());
        Optional<Function> opt = q.firstResultOptional();
        return opt;
    }

    // TODO: write transformer

    public List<Function> findAllForProject(String projectId) {
        final PanacheQuery<Function> q = find("project = ?1", projectId);
        return q.list();
    }

}
