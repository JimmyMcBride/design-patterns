package patterns

/**
 * Strategy pattern enables a client code to choose from a family of related but different algorithms and gives it a
 * simple way to choose any of the algorithm in runtime depending on the client context.
 */

data class Character(
    val name: String,
    val powerLevel: Int
)

data class Item(
    val name: String,
    val weight: Double,
    val value: Double
) {

    fun pricePerUnit(): Double {
        println("${this.name} value = ${this.value}")
        return value / weight
    }
}

// We want our weight modifier to run different algorithms based on context passed by the client
data class Inventory(
    val character: Character,
    val allItems: List<Item>,
    val weightModifier: WeightModifier? = null
) {

    fun totalWeight(): Double = allItems.sumOf { it.weight }

}

// This is our strategy interface
interface WeightModifier {
    fun modifiedWeight(itemList: List<Item>): Double
}

// This is a context for our strategy
class FeatherWeight : WeightModifier {
    override fun modifiedWeight(itemList: List<Item>): Double {
        val originalWeight = itemList.sumOf { it.weight }
        return originalWeight * 0.7
    }

}

// This is a context for our strategy
class HeavyWeight : WeightModifier {
    override fun modifiedWeight(itemList: List<Item>): Double {
        val originalWeight = itemList.sumOf { it.weight }
        return (originalWeight - 0.32) * 1.7
    }

}

fun displayStrategyPattern() {
    val daemon = Character(
        "Daemon",
        10
    )
    val lilith = Character(
        "Lilith",
        10
    )
    val belphegor = Character(
        "Belphegor",
        10
    )

    val healingPotion = Item(
        name = "Healing Potion",
        weight = 0.5f.toDouble(),
        value = 50f.toDouble()
    )
    val manaPotion = Item(
        name = "Mana Potion",
        weight = 0.5f.toDouble(),
        value = 40f.toDouble()
    )
    val boostStrengthPotion = Item(
        name = "Boost Strength Potion",
        weight = 0.5f.toDouble(),
        value = 100f.toDouble()
    )

    println("Healing potion price per unit: ${healingPotion.pricePerUnit()}")
    println("Mana potion price per unit: ${manaPotion.pricePerUnit()}")
    println("Boost strength potion price per unit: ${boostStrengthPotion.pricePerUnit()}")

    val daemonInventorySystem = Inventory(
        character = daemon,
        allItems = listOf(
            healingPotion,
            manaPotion,
            boostStrengthPotion
        ),
        weightModifier = FeatherWeight()
    )
    val lilithInventorySystem = Inventory(
        character = lilith,
        allItems = listOf(
            healingPotion,
            manaPotion,
            boostStrengthPotion
        )
    )
    val belphegorInventorySystem = Inventory(
        character = belphegor,
        allItems = listOf(
            healingPotion,
            manaPotion,
            boostStrengthPotion
        ),
        weightModifier = HeavyWeight()
    )

    val daemonAppliedWeight = daemonInventorySystem.getAppliedWeight()
    println("Char1 - Current applied weight: $daemonAppliedWeight")

    val lilithAppliedWeight = lilithInventorySystem.getAppliedWeight()
    println("Char2 - Current applied weight: $lilithAppliedWeight")

    val belphegorAppliedWeight = belphegorInventorySystem.getAppliedWeight()
    println("Char2 - Current applied weight: $belphegorAppliedWeight")
}

fun Inventory.getAppliedWeight(): Double {
    return if (this.weightModifier != null) {
        this.weightModifier.modifiedWeight(this.allItems)
    } else {
        this.totalWeight()
    }
}
