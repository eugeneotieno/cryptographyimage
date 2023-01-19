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

                println("Message to hide:")
                val msg = readln()

                try {

                    val bufferedImage = ImageIO.read(File(inputName))

                    val encodedMsg = msg.encodeToByteArray()
                    val binaryList = convertToBinary(encodedMsg)
                    binaryList.add("00000000")
                    binaryList.add("00000000")
                    binaryList.add("00000011")

                    val bitArray = mutableListOf<String>()
                    repeat(binaryList.size) { index->
                        val bits = binaryList[index].split("")
                        for (bit in bits) {
                            if (bit.isNotBlank()) bitArray.add(bit)
                        }
                    }

                    val bitsSize = bufferedImage.width * bufferedImage.height
                    if (bitsSize >= bitArray.size) {
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
                    } else {
                        println("The input image is not large enough to hold this message.")
                    }


                } catch (e: Exception){
                    println("Input Image: $inputName")
                    println("Output Image: $outputName")
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

fun convertToBinary(byteArray: ByteArray): MutableList<String> {
    val listArray = byteArray.map { Integer.toBinaryString(it.toInt() and 0xFF) }.toMutableList()
    for (i in listArray.indices) {
        var pad = ""
        val padLength = 8 - listArray[i].length
        repeat(padLength) {
            pad += "0"
        }
        if (pad.isNotBlank()) {
            listArray[i] = "$pad${listArray[i]}"
        }
    }
    return listArray
}