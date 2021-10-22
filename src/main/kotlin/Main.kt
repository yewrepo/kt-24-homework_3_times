val minutes = arrayOf("минуту", "минуты", "минут")//one,two,few
val hours = arrayOf("час", "часа", "часов")

const val justNow = "Только что"
const val suffix = "назад"
const val prefix = "Был(а)"
const val today = "сегодня"
const val yesterday = "вчера"
const val longAgo = "Давно"

const val minute = 60
const val hour = 3600
const val day = 86400
const val day2 = 172800
const val day3 = 259200

fun main() {
    println(getTextVariants(30))
    println(getTextVariants(120))
    println(getTextVariants(hour)) //1
    println(getTextVariants(43200)) //12
    println(getTextVariants(93600)) //26
    println(getTextVariants(day2)) //48
    println(getTextVariants(day3)) //72
}

//нашел в gist
fun getPlural(number: Int, words: Array<String>): String {
    val cases = arrayOf(2, 0, 1, 1, 1, 2)
    val word = words[
            if (number % 100 in 5..19) 2
            else cases[
                    if (number % 10 < 5)
                        number % 10
                    else 5]]
    return "$number $word"
}


fun getTextVariants(number: Int): String = when (number) {
    in (0..minute) -> justNow
    in (minute + 1..hour) -> "$prefix ${getPlural(number.toMinutes(), minutes)} $suffix"
    in (hour + 1..day) -> "$prefix ${getPlural(number.toHours(), hours)} $suffix"
    in (day + 1..day2) -> "$prefix $today"
    in (day2 + 1..day3) -> "$prefix $yesterday"
    else -> longAgo
}

private fun Int.toHours(): Int = this.also {
    return it / hour
}

private fun Int.toMinutes(): Int = this.also {
    return it / minute
}
