package project1;

import java.io.*;

/**
 * COMP6521 Project 1
 * Date:   2017-02-12
 * Author: Yuanwen Qin 40011473 q_yuanwe@encs.concordia.ca
 *         Chao Wang 25162130 chao_wa@encs.concordia.ca
 *         Haotao Lai 40018061 h_lai@encs.concordia.ca
 *         Anita Rai 40038782 an_rai@encs.concordia.ca
 */
public class FileJoiner {

    public FileJoiner() {
    }

    /**
     * execute the two files join procedure
     */
    public void process(String fileName1, String fileName2, String outputFileName) {
        File file1 = new File(fileName1);
        File file2 = new File(fileName2);
        File outputFile = new File(outputFileName);
        try {
            joinFiles(file1, file2, outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * join two sorted files as the result file
     */
    public void joinFiles(File file1, File file2, File outputFile) throws IOException {
        // get two files' buffered reader
        BufferedReader r1 = new BufferedReader(new InputStreamReader(
                new FileInputStream(file1), "UTF-8"));
        BufferedReader r2 = new BufferedReader(new InputStreamReader(
                new FileInputStream(file2), "UTF-8"));
        // get two files' buffer
        FileBuffer fb1 = new FileBuffer(r1);
        FileBuffer fb2 = new FileBuffer(r2);
        outputFile.delete();
        // create a buffer writer
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFile, true), "UTF-8"));

        try {
            // loop while two files' buffer is not empty
            while (!fb1.empty() && !fb2.empty()) {

                // handle some error situation
                if (fb1.peek().length() < Configuration.LENGTH_OF_EMP_ID) {
                    fb1.pop();
                    continue;
                }
                if (fb2.peek().length() < Configuration.LENGTH_OF_EMP_ID) {
                    fb2.pop();
                    continue;
                }

                // get the EmpId
                String id1 = fb1.peek().substring(0, Configuration.LENGTH_OF_EMP_ID);
                String id2 = fb2.peek().substring(0, Configuration.LENGTH_OF_EMP_ID);

                // compare the two EmpId, if their are the same then just them together
                if (id1.equals(id2)) {
                    String line = fb1.peek() + fb2.pop();
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
                // if id1 is less than id2, move the file1's pointer to the next line
                else if (id1.compareTo(id2) < 0) {
                    fb1.pop();
                }
                // if id2 is less than id1, move the file2's pointer to the next line
                else {
                    fb2.pop();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fb1.close();
            fb2.close();
            bufferedWriter.close();
        }
    }
}
