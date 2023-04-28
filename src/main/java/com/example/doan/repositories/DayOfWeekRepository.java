package com.example.doan.repositories;

import com.example.doan.models.DayOfTheWeek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayOfWeekRepository extends JpaRepository<DayOfTheWeek,String> {
}
