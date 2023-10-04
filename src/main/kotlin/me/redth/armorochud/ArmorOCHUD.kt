package me.redth.armorochud

import me.redth.armorochud.config.ModConfig
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(modid = ArmorOCHUD.MODID, name = ArmorOCHUD.NAME, version = ArmorOCHUD.VERSION, modLanguageAdapter = "cc.polyfrost.oneconfig.utils.KotlinLanguageAdapter")
object ArmorOCHUD {
    const val MODID = "@ID@"
    const val NAME = "@NAME@"
    const val VERSION = "@VER@"

    @Mod.EventHandler
    fun onInit(e: FMLInitializationEvent) {
        ModConfig
    }
}