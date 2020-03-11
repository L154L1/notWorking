import java.util.*;


public class MusicExchangeCenter {
    private ArrayList<User> users;
    private HashMap<String, Float> royalties;
    private ArrayList<Song> downloadedSongs;

    public MusicExchangeCenter() {
        users = new ArrayList<User>();
        royalties = new HashMap<String, Float>();
        downloadedSongs = new ArrayList<Song>();

    }

    public ArrayList<User> onlineUsers() {
        ArrayList<User> online = new ArrayList<User>();
        if (users.size() != 0) {
            for (User i : users) {
                if (i.isOnline()) {
                    online.add(i);
                }
            }
        }
        return online;
    }

    public ArrayList<Song> allAvailableSongs() {
        ArrayList<Song> available = new ArrayList<Song>();
        if (onlineUsers().size() != 0) {                                         //prev null point
            for (User i : onlineUsers()) {
                for (Song j : i.getSongList()) {
                    if (i.getSongList().size() != 0) {                          //prev null point
                        available.add(j);
                    }
                }
            }
        }
        return available;
    }

    public String toString() {
        return "Music Exchange Center (" + onlineUsers().size() + " users online, " + allAvailableSongs().size() + " songs available)";
    }

    public User userWithName(String s) {
        if (users.size() != 0){
            for (User i: users){
                if (i.getUserName() == s){
                    return i;
                }
            }
        }
        return null;
    }

    public void registerUser(User x){
        if (users.size() == 0){
            users.add(x);
        } else if (userWithName(x.getUserName())== null){           // if == null then x isnt in users already
            users.add(x);
        }
    }

    ArrayList<Song> availableSongsByArtist(String artist){
        ArrayList<Song> available = new ArrayList<Song>();
        if (allAvailableSongs().size() != 0){
            for (Song i: allAvailableSongs()){
                if (i.getArtist() == artist){
                    available.add(i);
                }
            }
        }
        return available;
    }

    Song getSong(String title, String ownerName){
        for (User u: onlineUsers()){
            if (u.getUserName() == ownerName){
                for (Song s: u.getSongList()){
                    if (s.getTitle() == title){
                        downloadedSongs.add(s);                                                                                 // adding to downloaded songs
                        if (royalties.containsKey(s.getArtist())){                                                              // updating royalties
                            royalties.replace(s.getArtist(), royalties.get(s.getArtist())+0.25f);
                        } else {
                            royalties.put(s.getArtist(), 0.25f);
                        }
                        return s;                                                                                               // returning s
                    }
                }
            }
        }
        return null;
    }

    public void displayRoyalties(){
        System.out.println(String.format("%-20s" + "%-40s", "Amount", "Artist"));
        System.out.println("_____________________________");
        for (Map.Entry<String, Float> e: royalties.entrySet()){
            String key = e.getKey();
            Float value = e.getValue();
            System.out.println(String.format("$" + "%-20.2f" + "%-40s", (double)e.getValue(), e.getKey()));
        }
    }

    public TreeSet<Song> uniqueDownloads(){
        TreeSet<Song> unique = new TreeSet<Song>();
        for (Song s: downloadedSongs){
            if (!unique.contains(s)){
                unique.add(s);
            }
        }
        return unique;
    }

    public ArrayList<Pair<Integer, Song>> songsByPopularity(){
        ArrayList<Pair<Integer, Song>> yourListOfPairs = new ArrayList<Pair<Integer, Song>>;
        int counter = 1;
        for (Song s: uniqueDownloads()){                                // getting unique songs from uniqueDownloads
            if (downloadedSongs.contains(s)){                           // getting the number of times something was downloaded from downloadedSongs
                if (yourListOfPairs
            }
            yourListOfPairs.add(Pair<s.>)
        }

        Collections.sort(yourListOfPairs, new Comparator<Pair<Integer, Song>>() {
            public int compare(Pair<Integer, Song> p1, Pair<Integer, Song> p2) {
                if (p1.Integer>p2){

                }
            }
        });
    }

    public ArrayList<User> getUsers() {return users;}
    public HashMap<String, Float> getRoyalties() {return royalties;}
    public ArrayList<Song> getDownloadedSongs() {return downloadedSongs;}

    public void setRoyalties(HashMap<String, Float> h) {royalties = h;}




}
