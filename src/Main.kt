fun main() {
    println("Задание какое запустить? ")
    val a = readln()
    if(a == "1") zadanie1()
    else if(a == "2") zadanie2()
}
//Функция запускающая 1 задачу
fun zadanie1() {
    println("Введите кол-во секунд")
    val time:Int = readln().toInt()
    println(agoToText(time))
}

// Первая обработка времени 4 варианта сразу можем обработать.
// Другие варианты будут обработаны в дальнейших функциях
fun agoToText(secondsA: Int): String {
    return when (secondsA) {
        in 0..60 -> "Был(а) только что"
        in 61 until (60 * 60) -> minutesAgo((secondsA / 60))
        in (60 * 60) until (24 * 60 * 60) -> hoursAgo((secondsA / (60 * 60)))
        in (24 * 60 * 60) until (24 * 60 * 60 * 2) -> "Был(а) вчера"
        in (24 * 60 * 60 * 2) until (24 * 60 * 60 * 3) -> "Был(а) позавчера"
        else -> "Был(а) давно"
    }
}

// для отображения минут
fun minutesAgo(minutes: Int): String {
    return when {
        minutes % 10 == 1 && minutes != 11 -> "$minutes минуту назад"
        minutes % 10 in 2..4 && !(minutes in 12..14) -> "$minutes минуты назад"
        else -> "$minutes минут назад"
    }
}

// для отображения часов
fun hoursAgo(hours: Int): String {
    return when {
        hours % 10 == 1 && hours != 11 -> "$hours час назад"
        hours % 10 in 2..4 && !(hours in 12..14) -> "$hours часа назад"
        else -> "$hours часов назад"
    }
}
//функция запускающая 2 задачу
fun zadanie2() {
    println("Введите карту/счёт")
    val Card:String = readln().toString()
    println("Введите сумму предыдущих переводов за этот месяц")
    val MonthAmount:Int = readln().toInt()
    println("Введите сумму перевода")
    val SummAmount:Int = readln().toInt()


    when(Card.lowercase()) {
        "vkpay" -> {
            //Если вк плей то проверяем соотвествующие лимиты, если не превышены, то без коммиссии перевод или ошибка
            if(MonthAmount <= 40000 && SummAmount <= 15000 && SummAmount > 0) println("Реквизит ${Card} итоговая сумма перевода ${SummAmount}")
            else println("Ошибка перевода по ${Card} сумма перевода ${SummAmount}")
        }
        //Если мастеркард или маестро то проверяем соотвествующие лимиты, если не превышены,
        // то проверяем чтобы перевод был до 75000 рублей тогда без коммиссии
        //или будет с коммисией
        "mastercard", "maestro" -> {
            if(MonthAmount <= 600000 && SummAmount <= 150000 && SummAmount > 0) {
                if(SummAmount >= 300 && SummAmount < 75000) println("Реквизит ${Card} итоговая сумма перевода ${SummAmount}")
                else println("Реквизит ${Card} итоговая сумма перевода ${SummAmount-((SummAmount*0.006)+20)}")
            } else println("Ошибка перевода по ${Card} сумма перевода ${SummAmount}")

        }
        //если visa или мир то проверяем лимиты и минимум перевод 35 рублей
        //тогда считаем сразу коммисию, так без коммиссии нету перевода в этом варианте карт
        "visa", "мир" -> {
            if(MonthAmount <= 600000 && SummAmount <= 150000 && SummAmount > 0) {
                if ((SummAmount >= 35) println("Реквизит ${Card} итоговая сумма перевода ${SummAmount-(SummAmount*0.0075)}")
            } else println("Ошибка перевода по ${Card} сумма перевода ${SummAmount}")
        }
        else -> println("Ошибка! Неправильно написан реквизит перевода")

    }


}
