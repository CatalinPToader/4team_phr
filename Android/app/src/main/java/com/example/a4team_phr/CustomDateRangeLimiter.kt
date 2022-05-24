package com.example.a4team_phr

import android.os.Parcel
import android.os.Parcelable
import com.wdullaer.materialdatetimepicker.date.DateRangeLimiter
import java.util.*

class CustomDateRangeLimiter() : DateRangeLimiter {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
    }

    override fun getStartDate(): Calendar {
        return Calendar.getInstance()
    }

    override fun getEndDate(): Calendar {
        val max = Calendar.getInstance()
        max.set(max[Calendar.YEAR] + 1, Calendar.JANUARY, 1)
        return max
    }

    override fun isOutOfRange(year: Int, month: Int, day: Int): Boolean {
        val cal = endDate
        cal.set(year, month, day)
        if (cal[Calendar.DAY_OF_WEEK] == Calendar.SATURDAY || cal[Calendar.DAY_OF_WEEK] == Calendar.SUNDAY)
            return true
        return false
    }

    override fun setToNearestDate(day: Calendar): Calendar {
        return day
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CustomDateRangeLimiter> {
        override fun createFromParcel(parcel: Parcel): CustomDateRangeLimiter {
            return CustomDateRangeLimiter(parcel)
        }

        override fun newArray(size: Int): Array<CustomDateRangeLimiter?> {
            return arrayOfNulls(size)
        }
    }
}