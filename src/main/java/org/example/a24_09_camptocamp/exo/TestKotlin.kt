package org.example.a24_09_camptocamp.exo


var toto : String? = ""

data class Student(var name: String = "", var surname : String? = null, var old : Int = 0)

fun main() {

    var student= Student(surname =  "toto")

    var myLambdav : (Int)-> Int = { it/60}

    var finalToto = toto
    if(finalToto != null) {
        println(finalToto.uppercase() )
    }
    else {
        println("-")
    }

    var res = toto?.uppercase() ?: "-"
}