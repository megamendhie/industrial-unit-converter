package converters;

import java.util.ArrayList;

import models.Unit;

import static models.Names.UNITS_LENGTH;

public class GeneralUnitConverter {

    public ArrayList<Unit> convertLength(String from, double value){
        ArrayList<Unit> newValues = new ArrayList<>();
        for(String unit: UNITS_LENGTH)
            newValues.add(LengthConverter.convertLength(value,from,unit));
        return newValues;
    }
}
