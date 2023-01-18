import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

fun main() {
    var showMenu = true
    while (showMenu) {
        println("Task (hide, show, exit):")
        when(val action = readln()) {
            "hide" -> {
                println("Input image file:")
                val inputName = readln()

                println("Output image file:")
                val outputName = readln()

                try {

                    println("Input Image: $inputName")
                    println("Output Image: $outputName")

                    val bufferedImage = ImageIO.read(File(inputName))
                    for (x in 0 until bufferedImage.width) {
                        for (y in 0 until bufferedImage.height) {
                            val color = Color(bufferedImage.getRGB(x, y))
                            val red = color.red or 1
                            val green = color.green or 1
                            val blue = color.blue or 1
                            val rgb = Color(red, green, blue).rgb
                            bufferedImage.setRGB(x,y, rgb)
                        }
                    }
                    ImageIO.write(bufferedImage, "png", File(outputName))
                    println("Image $outputName is saved.")
                } catch (e: Exception){
                    println("Can't read input file!")
                }
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