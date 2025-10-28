package org.fscl.process.adapters.driving.persistence;

import org.fscl.core.adapters.driving.persistence.entity.EntityJpaDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="functions")
public class FunctionJpaData extends EntityJpaDto  {
}

