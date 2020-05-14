package tim03we.futureplots.commands;

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

import cn.nukkit.player.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Location;
import tim03we.futureplots.FuturePlots;
import tim03we.futureplots.utils.Plot;
import tim03we.futureplots.utils.PlotSettings;
import tim03we.futureplots.utils.Settings;

import static tim03we.futureplots.utils.Settings.max_plots;
import static tim03we.futureplots.utils.Settings.plotSize;

public class AutoCommand extends BaseCommand {

    public AutoCommand(String name, String description, String usage) {
        super(name, description, usage);
    }

    @Override
    public void execute(CommandSender sender, String command, String[] args) {
        if(sender instanceof Player) {
            if(FuturePlots.getInstance().claimAvailable((Player) sender) == -1 || FuturePlots.getInstance().claimAvailable((Player) sender) >= max_plots) {
                if(FuturePlots.provider.getHomes(sender.getName()).size() != Settings.max_plots) {
                    if(Settings.economy) {
                        if((FuturePlots.economyProvider.getMoney(sender.getName()) - new PlotSettings(((Player) sender).getLevel().getName()).getClaimPrice()) >= 0) {
                            FuturePlots.economyProvider.reduceMoney(sender.getName(), new PlotSettings(((Player) sender).getLevel().getName()).getClaimPrice());
                        } else {
                            sender.sendMessage(translate(true, "economy.no.money"));
                            return;
                        }
                    }
                    Plot plot = FuturePlots.provider.getNextFreePlot(FuturePlots.getInstance().getPlotByPosition(((Player) sender).getLocation()));
                    FuturePlots.provider.claimPlot(sender.getName(), plot);
                    Location pos = FuturePlots.getInstance().getPlotPosition(plot);
                    float x = pos.getX();
                    float y = pos.getY();
                    float z = pos.getZ();
                    ((Player) sender).teleport(Location.from(x += Math.floor(plotSize / 2), y += 1.5, z -= 1,  pos.getLevel()));
                    sender.sendMessage(translate(true, "plot.claim"));
                } else {
                    sender.sendMessage(translate(true, "plot.max"));
                }
            } else {
                sender.sendMessage(translate(true, "plot.max"));
            }
        }
    }
}
