package org.academiadecodigo.balboas.sound;

public enum SoundType {
    START("rocky.wav"),
    HIT("punch.wav");

    private String path;

    SoundType(String path) {
        this.path = "/resources/" + path;
    }

    public String getPath() {
        return path;
    }

}
