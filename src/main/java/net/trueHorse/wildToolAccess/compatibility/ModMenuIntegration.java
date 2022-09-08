package net.trueHorse.wildToolAccess.compatibility;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.trueHorse.wildToolAccess.InGameHudAccess;
import net.trueHorse.wildToolAccess.WildToolAccessSoundEvents;
import net.trueHorse.wildToolAccess.config.WildToolAccessConfig;

import java.util.List;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            MinecraftClient client = MinecraftClient.getInstance();    
            ConfigBuilder confBuilder = ConfigBuilder.create()
                    .setParentScreen(client.currentScreen)
                    .setTitle(Text.translatable("screen.wildtoolaccess.config_screen"))
                    .setSavingRunnable(()->{
                        WildToolAccessConfig.createOrUpdateConfigFile();
                        WildToolAccessSoundEvents.updateSoundEventsAsConfigured();
                        ((InGameHudAccess)client.inGameHud).setAccessBarTexturesAsConfigured();
                    });
            ConfigCategory generalCat = confBuilder.getOrCreateCategory(Text.translatable("config_category.wildtoolaccess.general"));
            ConfigEntryBuilder eb = confBuilder.entryBuilder();

            generalCat.addEntry(eb.startBooleanToggle(Text.translatable("option.wildtoolaccess.mouse_select"), WildToolAccessConfig.getBoolValue("mouseSelect"))
                    .setDefaultValue(true)
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.mouse_select"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("mouseSelect", Boolean.toString(newVal)))
                    .build());
            generalCat.addEntry(eb.startBooleanToggle(Text.translatable("option.wildtoolaccess.esc_close"), WildToolAccessConfig.getBoolValue("escClose"))
                    .setDefaultValue(true)
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.esc_close"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("escClose", Boolean.toString(newVal)))
                    .build());
            generalCat.addEntry(eb.startIntSlider(Text.translatable("option.wildtoolaccess.select_sound_1"), WildToolAccessConfig.getIntValue("selectSound1"),0,3)
                    .setDefaultValue(1)
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.select_sound_1"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("selectSound1", Integer.toString(newVal)))
                    .build());
            generalCat.addEntry(eb.startIntSlider(Text.translatable("option.wildtoolaccess.select_sound_2"), WildToolAccessConfig.getIntValue("selectSound2"),0,3)
                    .setDefaultValue(1)
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.select_sound_2"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("selectSound2", Integer.toString(newVal)))
                    .build());
            generalCat.addEntry(eb.startIntSlider(Text.translatable("option.wildtoolaccess.bar_texture_1"), WildToolAccessConfig.getIntValue("barTexture1"),0,1)
                    .setDefaultValue(0)
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.bar_texture_1"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("barTexture1", Integer.toString(newVal)))
                    .build());
            generalCat.addEntry(eb.startIntSlider(Text.translatable("option.wildtoolaccess.bar_texture_2"), WildToolAccessConfig.getIntValue("barTexture2"),0,1)
                    .setDefaultValue(0)
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.bar_texture_2"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("barTexture2", Integer.toString(newVal)))
                    .build());
            generalCat.addEntry(eb.startIntField(Text.translatable("option.wildtoolaccess.x_offset"), WildToolAccessConfig.getIntValue("xOffset"))
                    .setDefaultValue(0)
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.x_offset"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("xOffset", Integer.toString(newVal)))
                    .build());
            generalCat.addEntry(eb.startIntField(Text.translatable("option.wildtoolaccess.y_offset"), WildToolAccessConfig.getIntValue("yOffset"))
                    .setDefaultValue(0)
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.y_offset"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("yOffset", Integer.toString(newVal)))
                    .build());
            generalCat.addEntry(eb.startIntField(Text.translatable("option.wildtoolaccess.space_between_slots"), WildToolAccessConfig.getIntValue("spaceBetweenSlots"))
                    .setDefaultValue(0)
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.space_between_slots"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("spaceBetweenSlots", Integer.toString(newVal)))
                    .build());
            generalCat.addEntry(eb.startStringDropdownMenu(Text.translatable("option.wildtoolaccess.labels"), WildToolAccessConfig.getStringValue("labels"), string -> Text.translatable("option_val.wildtoolaccess."+string))
                    .setDefaultValue("enchantments")
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.labels"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("labels", newVal))
                    .setSelections(List.of("all", "enchantments", "name", "none"))
                    .build());
            generalCat.addEntry(eb.startBooleanToggle(Text.translatable("option.wildtoolaccess.last_swapped_out_first"), WildToolAccessConfig.getBoolValue("lastSwappedOutFirst"))
                    .setDefaultValue(true)
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.last_swapped_out_first"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("lastSwappedOutFirst", Boolean.toString(newVal)))
                    .build());
            generalCat.addEntry(eb.startBooleanToggle(Text.translatable("option.wildtoolaccess.move_if_next_empty"), WildToolAccessConfig.getBoolValue("moveIfNextEmpty"))
                    .setDefaultValue(false)
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.move_if_next_empty"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("moveIfNextEmpty", Boolean.toString(newVal)))
                    .build());
            generalCat.addEntry(eb.startStringDropdownMenu(Text.translatable("option.wildtoolaccess.access_1"), WildToolAccessConfig.getStringValue("access1"), string -> Text.translatable("option_val.wildtoolaccess."+string))
                    .setDefaultValue("tools")
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.access_1"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("access1", newVal))
                    .setSelections(List.of("tools","swords","ranged weapons","potions","buckets","stuff"))
                    .requireRestart()
                    .build());
            generalCat.addEntry(eb.startStringDropdownMenu(Text.translatable("option.wildtoolaccess.access_2"), WildToolAccessConfig.getStringValue("access2"), string -> Text.translatable("option_val.wildtoolaccess."+string))
                    .setDefaultValue("swords")
                    .setTooltip(Text.translatable("tooltip.wildtoolaccess.access_2"))
                    .setSaveConsumer(newVal->WildToolAccessConfig.setValue("access2", newVal))
                    .setSelections(List.of("tools","swords","ranged weapons","potions","buckets","stuff"))
                    .requireRestart()
                    .build());

            return confBuilder.build();
        };
    }
}
