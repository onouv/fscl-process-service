package org.fscl.process.adapters.driving.persistence;

import java.util.List;

import org.fscl.core.commons.ResourceIdDataDto;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FunctionRepository implements PanacheRepositoryBase<FunctionDataDto, ResourceIdDataDto> {

	/*
	 * public Optional<FunctionDataDto> findById(ResourceIdDataDto id) throws
	 * PersistenceException { final PanacheQuery<FunctionDataDto> q =
	 * find("FROM FunctionDataDto WHERE project=?1 AND code=?2", id.getProject(),
	 * id.getCode());
	 * 
	 * return q.firstResultOptional(); }
	 */

	public List<FunctionDataDto> findByIdProject(String projectId) {
		return list("FROM FunctionDataDto WHERE project=?", projectId);
	}
	/*
	 * Optional<FunctionDataDto> findById(ResourceIdDataDto outwards);
	 */

}
