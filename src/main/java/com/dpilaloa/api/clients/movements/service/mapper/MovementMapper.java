package com.dpilaloa.api.clients.movements.service.mapper;

import com.dpilaloa.api.clients.movements.domain.models.MovementEntity;
import com.dpilaloa.api.clients.movements.service.models.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovementMapper {

    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    Movement movementEntityToMovement(MovementEntity movementEntity);

    MovementEntity movementToMovementEntity(Movement movement);

}
