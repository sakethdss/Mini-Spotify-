import java.util.ArrayList;
import java.util.Scanner;

public class MusicPlayer {
    private static ArrayList<Album> albums = new ArrayList<>();
    private static ArrayList<Playlist> playlists = new ArrayList<>();
    private static Playlist favoritesPlaylist = new Playlist("Favorites");

    public static void main(String[] args) {
        // Initialize sample albums and songs
        initializeSampleAlbumsAndSongs();

        // User interface
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 0:
                    System.out.println("Exiting the music player app.");
                    quit = true;
                    break;
                case 1:
                    printAlbums();
                    break;
                case 2:
                    createPlaylist(scanner);
                    break;
                case 3:
                    viewPlaylists();
                    break;
                case 4:
                    favoriteSong(scanner);
                    break;
                case 5:
                    shufflePlaylist(scanner);
                    break;
                case 6:
                    viewFavoritesPlaylist();
                    break;
                case 7:
                    controlPlaylist(scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\nMusic Player Menu:");
        System.out.println("0 - Quit");
        System.out.println("1 - View all albums");
        System.out.println("2 - Create a new playlist");
        System.out.println("3 - View all playlists");
        System.out.println("4 - Mark a song as favorite");
        System.out.println("5 - Shuffle a playlist");
        System.out.println("6 - View Favorites Playlist");
        System.out.println("7 - Control Playlist (Play, Pause, Next, Loop, Delete)");
    }

    private static void controlPlaylist(Scanner scanner) {
        System.out.println("Select the playlist number to control:");
        viewPlaylists();
        int playlistChoice = scanner.nextInt();
        scanner.nextLine();

        if (playlistChoice > 0 && playlistChoice <= playlists.size()) {
            Playlist selectedPlaylist = playlists.get(playlistChoice - 1);

            boolean controlling = true;
            while (controlling) {
                printControlMenu();
                int action = scanner.nextInt();
                scanner.nextLine();

                switch (action) {
                    case 0:
                        controlling = false;
                        break;
                    case 1:
                        selectedPlaylist.play();
                        break;
                    case 2:
                        selectedPlaylist.pause();
                        break;
                    case 3:
                        selectedPlaylist.next();
                        break;
                    case 4:
                        selectedPlaylist.loop();
                        break;
                    case 5:
                        System.out.println("Enter the song title to delete:");
                        String title = scanner.nextLine();
                        selectedPlaylist.removeSong(title);
                        break;
                    case 6:
                        selectedPlaylist.deletePlaylist();
                        playlists.remove(selectedPlaylist);
                        controlling = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Invalid playlist selection.");
        }
    }

    private static void printControlMenu() {
        System.out.println("\nPlaylist Control Menu:");
        System.out.println("0 - Exit Playlist Control");
        System.out.println("1 - Play");
        System.out.println("2 - Pause");
        System.out.println("3 - Next Song");
        System.out.println("4 - Loop Current Song");
        System.out.println("5 - Delete Song from Playlist");
        System.out.println("6 - Delete Entire Playlist");
    }

    private static void printAlbums() {
        System.out.println("Available Albums:");
        for (int i = 0; i < albums.size(); i++) {
            System.out.println((i + 1) + ": " + albums.get(i).getName() + " by " + albums.get(i).getArtist());
        }
    }

    private static void initializeSampleAlbumsAndSongs() {
        Album album1 = new Album("The Bends", "Radiohead");
        album1.addSong("High and Dry", 4.17);
        album1.addSong("Fake Plastic Trees", 4.50);
        album1.addSong("Just", 3.54);
        albums.add(album1);

        Album album2 = new Album("The Eminem Show", "Eminem");
        album2.addSong("Without Me", 4.50);
        album2.addSong("Cleaning Out My Closet", 4.57);
        album2.addSong("Superman", 5.50);
        albums.add(album2);

        Album album3 = new Album("Currents", "Tame Impala");
        album3.addSong("Let It Happen", 7.47);
        album3.addSong("The Less I Know the Better", 3.36);
        album3.addSong("New Person, Same Old Mistakes", 6.02);
        albums.add(album3);
    }

    private static void createPlaylist(Scanner scanner) {
        System.out.println("Enter the name of the new playlist:");
        String playlistName = scanner.nextLine();
        Playlist playlist = new Playlist(playlistName);

        boolean addingSongs = true;
        while (addingSongs) {
            System.out.println("Enter the album number to view songs and add to playlist (or 0 to finish):");
            printAlbums();
            int albumChoice = scanner.nextInt();
            scanner.nextLine();

            if (albumChoice == 0) {
                addingSongs = false;
                break;
            }

            Album selectedAlbum = albums.get(albumChoice - 1);
            selectedAlbum.printSongs();

            System.out.println("Enter the song number to add to playlist (or 0 to skip):");
            int songChoice = scanner.nextInt();
            scanner.nextLine();

            if (songChoice == 0) continue;

            Song selectedSong = selectedAlbum.getSongs().get(songChoice - 1);
            playlist.addSong(selectedSong);
        }

        playlists.add(playlist);
        System.out.println("New playlist created: " + playlistName);
    }

    private static void viewPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists available.");
        } else {
            System.out.println("Playlists:");
            for (int i = 0; i < playlists.size(); i++) {
                System.out.println((i + 1) + ": " + playlists.get(i).getName());
                playlists.get(i).printPlaylist();
            }
        }
    }

    private static void favoriteSong(Scanner scanner) {
        System.out.println("Select the album number to choose a song to mark as favorite:");
        printAlbums();
        int albumChoice = scanner.nextInt();
        scanner.nextLine();

        Album selectedAlbum = albums.get(albumChoice - 1);
        selectedAlbum.printSongs();

        System.out.println("Enter the song number to mark as favorite:");
        int songChoice = scanner.nextInt();
        scanner.nextLine();

        Song selectedSong = selectedAlbum.getSongs().get(songChoice - 1);
        selectedSong.toggleFavorite();
        System.out.println("Song marked as favorite: " + selectedSong.getTitle());
    }

    private static void shufflePlaylist(Scanner scanner) {
        System.out.println("Select the playlist number to shuffle:");
        viewPlaylists();
        int playlistChoice = scanner.nextInt();
        scanner.nextLine();

        if (playlistChoice > 0 && playlistChoice <= playlists.size()) {
            Playlist selectedPlaylist = playlists.get(playlistChoice - 1);
            selectedPlaylist.shuffle();
            selectedPlaylist.printPlaylist();
        } else {
            System.out.println("Invalid playlist selection.");
        }
    }

    private static void viewFavoritesPlaylist() {
        createFavoritesPlaylist();
        if (favoritesPlaylist.getSongs().isEmpty()) {
            System.out.println("No favorite songs found.");
        } else {
            favoritesPlaylist.printPlaylist();
        }
    }

    private static void createFavoritesPlaylist() {
        favoritesPlaylist = new Playlist("Favorites");

        for (Album album : albums) {
            for (Song song : album.getSongs()) {
                if (song.isFavorite()) {
                    favoritesPlaylist.addSong(song);
                }
            }
        }
    }
}
