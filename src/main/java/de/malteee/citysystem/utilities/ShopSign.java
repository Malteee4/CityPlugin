package de.malteee.citysystem.utilities;

import com.destroystokyo.paper.MaterialTags;
import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopSign implements Listener {
    FileConfiguration config = CitySystem.getPlugin().getConfig();

    private HashMap<String, Boolean> item = new HashMap<String, Boolean>();
    private HashMap<String, List<String>> join = new HashMap<String, List<String>>();
    ArrayList<String> li = new ArrayList<String>();

    @EventHandler
    public void onSignPlace(SignChangeEvent e) {
        if(e.getLine(0).contains("|[Shop]|")) {
            if(e.getLine(1).contains("B") || e.getLine(1).contains("S")) {
                if(e.getLine(1).contains("1") || e.getLine(1).contains("2") || e.getLine(1).contains("3") || e.getLine(1).contains("4") || e.getLine(1).contains("5") || e.getLine(1).contains("6") || e.getLine(1).contains("7") || e.getLine(1).contains("8") || e.getLine(1).contains("9") || e.getLine(1).contains("0")) {
                    Location loc = new Location(e.getBlock().getLocation().getWorld(),e.getBlock().getLocation().getX(),e.getBlock().getLocation().getY()-1,e.getBlock().getLocation().getZ());
                    if(loc.getBlock().getType() == ) {
                        e.setLine(0, "§l§f|[Shop]|");
                        e.setLine(3, e.getPlayer().getName());
                        e.getPlayer().sendMessage("§lUm das Shopschild fertigzustellen linksklicke nun mit dem zu verkaufenden Item auf das Schild!");
                        item.put(e.getPlayer().getName(), true);
                    }else {
                        e.getPlayer().sendMessage("§cShopschild konnte nicht erstellt werden \nGrund: Unter dem Schild muss sich eine Kiste befinden!");
                    }
                }else {
                    e.getPlayer().sendMessage("§cShopschild konnte nicht erstellt werden \nGrund: Preis(e) fehlen!");
                }
            }else {
                e.getPlayer().sendMessage("§cShopschild konnte nicht erstellt werden \nGrund: B oder S in der 2. Zeile fehlen!");
            }

        }else {
        }

    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        CityPlayer cPlayer = CitySystem.getCityPlayer(p);
        if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if(e.getClickedBlock().getType() == (Material.OAK_SIGN) || e.getClickedBlock().getType() == (Material.BIRCH_WALL_SIGN) || e.getClickedBlock().getType() == (Material.BIRCH_SIGN) || e.getClickedBlock().getType() == (Material.OAK_WALL_SIGN)) {
                Sign sign = (Sign) e.getClickedBlock().getState();
                if(!(config.contains(sign.getLocation().toString()))) {
                    if(sign.getLine(0).contains("|[Shop]|") && !(sign.getLine(1).contains("B") && sign.getLine(1).contains("S"))) {
                        if(item.get(p.getName()) == true) {
                            if(p.getItemInHand() != null) {
                                ItemStack hand = p.getItemInHand();
                                config.set(sign.getLocation().toString(), hand);
                                sign.setLine(0, "§l"+hand.getAmount()+" "+sign.getLine(0));
                                sign.setLine(2, "§e" + hand.getType().toString());
                                sign.update();
                                p.sendMessage("§l§aShopschild erfolgreich fertiggestellt!");
                                item.put(p.getName(), false);
                                CitySystem.getPlugin().saveConfig();
                            }else {
                            }
                        }else {
                        }
                    }else if(sign.getLine(1).contains("B") && sign.getLine(1).contains("S")) {
                        p.sendMessage("§cSorry aber derzeit dürfen Shopschilder noch nicht eine kaufen und gleichzeitig auch eine verkaufen Funktion besitzen!");
                    }
                }else {
                    if(sign.getLine(0).contains("|[Shop]|")) {
                        if(config.contains(sign.getLocation().toString())) {
                            ItemStack item = config.getItemStack(sign.getLocation().toString());
                            String str = "0";
                            for(char c : sign.getLine(0).toCharArray()) {
                                if(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
                                    str = str + c;
                                }
                            }
                            int menge = Integer.parseInt(str);
                            if(menge == 0) {
                                menge = 1;
                            }
                            if(sign.getLine(1).contains("S")) {
                                String s = "0";
                                for(int i = 0; i < sign.getLine(1).length(); i++) {
                                    if(sign.getLine(1).contains("|")) {
                                        i = sign.getLine(1).indexOf("|") + 1;
                                        char ch = sign.getLine(1).charAt(i);
                                        if(ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9') {
                                            s = s + ch;
                                        }
                                    }else {
                                        char ch = sign.getLine(1).charAt(i);
                                        if(ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9') {
                                            s = s + ch;
                                        }
                                    }
                                }
                                int preis = Integer.parseInt(s);
                                if(preis == 0) {
                                    preis = 1;
                                }
                                item.setAmount(menge);
                                Location l = new Location(sign.getLocation().getWorld(),sign.getLocation().getBlockX(),sign.getLocation().getBlockY() - 1,sign.getLocation().getBlockZ());
                                Chest chest = (Chest) l.getBlock().getState();
                                if(KontoCommand.getKontostand(sign.getLine(3)) >= preis) {
                                    if(p.getInventory().containsAtLeast(item, menge)) {
                                        boolean wirklichVoll = true;
                                        if(chest.get.firstEmpty() == -1) {
                                            for(ItemStack i : chest.getInventory().getContents()) {
                                                if(i.getType() == item.getType() && i.getAmount() + menge <= 64) {
                                                    wirklichVoll = false;
                                                }
                                            }
                                        }else {
                                            wirklichVoll = false;
                                        }
                                        boolean pInvFrei = false;
                                        if(p.getInventory().firstEmpty() == -1) {
                                            for(ItemStack i : p.getInventory().getContents()) {
                                                if(i.getType() == item.getType() && i.getAmount() + menge <= 64) {
                                                    pInvFrei = true;
                                                }
                                            }
                                        }else {
                                            pInvFrei = true;
                                        }
                                        if((!(chest.getInventory().firstEmpty() == -1) || wirklichVoll == false) && pInvFrei == true) {
                                            item.setAmount(1);
                                            for(int n = 0;n<menge;n++) {
                                                p.getInventory().removeItem(item);
                                            }
                                            for(int n = 0;n<menge;n++) {
                                                chest.getBlockInventory().addItem(item);
                                            }
                                            item.setAmount(menge);
                                            config.set(p.getName() + ".kontostand", KontoCommand.getKontostand(p.getName()) + preis);
                                            config.set(sign.getLine(3) + ".kontostand", KontoCommand.getKontostand(sign.getLine(3)) - preis);
                                            CitySystem.getPlugin().saveConfig();
                                            if(!(Bukkit.getOnlinePlayers().toString().contains(sign.getLine(3)))) {
                                                if(config.contains(Bukkit.getPlayer(sign.getLine(3)).getName() + ".kontostand.einnahmen")) {
                                                    config.set(sign.getLine(3) + ".kontostand.einnahmen", config.getInt(sign.getLine(3) + ".kontostand.einnahmen") + preis);
                                                    CitySystem.getPlugin().saveConfig();
                                                }else {
                                                    config.set(sign.getLine(3) + ".kontostand.einnahmen", preis);
                                                    CitySystem.getPlugin().saveConfig();
                                                }
                                            }
                                            wirklichVoll = true;
                                            if(chest.getInventory().firstEmpty() == -1) {
                                                for(ItemStack i : chest.getInventory().getContents()) {
                                                    if(i.getType() == item.getType() && i.getAmount() + menge <= 64) {
                                                        wirklichVoll = false;
                                                    }
                                                }
                                            }else {
                                                wirklichVoll = false;
                                            }
                                            if(wirklichVoll == true) {
                                                if(Bukkit.getOnlinePlayers().toString().contains(sign.getLine(3))) {
                                                    Bukkit.getPlayer(sign.getLine(3)).sendMessage("�cDein Shop(" +item.getType().toString() + ") ist nun voll!");
                                                }else {
                                                    if(config.contains(sign.getLine(3) + ".shopnachrichten")) {
                                                        List<String> list = config.getStringList(sign.getLine(3));
                                                        list.add("�cDein Shop(" +item.getType().toString() + ") ist voll!");
                                                        join.put(sign.getLine(3),list);
                                                    }else {
                                                        li.add("�cDein Shop(" +item.getType().toString() + ") ist voll!");
                                                        join.put(sign.getLine(3),li);
                                                    }

                                                    config.set(sign.getLine(3), join.get(sign.getLine(3) + ".shopnachrichten"));
                                                    CitySystem.getPlugin().saveConfig();
                                                }
                                            }
                                        }else {
                                            p.sendMessage("�cShop voll!");
                                        }
                                    }
                                }else {
                                    p.sendMessage("�cDer Shopbesitzer hat leider nicht gen�gend Smaragde auf seinem Konto!");
                                }
                            }
                        }

                    }
                }
            }else {
            }
            /*  buying            */
        }else if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if(e.getClickedBlock().getType() == (Material.OAK_SIGN) || e.getClickedBlock().getType() == (Material.OAK_WALL_SIGN)|| e.getClickedBlock().getType() == (Material.BIRCH_WALL_SIGN) || e.getClickedBlock().getType() == (Material.BIRCH_SIGN)) {
                Sign sign = (Sign) e.getClickedBlock().getState();
                if(sign.getLine(0).contains("|[Shop]|")) {
                    if(config.contains(sign.getLocation().toString())) {
                        ItemStack item = config.getItemStack(sign.getLocation().toString());
                        String str = "0";
                        for(int i = 0; i < sign.getLine(0).length(); i++) {
                            char ch = sign.getLine(0).charAt(i);
                            if(ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9') {
                                str = str + ch;
                            }
                        }
                        int menge = Integer.parseInt(str);
                        if(menge == 0) {
                            menge = 1;
                        }
                        if(sign.getLine(1).contains("B")) {
                            String s = "0";
                            for(int i = 0; i < sign.getLine(1).length(); i++) {
                                char ch = sign.getLine(1).charAt(i);
                                if(ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9') {
                                    s = s + ch;
                                }else if(ch == '|') {
                                    i = 20;
                                }
                            }
                            int preis = Integer.parseInt(s);
                            if(preis == 0) {
                                preis = 1;
                            }
                            item.setAmount(menge);
                            Location l = new Location(sign.getLocation().getWorld(),sign.getLocation().getBlockX(),sign.getLocation().getBlockY() - 1,sign.getLocation().getBlockZ());
                            Chest chest = (Chest) l.getBlock().getState();
                            boolean wirklichVoll = true;
                            if(chest.getInventory.firstEmpty() == -1) {
                                for(ItemStack i : chest.getInventory().getContents()) {
                                    if(i.getType() == item.getType() && i.getAmount() + menge <= 64) {
                                        wirklichVoll = false;
                                    }
                                }
                            }else {
                                wirklichVoll = false;
                            }
                            boolean pInvFrei = false;
                            if(p.getInventory().firstEmpty() == -1) {
                                for(ItemStack i : p.getInventory().getContents()) {
                                    if(i.getType() == item.getType() && i.getAmount() + menge <= 64) {
                                        pInvFrei = true;
                                    }
                                }
                            }else {
                                pInvFrei = true;
                            }
                            if(p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), preis) && pInvFrei == true) {
                                if(chest.getInventory().containsAtLeast(item, menge) && wirklichVoll == false) {
                                    for(int n = 0;n<preis;n++) {
                                        p.getInventory().removeItem(new ItemStack(Material.EMERALD));
                                    }
                                    item.setAmount(1);
                                    for(int n = 0;n<menge;n++) {
                                        chest.getBlockInventory().removeItem(item);
                                    }
                                    item.setAmount(menge);
                                    p.getInventory().addItem(item);
                                    if(!(config.contains(sign.getLine(3) + ".kontostand"))) {
                                        config.set(sign.getLine(3) + ".kontostand", preis);
                                        CitySystem.getPlugin().saveConfig();
                                    }else {
                                        config.set(sign.getLine(3) + ".kontostand", KontoCommand.getKontostand(sign.getLine(3)) + preis);
                                        CitySystem.getPlugin().saveConfig();
                                    }
                                    if(!(Bukkit.getOnlinePlayers().toString().contains(sign.getLine(3)))) {
                                        if(config.contains(sign.getLine(3) + ".kontostand.einnahmen")) {
                                            config.set(sign.getLine(3) + ".kontostand.einnahmen", config.getInt(sign.getLine(3) + ".kontostand.einnahmen") + preis);
                                            CitySystem.getPlugin().saveConfig();
                                        }else {
                                            config.set(sign.getLine(3) + ".kontostand.einnahmen", preis);
                                            CitySystem.getPlugin().saveConfig();
                                        }
                                    }
                                    if(!(chest.getInventory().containsAtLeast(item, menge))) {
                                        if(Bukkit.getOnlinePlayers().toString().contains(sign.getLine(3))) {
                                            Bukkit.getPlayer(sign.getLine(3)).sendMessage("�cDein Shop(" +item.getType().toString() + ") ist nun leer!");
                                        }else {
                                            if(config.contains(sign.getLine(3) + ".shopnachrichten")) {
                                                List<String> list = config.getStringList(Bukkit.getPlayer(sign.getLine(3)).getName());
                                                list.add("�cDein Shop(" +item.getType().toString() + ") ist leer!");
                                                join.put(sign.getLine(3),list);
                                            }else {
                                                li.add("�cDein Shop(" +item.getType().toString() + ") ist leer!");
                                                join.put(sign.getLine(3),li);
                                            }

                                            config.set(sign.getLine(3), join.get(sign.getLine(3) + ".shopnachrichten"));
                                            CitySystem.getPlugin().saveConfig();
                                        }
                                    }
                                }else {
                                    p.sendMessage("�cShop leer!");
                                }
                            }else {
                                p.sendMessage("�cNicht gen�gend Smaragde!");
                            }
                        }
                    }

                }

            }
        }
    }

    @EventHandler
    public void onChestOpening(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = e.getClickedBlock();
            if(block.getType().equals(Material.CHEST)) {
                Location loc = new Location(block.getWorld(),block.getX(),block.getY()+1,block.getZ());
                if(loc.getBlock().getType().equals(Material.OAK_SIGN) || loc.getBlock().getType().equals(Material.OAK_WALL_SIGN)) {
                    Sign sign = (Sign) loc.getBlock().getState();
                    if(!(sign.getLine(3).equals(p.getName())) && sign.getLine(0).contains("|[Shop]|")) {
                        e.setCancelled(true);
                    }
                }

            }
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getBlock().getType() == Material.OAK_SIGN || e.getBlock().getType() == Material.OAK_WALL_SIGN) {
            Sign sign = (Sign) e.getBlock().getState();
            if(!(sign.getLine(3).equals(e.getPlayer().getName())) && config.contains(sign.getLocation().toString())) {
                e.setCancelled(true);
            }else if(config.contains(sign.getLocation().toString())){
                config.set(sign.getLocation().toString(), null);
                e.getPlayer().sendMessage("§eShopschild entfernt!");
            }
        }else if(e.getBlock().getType() == Material.CHEST){
            Location loc = new Location(e.getBlock().getLocation().getWorld(),e.getBlock().getLocation().getBlockX(),e.getBlock().getLocation().getBlockY() + 1,e.getBlock().getLocation().getBlockZ());
            if(loc.getBlock().getType() == Material.OAK_SIGN || loc.getBlock().getType() == Material.OAK_WALL_SIGN) {
                Sign sign = (Sign) loc.getBlock().getState();
                if(!(sign.getLine(3).equals(e.getPlayer().getName())) && config.contains(sign.getLocation().toString())) {
                    e.setCancelled(true);
                }
            }
        }
        ArrayList<Block> blocks = new ArrayList<Block>();
        blocks.add(e.getBlock().getRelative(); blocks.add(e.getBlock().getRelative(BlockFace.WEST));
        blocks.add(e.getBlock().getRelative(BlockFace.SOUTH));blocks.add(e.getBlock().getRelative(BlockFace.NORTH));
        for(Block b : blocks) {
            if(config.contains(b.getLocation().toString())) {
                e.setCancelled(true);
            }
        }
    }

}
