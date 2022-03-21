package laboratorio.programacion.login;

/**
 * ACCOUNT MODEL TO REPRESENT ON DATABASE
 */

public class Account implements Comparable<Account>{
    private int id;
    private String username;
    private String password;
    private int highscore;

    public Account(){};

    public Account(String username, String password, int highscore) {
        this.username = username;
        this.password = password;
        this.highscore = highscore;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }


    @Override
    public int compareTo(Account account) {
        return 0;
    }
}
