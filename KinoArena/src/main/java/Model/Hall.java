package Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Hall {

    @Id
    private long id;
    private int number;
    private int capacity;
    private long cinemaId;
}