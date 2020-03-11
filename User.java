import java.util.ArrayList;
import java.util.HashMap;

public class User {
  private String userName;
  private boolean online;
  private ArrayList<Song> songList;

  public User() {
    this("");
  }

  public User(String u) {
    userName = u;
    online = false;
    songList = new ArrayList<Song>();
  }

  public void addSong(Song s) {
    songList.add(s);
    s.setOwner(this);
  }

  public int totalSongTime() {
    int sum = 0;
    if (songList.size() != 0) {        //prevent null pointer
      for (Song i : songList) {
        sum += i.getDuration();
      }
    }

    return sum;
  }

  public void register(MusicExchangeCenter m) {
    m.registerUser(this);
  }

  public void logon() {
    online = true;
  }

  public void logoff() {
    online = false;
  }

  public ArrayList<String> requestCompleteSonglist(MusicExchangeCenter m) {
    ArrayList<String> complete = new ArrayList<String>();
    int songNumber = 1;
    complete.add(String.format("%-43s", "TITLE") + String.format("%-31s", "ARTIST") + String.format("%-9s", "TIME") + String.format("%-20s", "OWNER"));
    if (m.getUsers().size() != 0) {
      for (User i : m.getUsers()) {
        if (i.getSongList().size() != 0) {
          for (Song j : i.getSongList()) {
            complete.add(String.format("%-2s" + "." + "%-40s" + "%-30s" + "%2d" + ":" + "%02d" + "     " + "%-20s", songNumber, j.getTitle(), j.getArtist(), j.getMinutes(), j.getSeconds(), j.getOwner().getUserName()));
            songNumber++;
          }
        }
      }
    }
    //for (String s: complete){System.out.println(s);}
    return complete;
  }

  ArrayList<String> requestSonglistByArtist(MusicExchangeCenter m, String artist) {
    ArrayList<String> byArtist = new ArrayList<String>();
    int songNumber = 1;
    byArtist.add(String.format("%-43s", "TITLE") + String.format("%-31s", "ARTIST") + String.format("%-9s", "TIME") + String.format("%-20s", "OWNER"));
    if (m.getUsers().size() != 0) {
      for (User i : m.getUsers()) {
        if (i.getSongList().size() != 0) {
          for (Song j : i.getSongList()) {
            if (j.getArtist() == artist) {
              byArtist.add(String.format("%-2s" + "." + "%-40s" + "%-30s" + "%2d" + ":" + "%02d" + "     " + "%-20s", songNumber, j.getTitle(), j.getArtist(), j.getMinutes(), j.getSeconds(), j.getOwner().getUserName()));
              songNumber++;
            }
          }
        }
      }
    }
    // for (String s: byArtist){System.out.println(s);}
    return byArtist;
  }

  public void downloadSong(MusicExchangeCenter m, String title, String ownerName) {
    if (m.getSong(title, ownerName) != null) {
      addSong(m.getSong(title, ownerName));
      //System.out.println("this was done");------------------------------------------------------------------------------------------------------------------------------------------------
    }
  }
  
  public String getUserName() { return userName; }
  public boolean isOnline() { return online; }
  public ArrayList<Song> getSongList() { return songList; }
  
  public String toString()  {
    String s = "" + userName + ": " + songList.size() + " songs (";
    if (!online) s += "not ";
    return s + "online)";
  }


}
