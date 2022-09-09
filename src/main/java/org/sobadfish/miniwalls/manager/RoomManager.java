package org.sobadfish.miniwalls.manager;

import org.sobadfish.miniwalls.MiniWallMain;
import org.sobadfish.miniwalls.room.config.GameRoomConfig;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Sobadfish
 * @date 2022/9/9
 */
public class RoomManager {

    public Map<String, GameRoomConfig> roomConfig;


    private RoomManager(Map<String, GameRoomConfig> roomConfig){
        this.roomConfig = roomConfig;
    }


    public static RoomManager initGameRoomConfig(File file){
        Map<String, GameRoomConfig> map = new LinkedHashMap<>();
        if(file.isDirectory()){
            File[] dirNameList = file.listFiles();
            if(dirNameList != null && dirNameList.length > 0) {
                for (File nameFile : dirNameList) {
                    if(nameFile.isDirectory()){
                        String roomName = nameFile.getName();
                        GameRoomConfig roomConfig = GameRoomConfig.getGameRoomConfigByFile(roomName,nameFile);
                        if(roomConfig != null){
                            MiniWallMain.sendMessageToConsole("&a加载房间 "+roomName+" 完成");
                            map.put(roomName,roomConfig);

                        }else{
                            MiniWallMain.sendMessageToConsole("&c加载房间 "+roomName+" 失败");

                        }
                    }
                }
            }
        }
        return new RoomManager(map);
    }


}
