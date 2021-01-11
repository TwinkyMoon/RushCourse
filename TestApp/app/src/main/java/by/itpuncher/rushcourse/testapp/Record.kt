package by.itpuncher.rushcourse.testapp

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_FORMAT = "MMM, dd yyyy HH:mm" // Dec, 9 2020 09:15

data class Record(val title: String = "", val text: String = ""): Serializable {

    companion object {
        private val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

        fun build(timeMillis: Long, text: String): Record {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeMillis
            return Record(formatter.format(calendar.time), text)
        }
    }
}
