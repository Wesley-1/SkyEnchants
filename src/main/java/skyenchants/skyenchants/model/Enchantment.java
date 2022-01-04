package skyenchants.skyenchants.model;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import skyenchants.skyenchants.SkyEnchants;

import java.io.File;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Getter /* This is lombok */
@Setter /* This is lombok */

public class Enchantment {

    private final String enchantmentName;
    private final Double maxChance;
    private final BiConsumer<Block, Player> breakBlock;
    private final Consumer<FileConfiguration> configuration;
    private File configFile;

    /**
     *
     * @param enchantmentName This is the enchants name and what it'll be referred to as.
     * @param maxChance This is the maxChance for this enchantment. It will be divided by each level.
     * @param breakBlock This is the consumer, this consumer will take location and player as a param.
     * @param configuration This is the consumer so it's known what is needed to be loaded.
     *
     * Constructor
     *
     */
    public Enchantment(String enchantmentName,
                       Double maxChance,
                       BiConsumer<Block, Player> breakBlock,
                       Consumer<FileConfiguration> configuration) throws IOException {

        this.enchantmentName = enchantmentName;
        this.maxChance = maxChance;
        this.breakBlock = breakBlock;
        this.configFile = new File(SkyEnchants.getInstance().getDataFolder() + "/enchants/" + enchantmentName + ".yml");
        this.configuration = configuration;
        loadEnchantmentConfig();
    }

    /**
     *
     * @throws IOException
     *
     * This loads the enchants folder and the enchantment config.
     *
     */
    public void loadEnchantmentConfig() throws IOException {

        File folder = new File(SkyEnchants.getInstance().getDataFolder() + "/enchants");

        if(!folder.exists()) {
            folder.mkdirs();
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        configuration.accept(config);
        config.save(configFile);


    }
}
