package org.fscl.process.function.adapters.persistence.driving;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.fscl.core.domain.entity.id.FsclEntityId;
import org.fscl.process.function.application.FunctionDataMapper;
import org.fscl.process.function.domain.Function;

@ApplicationScoped
public class FunctionRepository implements PanacheRepository<FunctionJpaData> {

    public Optional<Function> findById(FsclEntityId id) throws PersistenceException {
        final PanacheQuery<FunctionJpaData> q = find("project = ?1 and code = ?2", id.project(), id.code());
        Optional<FunctionJpaData> opt = q.firstResultOptional();
        
        return opt.map(fd -> FunctionDataMapper.INSTANCE.data2Domain(fd));
    }

    public List<Function> findAllForProject(String projectId) {
        final PanacheQuery<FunctionJpaData> q = find("project = ?1", projectId);
        List<FunctionJpaData> dtos = q.list();
        
        List<Function> functions = dtos
        	.stream()
        	.map(dto -> FunctionDataMapper.INSTANCE.data2Domain(dto))
        	.collect(Collectors.toList());
        
        return functions;
    }
    
    public void persist(Function f) {
    	FunctionJpaData dto = FunctionDataMapper.INSTANCE.domain2Data(f);
    	
    	persist(dto);
    }

}
