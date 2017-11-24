package org.academiadecodigo.balboas.sound;

import java.util.HashMap;

public class SoundManager {

    private HashMap<SoundType, Sound> soundMap;

    public SoundManager() {

        soundMap = new HashMap<>();
        for (SoundType type : SoundType.values()) {
            soundMap.put(type, new Sound(type.getPath()));
        }
    }

    public void play(SoundType type) {

        Sound sound;
        if ((sound = soundMap.get(type)) != null) {
            sound.play(true);
        }
    }

    public void stop(SoundType type) {

        Sound sound;
        if ((sound = soundMap.get(type)) != null) {
            sound.stop();
        }
    }

    public void loop(SoundType type) {
        Sound sound;
        if ((sound = soundMap.get(type)) != null) {
            sound.loopIndef();
        }
    }

}
