package me.redth.armorochud.config

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.Color
import cc.polyfrost.oneconfig.config.annotations.Dropdown
import cc.polyfrost.oneconfig.config.annotations.HUD
import cc.polyfrost.oneconfig.config.annotations.Switch
import cc.polyfrost.oneconfig.config.core.OneColor
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType
import me.redth.armorochud.ArmorOCHUD
import me.redth.armorochud.hud.*

object ModConfig : Config(Mod(ArmorOCHUD.NAME, ModType.HUD), "${ArmorOCHUD.MODID}.json") {
    @Dropdown(name = "Durability Format", options = ["Percentage%", "Remaining", "Remaining/Max"], size = 2)
    var durabilityFormat = 2

    @Switch(name = "Use Static Text Color")
    var useStaticTextColor = false

    @Color(name = "Static Text Color")
    var staticTextColor = OneColor(0xFFFFFFFF.toInt())

    @HUD(name = "Hand", category = "Hand")
    var handHUD = HandHUD

    @HUD(name = "Helmet", category = "Helmet")
    var helmetHUD = HelmetHUD

    @HUD(name = "Chestplate", category = "Chestplate")
    var chestplateHUD = ChestplateHUD

    @HUD(name = "Leggings", category = "Leggings")
    var leggingsHUD = LeggingsHUD

    @HUD(name = "Boots", category = "Boots")
    var bootsHUD = BootsHUD

    @HUD(name = "Arrow", category = "Arrow")
    var arrowHUD = ArrowHUD

    init {
        initialize()
        addDependency("staticTextColor", "useStaticTextColor")
    }

}