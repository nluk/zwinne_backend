package agile.fuel

import java.util.*

infix fun Date.plusMillis(millis : Long) : Date{
    return Date(this.time + millis)
}