package skyenchants.skyenchants.types;

import org.bukkit.Material;
import skyenchants.skyenchants.model.Enchantment;

import java.io.IOException;

public class LootFinder extends Enchantment {

    public LootFinder() throws IOException {
        super("LootFinder", 99.9,
                ((block, player) -> {
            block.setType(Material.ACACIA_FENCE);
            player.sendMessage("Worked!");
        }), config -> {
            config.set("Enchant.Name", "LootFinder");
            config.set("Enchant.MaxChance", 99.9);
        });
    }
}
