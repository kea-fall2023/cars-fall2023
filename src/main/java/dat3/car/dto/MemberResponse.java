package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //A must for @Builder
@Builder  //I will demo it's purpose in the class
//It's really IMPORTANT that you understand the purpose of this annotation
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {
    String username; //Remember this is the primary key
    //Observe password is obviously not included
    String email;
    String firstName;
    String lastName;
    String street;
    String city;
    String zip;
    //@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    List<ReservationResponse> reservations = new ArrayList<>();

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;
    Integer ranking;
    Boolean approved;

    //Convert Member Entity to Member DTO
    public MemberResponse(Member m, boolean includeAll) {
        this.username = m.getUsername();
        this.email = m.getEmail();
        this.street = m.getStreet();
        this.firstName =m.getFirstName();
        this.lastName = m.getLastName();
        this.city = m.getCity();
        this.zip = m.getZip();
        if(includeAll){
            this.created = m.getCreated();
            this.edited = m.getEdited();
            this.approved = m.isApproved();
            this.ranking = m.getRanking();
        }
    }

    //Convert Member Entity to Member DTO
    public MemberResponse(Member m, boolean includeAll, boolean includeReservations) {
        this.username = m.getUsername();
        this.email = m.getEmail();
        this.street = m.getStreet();
        this.firstName =m.getFirstName();
        this.lastName = m.getLastName();
        this.city = m.getCity();
        this.zip = m.getZip();

        if(includeAll){
            this.created = m.getCreated();
            this.edited = m.getEdited();
            this.ranking = m.getRanking();
        }

        if(includeReservations && (m.getReservations().size()>0)){
            reservations = m.getReservations().stream().map(r->ReservationResponse.builder()
                    .id(r.getId())
                    .carId(r.getCarToRent().getId())
                    .carBrand(r.getCarToRent().getBrand())
                    .rentalDate(r.getRentalDate())
                    .build()
            ).collect(Collectors.toList());
        }
    }
}

