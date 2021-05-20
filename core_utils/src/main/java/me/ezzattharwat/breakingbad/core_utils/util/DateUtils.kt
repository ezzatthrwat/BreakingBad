package me.ezzattharwat.breakingbad.core_utils.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*

object DateUtils {

    private fun getDiffYears(date: Date?, current: Date?): Int {
        val a: Calendar = getCalendar(date)
        val b: Calendar = getCalendar(current)
        var diff: Int = b.get(YEAR) - a.get(YEAR)
        if (a.get(MONTH) > b.get(MONTH) ||
            a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE)
        ) {
            diff--
        }
        return diff
    }

    private fun getDiffMonths(date: Date?, current: Date?): Int {
        val a: Calendar = getCalendar(date)
        val b: Calendar = getCalendar(current)

        return if (a.get(MONTH) > b.get(MONTH)) {
            12 - (a.get(MONTH) - b.get(MONTH))
        } else if (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE)){
            0
        } else {
             b.get(MONTH) - a.get(MONTH)
        }

    }

    private fun getDiffDays(date: Date?, current: Date?): Int {
        val a: Calendar = getCalendar(date)
        val b: Calendar = getCalendar(current)

        return  if (b.get(DAY_OF_MONTH) == a.get(DAY_OF_MONTH)) {
            0
        } else {
            b.get(DAY_OF_MONTH)
        }

    }

    fun getLiveAge(birthDate : String/*, context: Context*/) :String {

        val current = getInstance().time
        val df = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

      return  "${getDiffYears(df.parse(birthDate), current)} years, "+
              "${getDiffMonths(df.parse(birthDate), current)} months, " +
              "${getDiffDays(df.parse(birthDate), current)} days, "
    }

    private fun getCalendar(date: Date?): Calendar {
        val cal: Calendar = getInstance(Locale.US)
        date?.let {
            cal.time = it
        }
        return cal
    }
}