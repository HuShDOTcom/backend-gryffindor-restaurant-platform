package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Reservation;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.DeleteReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.UpdateReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services.ReservationCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.infrastructure.persistence.jpa.repositories.ReservationRepository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationRepository reservationRepository;

    public ReservationCommandServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Long handle(CreateReservationCommand command) {
        var reservation = new Reservation(command);
        try {
            this.reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving reservation: " + e.getMessage());
        }
        return reservation.getId();
    }

    @Override
    public Optional<Reservation> handle(UpdateReservationCommand command) {
        var id = command.id();

        // If the reservation does not exist, throw an exception
        if (!this.reservationRepository.existsById(id)) {
            throw new IllegalArgumentException("Reservation with id " + id + " does not exist");
        }

        var reservationToUpdate = this.reservationRepository.findById(id).get();
        reservationToUpdate.updateInformation(command.reservedId(),
                command.fechaReserva(), command.horaReserva(), command.customerQuantity(), command.status(),
                command.nombreCompletoUsuario(),command.correoUsuario());

        try {
            var updatedReservation = this.reservationRepository.save(reservationToUpdate);
            return Optional.of(updatedReservation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating reservation: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteReservationCommand command) {
        // If the reservation does not exist, throw an exception
        if (!this.reservationRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Reservation with id " + command.id() + " does not exist");
        }

        // Try to delete the reservation, if an error occurs, throw an exception
        try {
            this.reservationRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting reservation: " + e.getMessage());
        }
    }
}
