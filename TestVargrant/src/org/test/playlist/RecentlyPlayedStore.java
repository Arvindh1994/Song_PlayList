package org.test.playlist;

import java.util.*;

public class RecentlyPlayedStore {
    private int capacity;
    private Map<String, LinkedList<String>> store;

    public RecentlyPlayedStore(int capacity) {
        this.capacity = capacity;
        this.store = new HashMap<>();
    }

    public void addSong(String user, String song) {
        LinkedList<String> playlist = store.getOrDefault(user, new LinkedList<>());
        playlist.remove(song);
        playlist.addFirst(song);

        if (playlist.size() > capacity) {
            playlist.removeLast();
        }

        store.put(user, playlist);
    }

    public List<String> getRecentlyPlayed(String user) {
        LinkedList<String> playlist = store.getOrDefault(user, new LinkedList<>());
        return new ArrayList<>(playlist);
    }

    public static void main(String[] args) {
        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

        // User1 plays songs
        store.addSong("User1", "S1");
        store.addSong("User1", "S2");
        store.addSong("User1", "S3");
        System.out.println(store.getRecentlyPlayed("User1")); 

        // User1 plays a new song
        store.addSong("User1", "S4");
        System.out.println(store.getRecentlyPlayed("User1")); 
        
        // User2 plays songs
        store.addSong("User2", "S2");
        System.out.println(store.getRecentlyPlayed("User2")); 
        System.out.println(store.getRecentlyPlayed("User1")); // 

        // User1 plays a song again
        store.addSong("User1", "S1");
        System.out.println(store.getRecentlyPlayed("User1")); 
    }
}