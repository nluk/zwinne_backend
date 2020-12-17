package agile.fuel

import java.util.*

fun Date.plusMillis(millis : Long) : Date{
    return Date(this.time + millis)
}