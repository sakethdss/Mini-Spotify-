import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

public class Playlist {
    private String name;
    private LinkedList<Song> songs;
    private boolean isPlaying;
    private Song currentSong;
    private ListIterator<Song> songIterator;
    private boolean loop;

    public Playlist(String name) {
        this.name = name;
        this.songs = new LinkedList<>();
        this.isPlaying = false;
        this.currentSong = null;
        this.songIterator = songs.listIterator();
        this.loop = false;
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song) {
        songs.add(song);
        System.out.println("Added: " + song.getTitle() + " to playlist: " + name);
        resetIterator();
    }

    public boolean removeSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().equals(title)) {
                songs.remove(song);
                System.out.println("Removed: " + title + " from playlist: " + name);
                resetIterator();
                return true;
            }
        }
        System.out.println("Song not found in playlist: " + name);
        return false;
    }

    public void shuffle() {
        Collections.shuffle(songs);
        System.out.println("Shuffled playlist: " + name);
        resetIterator();
    }

    public void printPlaylist() {
        System.out.println("Playlist: " + name);
        for (Song song : songs) {
            System.out.println(song);
        }
    }

    public LinkedList<Song> getSongs() {
        return songs;
    }

    public void play() {
        if (isPlaying) {
            System.out.println("Already playing: " + currentSong.getTitle());
            return;
        }

        if (!songIterator.hasNext()) {
            resetIterator();
        }

        if (songIterator.hasNext()) {
            currentSong = songIterator.next();
            isPlaying = true;
            System.out.println("Now playing: " + currentSong.getTitle());
        } else {
            System.out.println("No songs available in the playlist.");
        }
    }

    public void pause() {
        if (isPlaying) {
            System.out.println("Paused: " + currentSong.getTitle());
            isPlaying = false;
        } else {
            System.out.println("No song is currently playing.");
        }
    }

    public void next() {
        if (songIterator.hasNext()) {
            currentSong = songIterator.next();
            System.out.println("Now playing: " + currentSong.getTitle());
        } else {
            if (loop) {
                resetIterator();
                if (songIterator.hasNext()) {
                    currentSong = songIterator.next();
                    System.out.println("Now playing: " + currentSong.getTitle());
                }
            } else {
                System.out.println("End of the playlist.");
            }
        }
    }

    public void loop() {
        this.loop = !this.loop;
        System.out.println("Looping is now " + (this.loop ? "enabled." : "disabled."));
    }

    public void deletePlaylist() {
        songs.clear();
        System.out.println("Deleted playlist: " + name);
    }

    private void resetIterator() {
        this.songIterator = songs.listIterator();
    }
}
