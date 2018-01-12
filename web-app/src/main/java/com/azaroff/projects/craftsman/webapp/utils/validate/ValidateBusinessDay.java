package com.azaroff.projects.craftsman.webapp.utils.validate;

import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

@Component
public class ValidateBusinessDay {

    public boolean isBusinessDay(LocalDate ld) {
        // check if weekend
        if (ld.getDayOfWeek() == DayOfWeek.SATURDAY.SATURDAY || ld.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return false;
        }
        // check if New Year's Day
        if (ld.getMonth() == Month.JANUARY && ld.getDayOfMonth() == 1) {
            return false;
        }
        // check if Christmas
        if (ld.getMonth() == Month.DECEMBER && ld.getDayOfMonth() == 25) {
            return false;
        }
        // check if 4th of July
        if (ld.getMonth() == Month.JULY && ld.getDayOfMonth() == 4) {
            return false;
        }
        // check Thanksgiving (4th Thursday of November)
        if (ld.getMonth() == Month.NOVEMBER
                && ld.getDayOfMonth() == 4
                && ld.getDayOfWeek() == DayOfWeek.THURSDAY) {
            return false;
        }
        // check Memorial Day (last Monday of May)
        if (ld.getMonth() == Month.MAY
                && ld.getDayOfWeek() == DayOfWeek.MONDAY
                && ld.getDayOfMonth() > (31 - 7)) {
            return false;
        }
        // check Labor Day (1st Monday of September)
        if (ld.getMonth() == Month.SEPTEMBER
                && ld.getDayOfMonth() == 1
                && ld.getDayOfWeek() == DayOfWeek.MONDAY) {
            return false;
        }
        // check President's Day (3rd Monday of February)
        if (ld.getMonth() == Month.FEBRUARY
                && ld.getDayOfMonth() == 3
                && ld.getDayOfWeek() == DayOfWeek.MONDAY) {
            return true;
        }
        // check Veterans Day (November 11)
        if (ld.getMonth() == Month.NOVEMBER
                && ld.getDayOfMonth() == 11) {
            return true;
        }
        // check MLK Day (3rd Monday of January)
        if (ld.getMonth() == Month.JANUARY
                && ld.getDayOfMonth() == 3
                && ld.getDayOfWeek() == DayOfWeek.MONDAY) {
            return true;
        }
        // IF NOTHING ELSE, IT'S A BUSINESS DAY
        return true;
    }
}