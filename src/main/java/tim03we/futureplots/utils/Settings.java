package tim03we.futureplots.utils;

/*
 * This software is distributed under "GNU General Public License v3.0".
 * This license allows you to use it and/or modify it but you are not at
 * all allowed to sell this plugin at any cost. If found doing so the
 * necessary action required would be taken.
 *
 * GunGame is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License v3.0 for more details.
 *
 * You should have received a copy of the GNU General Public License v3.0
 * along with this program. If not, see
 * <https://opensource.org/licenses/GPL-3.0>.
 */

import cn.nukkit.utils.Config;
import tim03we.futureplots.FuturePlots;

import java.io.File;
import java.util.ArrayList;

public class Settings {


    public static ArrayList<String> levels = new ArrayList<>();
    public static int wallBlock;
    public static int roadBlock;
    public static int roadWidth;
    public static int groundHeight;
    public static int plotSize;
    public static int bottomBlock;
    public static int plotFloorBlock;
    public static int plotFillBlock;

    public static String provider;
    public static String language;
    public static int max_plots;

    public static void init() {
        Config config = FuturePlots.getInstance().getConfig();
        File folder = new File(FuturePlots.getInstance().getDataFolder() + "/worlds/");
        for(File file : folder.listFiles()) {
            levels.add(file.getName().replace(".yml", ""));
        }
        wallBlock = config.getInt("default-settings.wallBlock");
        roadBlock = config.getInt("default-settings.roadBlock");
        roadWidth = config.getInt("default-settings.roadWidth");
        groundHeight = config.getInt("default-settings.groundHeight");
        plotSize = config.getInt("default-settings.plotSize");
        bottomBlock = config.getInt("default-settings.bottomBlock");
        plotFloorBlock = config.getInt("default-settings.plotFloorBlock");
        plotFillBlock = config.getInt("default-settings.plotFillBlock");
        provider = config.getString("provider").toLowerCase();
        language = config.getString("lang").toLowerCase();
        max_plots = config.getInt("max-plots");
    }

}