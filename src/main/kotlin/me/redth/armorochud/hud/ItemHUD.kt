package me.redth.armorochud.hud

import cc.polyfrost.oneconfig.hud.Hud
import cc.polyfrost.oneconfig.libs.universal.UMatrixStack
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.RenderHelper
import net.minecraft.item.ItemStack

abstract class ItemHUD(
    x: Int,
    y: Int,
    @field:Transient protected val exampleItem: ItemStack
) : Hud(true, x.toFloat(), y.toFloat()) {

    override fun draw(matrices: UMatrixStack, x: Float, y: Float, scale: Float, example: Boolean) {
        val item = if (example) exampleItem else getItem() ?: return

        GlStateManager.pushMatrix()
        GlStateManager.translate(x, y, 100f)
        GlStateManager.scale(scale.toDouble(), scale.toDouble(), 1.0)
        drawItem(item)
        GlStateManager.popMatrix()
    }

    abstract fun getItem(): ItemStack?

    override fun getWidth(scale: Float, example: Boolean) = 16 * scale

    override fun getHeight(scale: Float, example: Boolean) = 16 * scale

    open fun drawItem(item: ItemStack?) {
        GlStateManager.enableRescaleNormal()
        GlStateManager.enableBlend()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        RenderHelper.enableGUIStandardItemLighting()
        val itemRenderer = mc.renderItem
        itemRenderer.renderItemAndEffectIntoGUI(item, 0, 0)
        itemRenderer.renderItemOverlayIntoGUI(mc.fontRendererObj, item, 0, 0, null)
        RenderHelper.disableStandardItemLighting()
        GlStateManager.disableBlend()
        GlStateManager.disableRescaleNormal()
        GlStateManager.enableAlpha()
    }

    companion object {
        @Transient
        val mc = Minecraft.getMinecraft()!!
    }
}
