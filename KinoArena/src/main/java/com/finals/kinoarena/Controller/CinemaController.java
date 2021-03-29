package com.finals.kinoarena.Controller;

import com.finals.kinoarena.Exceptions.*;
import com.finals.kinoarena.Model.DTO.CinemaWithoutHallDTO;
import com.finals.kinoarena.Model.Entity.User;
import com.finals.kinoarena.Service.CinemaService;
import com.finals.kinoarena.Model.DTO.CinemaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Component
@RestController
public class CinemaController extends AbstractController {

    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private SessionManager sessionManager;


    @GetMapping(value = "/cinemas")
    public List<CinemaWithoutHallDTO> getAllCinemas() throws NotFoundException {
        return cinemaService.getAllCinemas();
    }

    @GetMapping(value = "/cinema/{cinema_id}")
    public CinemaDTO getCinemaById(@PathVariable(name = "cinema_id") int cinema_Id) {
        return cinemaService.getCinemaById(cinema_Id);
    }

    @GetMapping(value = "/cinemas/city/{city}")
    public List<CinemaDTO> getAllCinemasByCity(@PathVariable String city) throws NotFoundException {
        return cinemaService.getAllCinemasByCity(city);
    }

    @PutMapping(value = "/cinemas")
    public CinemaDTO addCinema(@RequestBody CinemaDTO cinemaDTO, HttpSession ses) throws BadRequestException, NotFoundException, UnauthorizedException {
        User user = sessionManager.getLoggedUser(ses);
        int userId = user.getId();
        if (!validateNewCinema(cinemaDTO.getCity(), cinemaDTO.getName())) {
            throw new BadRequestException("Please fill all requested fields");
        }
        return cinemaService.addCinema(cinemaDTO, userId);
    }

    @DeleteMapping(value = "/cinemas/delete/{cinema_id}")
    public String deleteCinema(@PathVariable(name = "cinema_id") int cinemaId, HttpSession ses) throws BadRequestException, UnauthorizedException {
        User user = sessionManager.getLoggedUser(ses);
        int userId = user.getId();
        cinemaService.removeCinema(cinemaId, userId);
        return "Cinema successfully deleted";
    }

    @PostMapping(value = "/cinemas/{cinema_id}")
    public CinemaDTO editCinema(@PathVariable(name = "cinema_id") int cinemaId, @RequestBody CinemaDTO cinemaDTO, HttpSession ses) throws UnauthorizedException, BadRequestException {
        User user = sessionManager.getLoggedUser(ses);
        return cinemaService.editCinema(cinemaDTO, cinemaId, user.getId());
    }

    private boolean validateNewCinema(String city, String name) throws BadRequestException {
        if (city.isBlank() || name.isBlank()) {
            throw new BadRequestException("Please fill all necessary fields");
        }
        if (city.length() > 20 || city.length() < 3) {
            throw new BadRequestException("City names must be with at least 3 letters or max with 20");
        }
        if (name.length() > 25) {
            throw new BadRequestException("Name can contain maximum 25 letters");
        }
        return true;
    }
}
