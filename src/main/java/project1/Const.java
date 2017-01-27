package project1;

/**
 * Created by ERIC_LAI on 2017-01-27.
 */
public class Const {

    public static final String PROJECT_ROOT = "/Users/ERIC_LAI/IdeaProjects/COMP6521/";

    public static final int READ_NOTHING = -1;

    public static final int BLOCK_TUPLES = 10;
    public static final int BLOCK_SIZE = 4;                 // 4KB
    public static final int MAIN_MEMORY_SIZE = 1 * 1024;    // 1MB
    public static final int BLOCK_NUM = MAIN_MEMORY_SIZE / BLOCK_SIZE;
    public static final int BUFFER_SIZE = BLOCK_NUM * BLOCK_TUPLES;
}
