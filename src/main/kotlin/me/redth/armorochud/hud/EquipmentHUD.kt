package me.redth.armorochud.hud

import cc.polyfrost.oneconfig.config.annotations.Dropdown
import me.redth.armorochud.config.ModConfig
import net.minecraft.item.ItemStack
import kotlin.math.roundToInt

open class EquipmentHUD(
    x: Int,
    y: Int,
    exampleItem: ItemStack,
    @field:Transient protected val slot: Int
) : ItemHUD(x, y, exampleItem) {

    @Dropdown(name = "Show Durability", options = ["None", "On Left", "On Right"], size = 2)
    var showDurability = 2

    override fun getItem(): ItemStack? = mc.thePlayer.getEquipmentInSlot(slot)

    override fun drawItem(item: ItemStack?) {
        super.drawItem(item)
        if (item?.isItemStackDamageable == true) {
            drawDurability(item, showDurability)
        }
    }

    companion object {
        private fun drawDurability(item: ItemStack, side: Int) {
            if (side == 0) return
            val text = getDurabilityText(item)
            val color = getDurabilityColor(item)
            val x = when (side) {
                1 -> -2F - mc.fontRendererObj.getStringWidth(text).toFloat()
                else -> 18f
            }
            mc.fontRendererObj.drawStringWithShadow(text, x, 4f, color)
        }

        private fun getDurabilityText(item: ItemStack) = when (ModConfig.durabilityFormat) {
            0 -> "${(100.0 - item.item.getDurabilityForDisplay(item) * 100.0).roundToInt()}%"
            1 -> "${item.maxDamage - item.itemDamage}"
            else -> "${item.maxDamage - item.itemDamage}/${item.maxDamage}"
        }

        private fun getDurabilityColor(item: ItemStack): Int {
            if (ModConfig.useStaticTextColor) return ModConfig.staticTextColor.rgb
            val i = (item.item.getDurabilityForDisplay(item) * 255.0).roundToInt()
            return i shl 16 or (255 - i shl 8) or -0x1000000
        }
    }
}
