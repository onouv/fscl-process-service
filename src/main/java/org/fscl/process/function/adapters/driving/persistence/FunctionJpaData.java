package org.fscl.process.function.adapters.driving.persistence;

import org.fscl.core.adapters.driving.persistence.entity.EntityJpaData;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public abstract class FunctionJpaData extends EntityJpaData  {
}

