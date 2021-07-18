package converters;

import models.Unit;

import static models.Names.BTU;
import static models.Names.CALORIES;
import static models.Names.JOULES;
import static models.Names.KILOCALORIES;
import static models.Names.KILOJOULES;
import static models.Names.MEGAJOULES;
import static models.Names.THERM;

public class EnergyConverter {

    static Unit convertEnergy(double value, String from, String to){
        switch (to){
            case BTU:
                return toBtu(value, from);
            case CALORIES:
                return toCalories(value, from);
            case KILOCALORIES:
                return toKiloCalories(value, from);
            case MEGAJOULES:
                return toMegaJoules(value, from);
            case KILOJOULES:
                return toKiloJoules(value, from);
            case THERM:
                return toTherms(value, from);
            default:
                return toJoules(value, from);
        }
    }

    private static Unit toBtu(double value, String from){
        switch (from){
            case JOULES:
                value /= 1055.06; break;
            case CALORIES :
                value *= 0.003966; break;
            case KILOCALORIES:
                value *= 3.966; break;
            case KILOJOULES:
                value *= 0.947817; break;
            case MEGAJOULES:
                value *= 947.817; break;
            case THERM:
                value *= 100066.946704; break;
        }
        return new Unit(BTU, "btu", value);
    }

    private static Unit toJoules(double value, String from){
        switch (from){
            case BTU:
                value *= 1055.06; break;
            case CALORIES :
                value *= 4.184; break;
            case KILOCALORIES:
                value *= 4184; break;
            case KILOJOULES:
                value *= 1000; break;
            case MEGAJOULES:
                value *= 1000000; break;
            case THERM:
                value *= 105505585.257; break;
        }
        return new Unit(JOULES, "J", value);
    }

    private static Unit toKiloJoules(double value, String from){
        switch (from){
            case BTU:
                value *= 1.05506; break;
            case CALORIES :
                value *= 0.004184; break;
            case KILOCALORIES:
                value *= 4.184; break;
            case JOULES:
                value /= 1000; break;
            case MEGAJOULES:
                value *= 1000; break;
            case THERM:
                value *= 105505.585257; break;
        }
        return new Unit(KILOJOULES, "kJ", value);
    }

    private static Unit toMegaJoules(double value, String from){
        switch (from){
            case BTU:
                value *= 0.005506; break;
            case CALORIES :
                value *= 0.00000418; break;
            case KILOCALORIES:
                value *= 0.004184; break;
            case JOULES:
                value /= 1000000; break;
            case KILOJOULES:
                value /= 1000; break;
            case THERM:
                value *= 105.505585; break;
        }
        return new Unit(KILOJOULES, "kJ", value);
    }

    private static Unit toCalories(double value, String from){
        switch (from){
            case BTU:
                value *= 251.995698; break;
            case JOULES:
                value *= 0.239006; break;
            case KILOCALORIES:
                value *= 1000; break;
            case KILOJOULES:
                value *= 239.006; break;
            case MEGAJOULES:
                value *= 239006; break;
            case THERM:
                value *= 25216440.07; break;
        }
        return new Unit(CALORIES, "cal", value);
    }

    private static Unit toKiloCalories(double value, String from){
        switch (from){
            case BTU:
                value *= 0.251995698; break;
            case JOULES:
                value *= 0.000239006; break;
            case CALORIES:
                value /= 1000; break;
            case KILOJOULES:
                value *= 0.239006; break;
            case MEGAJOULES:
                value *= 239.006; break;
            case THERM:
                value *= 25216.44007; break;
        }
        return new Unit(CALORIES, "kcal", value);
    }

    private static Unit toTherms(double value, String from){
        switch (from){
            case BTU:
                value /= 100066.946704; break;
            case JOULES:
                value /= 105505585.257; break;
            case CALORIES:
                value /= 25216440.07; break;
            case KILOCALORIES:
                value /= 25216.44007; break;
            case KILOJOULES:
                value /= 105505.585257; break;
            case MEGAJOULES:
                value /= 105.505585; break;
        }
        return new Unit(CALORIES, "kcal", value);
    }
}
