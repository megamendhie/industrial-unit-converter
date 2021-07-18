package converters;

import models.Unit;

import static models.Names.CENTIMETER;
import static models.Names.DECIMETER;
import static models.Names.DEKAMETER;
import static models.Names.FEET;
import static models.Names.HECTOMETER;
import static models.Names.KILOMETER;
import static models.Names.METER;
import static models.Names.MILE;
import static models.Names.MILLIMETER;

public class LengthConverter {

    static Unit convertLength(double value, String from, String to){
        switch (to){
            case MILLIMETER:
                return toMillimeter(value, from);
            case CENTIMETER:
                return toCentimeter(value, from);
            case DECIMETER:
                return toDecimeter(value, from);
            case DEKAMETER:
                return toDekameter(value, from);
            case HECTOMETER:
                return toHectometer(value, from);
            case KILOMETER:
                return toKilometer(value, from);
            case MILE:
                return toMile(value, from);
            case FEET:
                return toFeet(value, from);
            default:
                return toMeter(value, from);
        }
    }

    private static Unit toMillimeter(double value, String from){
        switch (from){
            case MILE:
                value *= 1609344;  break;
            case FEET:
                value *= 304.8; break;
            case CENTIMETER:
                value *= 10; break;
            case DECIMETER:
                value *= 100; break;
            case METER:
                value *= 1000; break;
            case DEKAMETER:
                value *= 10000; break;
            case HECTOMETER:
                value *= 100000; break;
            case KILOMETER:
                value *= 1000000; break;
        }
        return new Unit(MILLIMETER, "mm", value);
    }

    private static Unit toCentimeter(double value, String from){
        switch (from){
            case MILE:
                value *= (160934.4);  break;
            case FEET:
                value *= 30.48; break;
            case MILLIMETER:
                value /= 10; break;
            case DECIMETER:
                value *= 10; break;
            case METER:
                value *= 100; break;
            case DEKAMETER:
                value *= 1000; break;
            case HECTOMETER:
                value *= 10000; break;
            case KILOMETER:
                value *= 100000; break;
        }
        return new Unit(CENTIMETER, "cm", value);
    }

    private static Unit toDecimeter(double value, String from){
        switch (from){
            case MILE:
                value *= (16093.44);  break;
            case FEET:
                value *= 3.048; break;
            case MILLIMETER:
                value /= 100; break;
            case CENTIMETER:
                value /= 10; break;
            case METER:
                value *= 10; break;
            case DEKAMETER:
                value *= 100; break;
            case HECTOMETER:
                value *= 1000; break;
            case KILOMETER:
                value *= 10000;break;
        }
        return new Unit(DECIMETER, "dm", value);
    }

    private static Unit toMeter(double value, String from){
        switch (from){
            case MILE:
                value *= (1609.344);  break;
            case FEET:
                value *= 0.3048; break;
            case MILLIMETER:
                value /= 1000; break;
            case CENTIMETER:
                value /= 100; break;
            case DECIMETER:
                value /= 10; break;
            case DEKAMETER:
                value *= 10; break;
            case HECTOMETER:
                value *= 100; break;
            case KILOMETER:
                value *= 1000; break;
        }
        return new Unit(METER, "m", value);
    }

    private static Unit toDekameter(double value, String from){
        switch (from){
            case MILE:
                value *= (160.9344); break;
            case FEET:
                value *= 0.03048; break;
            case MILLIMETER:
                value /= 10000; break;
            case CENTIMETER:
                value /= 1000; break;
            case DECIMETER:
                value /= 100; break;
            case METER:
                value /= 10; break;
            case HECTOMETER:
                value *= 10; break;
            case KILOMETER:
                value *= 100; break;
        }
        return new Unit(DEKAMETER, "dam", value);
    }

    private static Unit toHectometer(double value, String from){
        switch (from){
            case MILE:
                value *= (16.09344); break;
            case FEET:
                value *= 0.003048; break;
            case MILLIMETER:
                value /= 100000; break;
            case CENTIMETER:
                value /= 10000; break;
            case DECIMETER:
                value /= 1000; break;
            case METER:
                value /= 100; break;
            case DEKAMETER:
                value /= 10; break;
            case KILOMETER:
                value *= 10; break;
        }
        return new Unit(HECTOMETER, "hm", value);
    }

    private static Unit toKilometer(double value, String from){
        switch (from){
            case MILE:
                value *= (1.609344); break;
            case FEET:
                value *= 0.0003048; break;
            case MILLIMETER:
                value /= 1000000; break;
            case CENTIMETER:
                value /= 100000; break;
            case DECIMETER:
                value /= 10000; break;
            case METER:
                value /= 1000; break;
            case DEKAMETER:
                value /= 100; break;
            case HECTOMETER:
                value /= 10; break;
        }
        return new Unit(KILOMETER, "km", value);
    }

    private static Unit toMile(double value, String from){
        switch (from){
            case FEET:
                value *= 0.000189394; break;
            case MILLIMETER:
                value /= 1609344;  break;
            case CENTIMETER:
                value /= 160934.4;  break;
            case DECIMETER:
                value /= 16093.44;  break;
            case METER:
                value /= 1609.344;  break;
            case DEKAMETER:
                value /= 160.9344;  break;
            case HECTOMETER:
                value /= 16.09344;  break;
            case KILOMETER:
                value /= 1.609344;  break;
        }
        return new Unit(MILE, "mi", value);
    }

    private static Unit toFeet(double value, String from){
        switch (from){
            case MILE:
                value *= 5280; break;
            case MILLIMETER:
                value /= 304.8; break;
            case CENTIMETER:
                value /= 30.48; break;
            case DECIMETER:
                value /= 3.048; break;
            case METER:
                value *= 3.28084; break;
            case DEKAMETER:
                value *= 32.8084; break;
            case HECTOMETER:
                value *= 328.084; break;
            case KILOMETER:
                value *= 3280.84; break;
        }
        return new Unit(FEET, "ft", value);
    }
}
