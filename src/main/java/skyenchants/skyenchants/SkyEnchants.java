package skyenchants.skyenchants;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import skyenchants.skyenchants.model.Enchantment;
import skyenchants.skyenchants.utils.ReflectionsUtil;
import java.util.Set;

public final class SkyEnchants extends JavaPlugin implements Listener {

    @Getter
    private static SkyEnchants instance;

    @Getter
    public Set<Enchantment> enchantReflection;

    @Override
    public void onEnable() {
        loadConfig();
        instance = this;

        enchantReflection =
        new ReflectionsUtil<Enchantment>(this.getClass().getPackageName())
                .scanPathForSubTypes(Enchantment.class);

        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void loadConfig() {
        saveDefaultConfig();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        getEnchantReflection()
                .forEach(obj -> obj.getBreakBlock().accept(event.getBlock(), event.getPlayer()));
    }
}
