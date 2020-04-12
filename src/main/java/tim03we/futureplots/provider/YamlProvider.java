package tim03we.futureplots.provider;

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
import tim03we.futureplots.utils.Plot;

import java.util.ArrayList;

public class YamlProvider implements Provider {

    @Override
    public void claimPlot(String username, Plot plot) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        config.set(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".owner", username);
        config.set(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".helpers", new ArrayList<String>());
        config.set(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".denied", new ArrayList<String>());
        config.set(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".flags", new ArrayList<String>());
        config.save();
    }

    @Override
    public void deletePlot(Plot plot) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        config.remove(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ());
        config.save();
    }

    @Override
    public String getHelpers(Plot plot) {
        StringBuilder sb = new StringBuilder();
        for (String list : new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML).getStringList(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".helpers")) {
            sb.append(list + ", ");
        }
        return sb.toString();
    }

    @Override
    public String getDenied(Plot plot) {
        StringBuilder sb = new StringBuilder();
        for (String list : new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML).getStringList(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".denied")) {
            sb.append(list + ", ");
        }
        return sb.toString();    }

    @Override
    public boolean isHelper(String username, Plot plot) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        for (String list : config.getStringList(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".helpers")) {
            if(list.toLowerCase().equals(username.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addHelper(String username, Plot plot) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        ArrayList<String> helpers = new ArrayList<>();
        for (String list : config.getStringList(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".helpers")) {
            helpers.add(list);
        }
        helpers.add(username.toLowerCase());
        config.set(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".helpers", helpers);
        config.save();
    }

    @Override
    public void removeHelper(String username, Plot plot) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        ArrayList<String> helpers = new ArrayList<>();
        for (String list : config.getStringList(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".helpers")) {
            helpers.add(list);
        }
        helpers.remove(username.toLowerCase());
        config.set(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".helpers", helpers);
        config.save();
    }

    @Override
    public boolean isDenied(String username, Plot plot) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        for (String list : config.getStringList(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".denied")) {
            if(list.toLowerCase().equals(username.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addDenied(String username, Plot plot) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        ArrayList<String> denied = new ArrayList<>();
        for (String list : config.getStringList(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".denied")) {
            denied.add(list);
        }
        denied.add(username.toLowerCase());
        config.set(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".denied", denied);
        config.save();
    }

    @Override
    public void removeDenied(String username, Plot plot) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        ArrayList<String> denied = new ArrayList<>();
        for (String list : config.getStringList(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".denied")) {
            denied.add(list);
        }
        denied.remove(username.toLowerCase());
        config.set(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".denied", denied);
        config.save();
    }

    @Override
    public boolean isOwner(String username, Plot plot) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        return config.getString(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".owner").equals(username);
    }

    @Override
    public boolean hasOwner(Plot plot) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        return config.exists(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ());
    }

    @Override
    public boolean hasHome(String username, int homeNumber) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        ArrayList<String> homes = new ArrayList<>();
        for (String list : config.getAll().keySet()) {
            if(config.getString(list + ".owner").equals(username)) {
                homes.add(list);
            }
        }
        if (homes.get(homeNumber - 1) != null) {
            return true;
        }
        return false;
    }

    @Override
    public String getPlotId(String username, int homeNumber) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        ArrayList<String> homes = new ArrayList<>();
        for (String list : config.getAll().keySet()) {
            if(config.getString(list + ".owner").equals(username)) {
                homes.add(list);
            }
        }
        if(homes.size() > 0) {
            return homes.get(homeNumber - 1);
        }
        return null;
    }

    @Override
    public ArrayList<String> getHomes(String username, String world) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        ArrayList<String> homes = new ArrayList<>();
        for (String list : config.getAll().keySet()) {
            if(config.getString(list + ".owner").equals(username)) {
                homes.add(list);
            }
        }
        return homes;
    }

    @Override
    public String getPlotName(Plot plot) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        return config.getString(plot.getLevelName() + ";" + plot.getX() + ";" + plot.getZ() + ".owner");
    }

    @Override
    public Plot getNextFreePlot(Plot plot) {
        Config config = new Config(FuturePlots.getInstance().getDataFolder() + "/plots.yml", Config.YAML);
        int limitXZ = 0;
        for(int i = 0; limitXZ <= 0 || i < limitXZ; i++) {
            ArrayList<String> existing = new ArrayList<>();
            for (String list : config.getAll().keySet()) {
                String[] ex = list.split(";");
                if(Math.abs(Integer.parseInt(ex[1])) == i && Math.abs(Integer.parseInt(ex[2])) <= i) {
                    existing.add(list);
                } else if(Math.abs(Integer.parseInt(ex[2])) == i && Math.abs(Integer.parseInt(ex[1])) <= i) {
                    existing.add(list);
                }
            }
            if(existing.size() == Math.max(1, 8 * i)) {
                continue;
            }
            if(FuturePlots.getInstance().findEmptyPlotSquared(0, i, existing) != null) {
                String[] ex = FuturePlots.getInstance().findEmptyPlotSquared(0, i, existing).split(";");
                return new Plot(Integer.parseInt(ex[0]), Integer.parseInt(ex[1]), plot.getLevelName());
            }
            for (int a = 1; a < i; a++) {
                if(FuturePlots.getInstance().findEmptyPlotSquared(a, i, existing) != null) {
                    String[] ex = FuturePlots.getInstance().findEmptyPlotSquared(a, i, existing).split(";");
                    return new Plot(Integer.parseInt(ex[0]), Integer.parseInt(ex[1]), plot.getLevelName());
                }
            }
            if(FuturePlots.getInstance().findEmptyPlotSquared(i, i, existing) != null) {
                String[] ex = FuturePlots.getInstance().findEmptyPlotSquared(i, i, existing).split(";");
                return new Plot(Integer.parseInt(ex[0]), Integer.parseInt(ex[1]), plot.getLevelName());
            }
        }
        return null;
    }
}