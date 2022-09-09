package org.sobadfish.miniwalls;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import org.sobadfish.miniwalls.manager.RoomManager;
import org.sobadfish.miniwalls.panel.lib.AbstractFakeInventory;

/**
 *  __  __ _      ___      __    _ _
 * |  \/  (_)_ _ (_) \    / /_ _| | |
 * | |\/| | | ' \| |\ \/\/ / _` | | |
 * |_|  |_|_|_||_|_| \_/\_/\__,_|_|_|
 * @author Sobadfish
 * @date 2022/9/9
 */
public class MiniWallMain extends PluginBase {

    public static MiniWallMain miniWallMain;

    private static RoomManager roomManager;

    @Override
    public void onEnable() {
        miniWallMain = this;
        checkServer();
        this.getLogger().info(TextFormat.colorize('&',"&b __  __ _      ___      __    _ _"));
        this.getLogger().info(TextFormat.colorize('&',"&b|  \\/  (_)_ _ (_) \\    / /_ _| | |"));
        this.getLogger().info(TextFormat.colorize('&',"&b| |\\/| | | ' \\| |\\ \\/\\/ / _` | | |"));
        this.getLogger().info(TextFormat.colorize('&',"&b|_|  |_|_|_||_|_| \\_/\\_/\\__,_|_|_|"));
        this.getLogger().info(TextFormat.colorize('&',"&b"));
        this.getLogger().info(TextFormat.colorize('&',"&e正在加载MiniWall 迷你战墙插件 本版本为&av"+this.getDescription().getVersion()));
        this.getLogger().info(TextFormat.colorize('&',"&c插件作者:&b sobadfish(某吃瓜咸鱼) &aQQ：&e1586235767"));
        this.getLogger().info(TextFormat.colorize('&',"&c本插件为原创插件 部分源代码出处已标明原作者"));
        this.getLogger().info(TextFormat.colorize('&',"&a迷你战墙插件加载完成，祝您使用愉快"));

    }

    public static void sendMessageToConsole(String msg){
        sendMessageToObject(msg,null);
    }

    public static String getTitle(){
        return TextFormat.colorize('&',miniWallMain.getConfig().getString("title"));
    }

    public static String getScoreBoardTitle(){
        return TextFormat.colorize('&',miniWallMain.getConfig().getString("scoreboard-title","&f[&a迷你战墙&f]"));
    }

    public static void sendTipMessageToObject(String msg,Object o){
        String message = TextFormat.colorize('&',msg);
        if(o != null){
            if(o instanceof Player){
                if(((Player) o).isOnline()) {
                    ((Player) o).sendMessage(message);
                    return;
                }
            }
        }
        miniWallMain.getLogger().info(message);

    }


    public static void sendMessageToObject(String msg,Object o){
        String message = TextFormat.colorize('&',getTitle()+" &b>>&r "+msg);
        sendTipMessageToObject(message,o);
    }

    public static void sendSubTitle(String msg,Player o){
        String message = TextFormat.colorize('&',msg);
        if(o != null){
            if(o.isOnline()) {
                o.setSubtitle(message);
            }
        }else{
            miniWallMain.getLogger().info(message);
        }
    }

    public static void sendTitle(String msg,int time,Player o){
        String message = TextFormat.colorize('&',msg);
        if(o != null){
            if(o.isOnline()) {
                o.sendTitle(message,null,0,time,0);
            }
        }else{
            miniWallMain.getLogger().info(message);
        }
    }

    public static void sendTip(String msg,Player o){
        String message = TextFormat.colorize('&',msg);
        if(o != null){
            if(o.isOnline()) {
                o.sendTip(message);
            }
        }else{
            miniWallMain.getLogger().info(message);
        }
    }

    private void checkServer(){
        boolean ver = false;
        //双核心兼容
        try {
            Class<?> c = Class.forName("cn.nukkit.Nukkit");
            c.getField("NUKKIT_PM1E");
            ver = true;

        } catch (ClassNotFoundException | NoSuchFieldException ignore) { }
        try {
            Class<?> c = Class.forName("cn.nukkit.Nukkit");
            c.getField("NUKKIT").get(c).toString().equalsIgnoreCase("Nukkit PetteriM1 Edition");
            ver = true;
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException ignore) {
        }

        AbstractFakeInventory.IS_PM1E = ver;
        if(ver){
            sendMessageToConsole("&e当前核心为 Nukkit PM1E");
        }else{
            sendMessageToConsole("&e当前核心为 Nukkit");
        }
    }

    public enum UiType{
        /**
         * auto: 自动
         *
         * packet: GUI界面
         *
         * ui: 箱子界面
         * */
        AUTO,PACKET,UI
    }
}
