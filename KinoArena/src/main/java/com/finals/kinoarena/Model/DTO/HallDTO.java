package com.finals.kinoarena.Model.DTO;

import com.finals.kinoarena.Model.Entity.Cinema;
import com.finals.kinoarena.Model.Entity.Hall;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HallDTO {

    private int id;
    private int number;
    private int capacity;
    private int cinemaId;
    private Cinema cinema;

    public HallDTO(Hall hall) {
        this.id = hall.getId();
        this.number = hall.getNumber();
        this.capacity = hall.getCapacity();
        this.cinema = hall.getCinema();

    }
}
