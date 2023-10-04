package me.redth.armorochud.hud

import cc.polyfrost.oneconfig.config.annotations.Switch
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack


object ArrowHUD : ItemHUD(0, 476, ItemStack(Items.arrow, 10)) {

    @Transient
    private val barrier = ItemStack(Blocks.barrier)

    @Transient
    private val creative = ItemStack(Items.arrow)

    @Switch(name = "Display when holding bow", size = 2)
    var whenHoldingBow = true

    override fun getItem(): ItemStack {
        if (mc.thePlayer.capabilities.isCreativeMode) return creative

        var arrows = 0
        for (item in mc.thePlayer.inventory.mainInventory) {
            if (item != null && item.item === Items.arrow) {
                arrows += item.stackSize
            }
        }

        if (arrows == 0) return barrier

        return ItemStack(Items.arrow, arrows)
    }

    override fun shouldShow() = super.shouldShow() && isHoldingBow()

    fun isHoldingBow(): Boolean {
        if (!whenHoldingBow) return true
        val item = mc.thePlayer.heldItem
        return item != null && item.item === Items.bow
    }
}
