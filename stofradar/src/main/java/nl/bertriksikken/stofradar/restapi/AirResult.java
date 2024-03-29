package nl.bertriksikken.stofradar.restapi;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
final class AirResult {

    @JsonProperty("pm2.5")
    private BigDecimal pm2_5;

    private AirResult() {
        // jackson constructor
    }

    AirResult(double pm2_5) {
        this();
        if (Double.isFinite(pm2_5)) {
            this.pm2_5 = BigDecimal.valueOf(pm2_5).setScale(2, RoundingMode.HALF_UP);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(pm2_5);
    }

}
