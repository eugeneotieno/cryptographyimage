fun main() {
    var showMenu = true
    while (showMenu) {
        println("Task (hide, show, exit):")
        when(val action = readln()) {
            "hide" -> {
                println("Hiding message in image.")
            }
            "show" -> {
                println("Obtaining message from image.")
            }
            "exit" -> {
                showMenu = false
                println("Bye!")
            }
            else -> println("Wrong task: $action")
        }
    }
}