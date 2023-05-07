package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    private static final Injector injector = Injector.getInstance("mate");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        CinemaHallService cinemaHallService = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        MovieSessionService movieSessionService = (MovieSessionService) injector.getInstance(MovieSessionService.class);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(30);
        cinemaHall.setDescription("Gold");
        cinemaHallService.add(cinemaHall);

        Movie movie = new Movie();
        movie.setTitle("Spider man");
        movie.setDescription("rating 10");
        movieService.add(movie);

        LocalDateTime localDateTime = LocalDateTime.now();

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setLocalDateTime(localDateTime);
        movieSessionService.add(movieSession);

        LocalDateTime localDateTime2 = LocalDateTime.of(2023, Month.MAY, 6, 18, 33);
        MovieSession movieSession2 = new MovieSession();
        movieSession2.setMovie(movie);
        movieSession2.setCinemaHall(cinemaHall);
        movieSession2.setLocalDateTime(localDateTime2);
        movieSessionService.add(movieSession2);

        LocalDate date = LocalDate.of(2023, Month.MAY, 7);

        movieSessionService.findAvailableSessions(2L, date).forEach(System.out::println);
    }
}
