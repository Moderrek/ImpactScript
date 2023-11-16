import com.impact.script.Lexer

fun runConsole() {
    val lexer = Lexer()
    while (true) {
        print("is >>> ")
        val input = readln()
        if (input == "exit()") break
        val tokens = lexer.tokenize(input)
        for (token in tokens) {
            println(token)
        }
    }

}

fun runFile(fileName: String) {

}

fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")
    if (args.isEmpty()) {
        runConsole();
        return
    }
    val fileName = args[0]
    runFile(fileName)
}