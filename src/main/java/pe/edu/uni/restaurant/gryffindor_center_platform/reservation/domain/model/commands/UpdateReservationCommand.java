package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands;

import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.CorreoUsuario;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.NombreCompletoUsuario;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.Status;

import java.sql.Time;
import java.util.Date;

public record UpdateReservationCommand(Long id, Long reservedId,
                                       Date fechaReserva,
                                       Time horaReserva,
                                       Integer customerQuantity,
                                       Status status,
                                       NombreCompletoUsuario nombreCompletoUsuario,
                                       CorreoUsuario correoUsuario) {
}
