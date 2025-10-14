package org.fscl.process.function.adapters.driving.persistence;

import org.fscl.core.adapters.driving.persistence.entity.EntityJpaData;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="function")
@NoArgsConstructor
@Getter
@Setter
public class FunctionJpaData extends EntityJpaData  {
}

