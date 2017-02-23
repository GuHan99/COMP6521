package project1;

/**
 * COMP6521 Project 1
 * Date:   2017-02-12
 * Author: Yuanwen Qin 40011473 q_yuanwe@encs.concordia.ca
 *         Chao Wang 25162130 chao_wa@encs.concordia.ca
 *         Haotao Lai 40018061 h_lai@encs.concordia.ca
 *         Anita Rai 40038782 an_rai@encs.concordia.ca
 */
public interface Configuration {

    // configure the path of the project directory root
    String TMP_FILE_PATH = "/Users/ERIC_LAI/IdeaProjects/COMP6521/NonRepeatedRadomNumGenerator";
    String INPUT_FILE_PATH = "/Users/ERIC_LAI/IdeaProjects/COMP6521/NonRepeatedRadomNumGenerator";
    // final output file name
    String OUTPUT_FILE_NAME = "output.txt";
    // input files name
    String INPUT_FILE_NAME_2 = "tt2.txt";
    String INPUT_FILE_NAME_1 = "tt1.txt";

    // some global variables
    int LENGTH_OF_EMP_ID = 7;
    int OBJECT_HEADER = 16;
    int ARRAR_HEADER = 24;
    int OBJECT_REFERENCE = 8;
    int OBJECT_OVERHEAD = OBJECT_HEADER + ARRAR_HEADER + OBJECT_REFERENCE;

}
