package atu.ie.oopsem2lab5.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Reservation {

    private long reservationID;

    @NotEmpty(message = "You need an equipmentTag")
    private String equipmentTag;

    @NotEmpty(message = "You need an email")
    @Email(message = "You need a valid email")
    private String studentEmail;

    @NotNull(message = "You need a date")
    private LocalDate reservationDate;

    @Min(value=0,message = "Value too low for start hour")
    @Max(value=23,message = "Value too high for start hour")
    private int startHour;

    @Min(value=1,message = "Value too low for duration")
    @Max(value=24,message = "Value too high for duration")
    private int durationHours;
}
