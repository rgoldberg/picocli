package picocli.examples.kotlin

import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.HelpCommand
import java.util.Locale

@Command(name = "ISOCodeResolve", mixinStandardHelpOptions = true, version = ["1.0"], subcommands = [ HelpCommand::class ],
        description = ["Resolve ISO country codes (ISO-3166-1) or language codes (ISO 639-1 or -2)"])
class SubCmdsViaMethods : Runnable  {
    @CommandLine.Spec
    val spec: CommandLine.Model.CommandSpec? = null

    @Command(description = ["Resolve ISO country code (ISO-3166-1, Alpha-2 code)"])
    fun country( @CommandLine.Parameters( arity = "1..*n", paramLabel = "<country1> <country2>",
            description = ["country code(s) to be resolved"] ) vararg countryCodes : String)
    {
        for (code in countryCodes) {
            println("${code.toUpperCase()}: " + Locale("", code).displayCountry)
        }
    }

    @Command(description = ["Resolve ISO language code (ISO 639-1 or -2, two/three letters)"])
    fun language( @CommandLine.Parameters( arity = "1..*n", paramLabel = "<code> <code2>",
            description = ["language code(s) to be resolved"] ) vararg languageCodes : String)
    {
        for (code in languageCodes) {
            println("${code.toUpperCase()}: " + Locale(code).displayLanguage)
        }
    }

    override fun run() = throw CommandLine.ParameterException(spec?.commandLine(), "Specify a subcommand")
}

fun main(args: Array<String>) = System.exit(CommandLine(SubCmdsViaMethods()).execute(*args))