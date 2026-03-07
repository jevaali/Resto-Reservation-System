package com.example.smartrestaurantreservationsystem.repositories;

import com.example.smartrestaurantreservationsystem.model.GridCell;
import com.example.smartrestaurantreservationsystem.model.enums.CellType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GridCellRepository extends JpaRepository<GridCell, Long> {

    List<GridCell> findByHallId(Long hallId);

    List<GridCell> findByTableId(Long tableId);

    List<GridCell> findByHallIdAndType(Long hallId, CellType type);
}
