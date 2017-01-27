package project1;

import java.io.*;

/**
 * Created by ERIC_LAI on 2017-01-27.
 */
public class ExternalSorting {

    private BufferedReader br;
    private BufferedWriter bw;
    private Employee[] buffer;
    private String outputPath;

    public ExternalSorting(String inputPath, String outputPath) {
        try {
            File file = new File(inputPath);
            this.br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        buffer = new Employee[Const.BUFFER_SIZE];
        this.outputPath = outputPath;
    }

    public void start() {
        // count used to track how many tmp file we have
        int count = 1;
        // idx is the last valid element index in buffer (if the main memory's size larger than the
        // items' size or the last time when we sorting the elements may less than the size of
        // main memory)
        int idx = readDataIntoBuffer();
        // loop to sorting all items in the input file
        while (idx != Const.READ_NOTHING) {
            QuickSort.sort(buffer, 0, idx);
            writeDataIntoDisk(buffer, count);
            count++;
            idx = readDataIntoBuffer();
        }
        // TODO: merge and then delete the tmp file
        merge(count - 1);
    }

    private void merge(int count) {

    }

    private void writeDataIntoDisk(Employee[] buffer, int count) {
        String path = outputPath + count + ".txt";
        try {
            FileWriter fw = new FileWriter(path);
            bw = new BufferedWriter(fw);
            for (Employee e : buffer) {
                if (e == null) break;
                else {
                    bw.write(e.getEmpId() + "\n");
                }
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int readDataIntoBuffer() {
        int curIdx = Const.READ_NOTHING;
        for (int i = 0; i < buffer.length; i++) {
            try {
                String str = br.readLine();
                // TODO: 2017-01-27
                // add something to parse different part of the string reading from the data file
                if (str == null) break;
                buffer[i] = new Employee(Integer.parseInt(str));
                curIdx++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return curIdx;
    }

}
