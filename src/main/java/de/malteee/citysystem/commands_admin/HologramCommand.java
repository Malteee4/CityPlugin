package de.malteee.citysystem.commands_admin;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.utilities.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class HologramCommand implements CommandExecutor, Listener{

    FileConfiguration config = CitySystem.getPlugin().getConfig();

    String text = "";
    boolean first;
    boolean second;
    boolean third;

    int deleteZaehler;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p) {
            if (!(p.hasPermission("CitySystem.hologram"))) return false;
            if (args.length < 1) return false;
            if(args[0].equalsIgnoreCase("delete")) {
                Location loc = p.getLocation();
                Location l = new Location(Bukkit.getWorld("world"), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(),0,0);
                for(Entity entity : Bukkit.getWorld("world").getEntities()) {
                    Location lE = new Location(Bukkit.getWorld("world"), entity.getLocation().getBlockX(), entity.getLocation().getBlockY(), entity.getLocation().getBlockZ(),0,0);
                    if(lE.getX() == l.getX() && lE.getY() == l.getY() && lE.getZ() == l.getZ() && entity.getType().equals(EntityType.ARMOR_STAND)) {
                        entity.remove();
                        deleteZaehler++;
                    }
                }
                if(deleteZaehler == 0) {
                    p.sendMessage("§cEs wurde an dieser Stelle kein hologramm gefunden!");
                }else if(deleteZaehler==1) {
                    p.sendMessage("§a1 Objekt entfernt!");
                }else {
                    p.sendMessage("§a"+deleteZaehler+" Objekte entfernt!");
                }
                deleteZaehler = 0;
            }else {
                try {
                    double hoehe = Double.parseDouble(args[0]);
                    first = true;
                    for (int i = 1; i < args.length; i++) {
                        text = text + " " + args[i];
                    }
                    text = text.replace("&", "§").trim();
                    /*if(text.contains("[fett]")) {
                        text = text.replace("[fett] ", "");text = text.replace("[fett]", "");
                        text = "§l"+text;
                    }
                    if(text.contains("[kursiv]")) {
                        text = text.replace("[kursiv] ", "");text = text.replace("[kursiv]", "");
                        text = "§o"+text;
                    }
                    switch(farbe) {
                        case "hellgrün":
                            text = "§a"+text;break;
                        case "dunkelgrün":
                            text = "§2"+text;break;
                        case "hellblau":
                            text = "§9"+text;break;
                        case "dunkelblau":
                            text = "§1"+text;break;
                        case "dunkelrot":
                            text = "§4"+text;break;
                        case "hellrot":
                            text = "§c"+text;break;
                        case "orange":
                            text = "§6"+text;break;
                        case "gelb":
                            text = "§e"+text;break;
                        case "dunkeltürkis":
                            text = "§3"+text;break;
                        case "helltürkis":
                            text = "§b"+text;break;
                        case "rosa":
                            text = "§d"+text;break;
                        case "lila":
                            text = "§5"+text;break;
                        case "hellgrau":
                            text = "§7"+text;break;
                        case "dunkelgrau":
                            text = "§8"+text;break;
                        case "schwarz":
                            text = "§0"+text;break;
                        case "weiß":
                            text = "§f"+text;break;
                    }*/
                    new Hologram(text,p,hoehe);
                    text = "";
                }catch (Exception e) {
                    p.sendMessage("§cError");
                }
            }
        }
        return false;
    }
    @EventHandler
    public void onArmorstandClick(PlayerArmorStandManipulateEvent e) {
        if(e.getRightClicked().hasGravity() == false) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onArmorstandDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof ArmorStand) {
            ArmorStand armor = (ArmorStand) e.getEntity();
            if(armor.getHealth() == 200) {
                e.setCancelled(true);
            }
        }
    }

}

