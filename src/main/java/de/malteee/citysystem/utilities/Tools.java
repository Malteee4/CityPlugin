package de.malteee.citysystem.utilities;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Tools {

    public static Location getLocFromString(String s, Plugin pl) {
        ArrayList<String> list = stringToList(s);
        Location loc = new Location(pl.getServer().getWorld(list.get(0)), Double.parseDouble(list.get(1)), Double.parseDouble(list.get(2)), Double.parseDouble(list.get(3)), Float.parseFloat(list.get(4)), Float.parseFloat(list.get(5)));
        return loc;
    }
    public static String locationToString(Location l) {
        return l.getWorld().getName() + ", " + l.getX() + ", " + l.getY() + ", " + l.getZ() + ", " + l.getYaw() + ", " + l.getPitch();
    }
    public static ArrayList<String> stringToList(String s) {
        ArrayList<String> result = new ArrayList<>();
        String str = "";
        for(char c : s.toCharArray()) {
            if(c == ',') {
                result.add(str);
                str = "";
            }else {
                str = str + c;
            }
        }result.add(str);
        return result;
    }
}
