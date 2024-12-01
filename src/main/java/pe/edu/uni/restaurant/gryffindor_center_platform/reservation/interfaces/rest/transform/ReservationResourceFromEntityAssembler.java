package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.transform;


import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Reservation;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.resources.ReservationResource;

public class ReservationResourceFromEntityAssembler {
    public static ReservationResource toResourceFromEntity(Reservation entity){
        return new ReservationResource(entity.getId(), entity.getReservedId(),
                entity.getFechaReserva(),
                entity.getHoraReserva(),
                entity.getCustomerQuantity(),
                entity.getStatus(),
                entity.getNombreCompletoUsuario(),
                entity.getCorreoUsuario());
    }
}
