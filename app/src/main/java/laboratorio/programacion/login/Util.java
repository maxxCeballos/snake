package laboratorio.programacion.login;

public class Util {

    // Database setup
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "accountDB";
    public static final String TABLE_NAME = "account";

    // Account table columns names
    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_HIGHSCORE = "highscore";

    // Sound
    public static boolean SOUND_ENABLE = true;

    // DB
    public static DAL db;

    // Account played
    public static Account accountPlayed;
}
