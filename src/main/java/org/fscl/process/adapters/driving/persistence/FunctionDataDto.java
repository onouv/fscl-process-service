package org.fscl.process.adapters.driving.persistence;

import org.fscl.core.adapters.driving.persistence.ResourceDataDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@Entity
@Table(name = "functions")
public class FunctionDataDto extends ResourceDataDto {
}
