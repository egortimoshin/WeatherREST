package Project3.dto;

import Project3.models.Sensor;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeasurementDTO {

    @NotNull(message = "Value field is null")
    @Min(value = -100, message = "The value of temperature is not valid")
    @Max(value = 100, message = "The value of temperature is not valid")
    private Integer value;

    @NotNull(message = "Raining field is null")
    private Boolean raining;

    @NotNull(message = "Sensor field is null")
    private Sensor sensor;

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
