package com.finals.kinoarena.Model.Repository;

import com.finals.kinoarena.Model.Entity.Cinema;
import com.finals.kinoarena.Model.Entity.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

    List<Cinema> findAllByCity(String city);



}
