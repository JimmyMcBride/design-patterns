package patterns

class MainCharacter private constructor(
    val name: String,
) {
    var maxHealth: Int? = null
        private set
    var maxStamina: Int? = null
        private set

    class Builder internal constructor(name: String) {
        private var mainCharacterToBuild: MainCharacter? = null

        init {
            mainCharacterToBuild = MainCharacter(name)
        }

        fun setMaxHealth(value: Int): Builder {
            this.mainCharacterToBuild!!.maxHealth = value
            return this
        }

        fun setMaxStamina(value: Int): Builder {
            this.mainCharacterToBuild!!.maxStamina = value
            return this
        }

        internal fun build(): MainCharacter? {
            return mainCharacterToBuild
        }
    }
}

fun displayBuilderPattern() {
    val myCharacter = MainCharacter.Builder("Jimmy").setMaxHealth(200).setMaxStamina(150).build()
    println("Max health for ${myCharacter?.name}: ${myCharacter?.maxHealth}")
    println("Max stamina for ${myCharacter?.name}: ${myCharacter?.maxStamina}")
}

