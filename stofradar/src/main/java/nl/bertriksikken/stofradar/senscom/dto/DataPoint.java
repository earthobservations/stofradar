package nl.bertriksikken.stofradar.senscom.dto;

import java.util.ArrayList;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object describing a particular sensor and its current measurement.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class DataPoint {

    @JsonProperty("sensordatavalues")
    private SensorDataValues sensorDataValues;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("sensor")
    private Sensor sensor;
    
    public SensorDataValues getSensorDataValues() {
        return new SensorDataValues(sensorDataValues);
    }

    public Location getLocation() {
        return location;
    }

    public Sensor getSensor() {
        return sensor;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "{%s,%s,%s}", location, sensor, sensorDataValues);
    }
    
    /**
     * This is the top-level element at
     * http://api.luftdaten.info/static/v2/data.dust.min.json
     */
    public static final class DataPoints extends ArrayList<DataPoint> {
        private static final long serialVersionUID = 3877029198019218794L;
        
    }

}