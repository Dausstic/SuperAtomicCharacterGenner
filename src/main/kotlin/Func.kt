import java.io.File

fun generateCharacter(
    filePath: String?,
    count: Int,
    includeDescription: Boolean
): List<String> {
    val results: MutableList<String> = mutableListOf()

    if (filePath == null) {
        results.add("No matching CSV file found.")
        return results
    }

    val file = File(filePath)
    if (!file.exists()) {
        results.add("No matching CSV file found: $filePath")
        return results
    }

    val rows: MutableList<List<String>> = mutableListOf()
    file.forEachLine { line ->
        val columns = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)".toRegex())
            .map { it.trim().removeSurrounding("\"") }
        rows.add(columns)
    }

    if (rows.size > count) {
        repeat(count) {
            val randomRowIndex = (0 until rows.size).random()
            val randomResult = rows[randomRowIndex][0]
            val description = if (includeDescription) rows[randomRowIndex].drop(1).joinToString(", ") else ""
            results.add("$randomResult${if (description.isNotEmpty()) ": $description" else ""}")
            rows.removeAt(randomRowIndex)
        }
    } else {
        rows.forEach { row ->
            val result = row[0]
            val description = if (includeDescription) row.drop(1).joinToString(", ") else ""
            results.add("$result${if (description.isNotEmpty()) ": $description" else ""}")
        }
    }

    return results
}

fun getResults() : String {
    println("Enter the number of results to generate for each option:")
    print("Training & Talents: ")
    val trainingTalentsCount = readIntInput()
    print("Major Powers: ")
    val majorPowersCount = readIntInput()
    print("Minor Powers: ")
    val minorPowersCount = readIntInput()
    print("Mutations: ")
    val mutationsCount = readIntInput()

    val backgroundOriginFilePath = "rolltables/backgroundorigin.csv"
    val superheroOriginFilePath = "rolltables/superheroorigin.csv"
    val trainingTalentsFilePath = "rolltables/training.csv"
    val majorPowersFilePath = "rolltables/majorpowers.csv"
    val minorPowersFilePath = "rolltables/minorpowers.csv"
    val mutationsFilePath = "rolltables/mutations.csv"

    val backgroundOrigins = generateCharacter(backgroundOriginFilePath, 1, false)
    val superheroOrigins = generateCharacter(superheroOriginFilePath, 1, false)
    val trainingTalents = generateCharacter(trainingTalentsFilePath, trainingTalentsCount, false)
    val majorPowers = generateCharacter(majorPowersFilePath, majorPowersCount, true)
    val minorPowers = generateCharacter(minorPowersFilePath, minorPowersCount, true)
    val mutations = generateCharacter(mutationsFilePath, mutationsCount, true)

    println("Generated results:")
    displayResults("Your background origins:", backgroundOrigins)
    displayResults("Your superhero origins:", superheroOrigins)
    displayResults("Your training & talents:", trainingTalents)
    displayResults("Your major powers:", majorPowers)
    displayResults("Your minor powers:", minorPowers)
    displayResults("Your mutations:", mutations)

    return "Generated results:\n\nYour background origins: $backgroundOrigins,\n\nYour superhero origins: $superheroOrigins,\n\nYour training & talents: $trainingTalents,\n\nYour major powers: $majorPowers,\n\nYour minor powers: $minorPowers,\n\nYour mutations: $mutations"

}

fun displayResults(categoryName: String, results: List<String>) {
    println(categoryName)
    if (results.isNotEmpty()) {
        results.forEach { result ->
            println("  - $result")
        }
    } else {
        println("  No result generated.")
    }
}

fun readIntInput(): Int {
    var input: String?
    do {
        input = readLine()
    } while (input == null || input.toIntOrNull() == null)
    return input.toInt()
}
