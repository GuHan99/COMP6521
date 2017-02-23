package project1;

import java.io.IOException;

/**
 * COMP6521 Project 1
 * Date:   2017-02-12
 * Author: Yuanwen Qin 40011473 q_yuanwe@encs.concordia.ca
 *         Chao Wang 25162130 chao_wa@encs.concordia.ca
 *         Haotao Lai 40018061 h_lai@encs.concordia.ca
 *         Anita Rai 40038782 an_rai@encs.concordia.ca
 */
public class EntryPoint {

    /**
     * entrance of the program， basic idea is sorting the two unsorted files respectively and join
     * them together according to the EmpId
     */
    public static void main(String[] args) throws IOException {
        ExternalSortor externalSortor = new ExternalSortor();
        FileJoiner fileJoiner = new FileJoiner();
        long time1 = System.currentTimeMillis();
        fileJoiner.process(
                externalSortor.process(Configuration.INPUT_FILE_NAME_1),
                externalSortor.process(Configuration.INPUT_FILE_NAME_2),
                Configuration.OUTPUT_FILE_NAME);
        long time2 = System.currentTimeMillis();
        System.out.println("total time: " + (time2 - time1));

    }
}
