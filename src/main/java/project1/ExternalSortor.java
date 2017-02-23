package project1;

import java.io.*;
import java.util.*;

/**
 * COMP6521 Project 1
 * Date:   2017-02-12
 * Author: Yuanwen Qin 40011473 q_yuanwe@encs.concordia.ca
 *         Chao Wang 25162130 chao_wa@encs.concordia.ca
 *         Haotao Lai 40018061 h_lai@encs.concordia.ca
 *         Anita Rai 40038782 an_rai@encs.concordia.ca
 */
public class ExternalSortor {

    /**
     * an comparator used to compare the EmpId
     */
    private Comparator<String> idComparator = new Comparator<String>() {
        @Override
        public int compare(String r1, String r2) {
            return r1.substring(0, Configuration.LENGTH_OF_EMP_ID).compareTo(r2.substring(0,
                    Configuration.LENGTH_OF_EMP_ID));
        }
    };

    public ExternalSortor() {
    }

    /**
     * execute all steps in order to sort a large file with limited memory
     */
    public String process(String inputFileName) throws IOException {
        long availableMemory = getAvailableMemory();
        String outputFileName = "sorted_" + inputFileName + ".tmp";
        File inputFile = new File(Configuration.INPUT_FILE_PATH, inputFileName);
        File outputFile = new File(outputFileName);
        List<File> tmpFileList = splitAndSort(inputFile, availableMemory);
        mergeSortedTmpFiles(tmpFileList, outputFile);
        return outputFileName;
    }

    /**
     * get the current free memory from the runtime environment
     */
    private long getAvailableMemory() {
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        return runtime.maxMemory() - usedMemory;
    }

    /**
     * read data from disk until the data size reaches the threshold and then using quick sort to
     * sort the data and output to become a sorted sub-file
     */
    private List<File> splitAndSort(File file, long availableMemory) throws IOException {
        List<File> files = new ArrayList<>();
        long maxBlockSize = availableMemory / 2;
        List<String> tmpList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "UTF-8"))) {
            String line;
            long blockSize = 0;
            while ((line = reader.readLine()) != null) {
                if (blockSize >= maxBlockSize) {
                    files.add(sortAndSaveToTmp(file, tmpList));
                    blockSize = 0;
                    tmpList.clear();
                }

                if (!line.isEmpty()) {
                    tmpList.add(line);
                    blockSize += getStringSize(line);
                }
            }
        } finally {
            files.add(sortAndSaveToTmp(file, tmpList));
            tmpList.clear();
        }
        return files;
    }


    /**
     * when the memory reaches to the threshold we set then save them to a temporary sorted file
     */
    private File sortAndSaveToTmp(File inputFile, List<String> tmpList) throws IOException {
        Collections.sort(tmpList, idComparator);
        File tmpFile = File
                .createTempFile(inputFile.getName(), null, new File(Configuration.TMP_FILE_PATH));
        tmpFile.deleteOnExit();
        OutputStream out = new FileOutputStream(tmpFile);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"))) {
            for (String r : tmpList) {
                writer.write(r);
                writer.newLine();
            }
        }
        return tmpFile;
    }

    /**
     * merge all sorted sub-files to be a sorted file
     */
    private void mergeSortedTmpFiles(List<File> fileList, File outputFile)
            throws IOException {
        ArrayList<FileBuffer> fileBufferList = new ArrayList<>();
        for (File file : fileList) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "UTF-8"));
            FileBuffer fileBuffer = new FileBuffer(reader);
            fileBufferList.add(fileBuffer);
        }
        outputFile.delete();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFile, true), "UTF-8"));
        merge(bufferedWriter, fileBufferList);

        for (File file : fileList) {
            file.delete();
        }
    }

    /**
     * use a priority queue to extract minimum element and add a new element into the queue from the
     * where the min-element come from
     */
    private void merge(BufferedWriter fileBufferWriter, List<FileBuffer> buffers)
            throws IOException {
        PriorityQueue<FileBuffer> pq = new PriorityQueue<>(
                11, new Comparator<FileBuffer>() {
            @Override
            public int compare(FileBuffer fb1,
                               FileBuffer fb2) {
                return idComparator.compare(fb1.peek(), fb2.peek());
            }
        });

        for (FileBuffer fileBuffer : buffers) {
            if (!fileBuffer.empty()) {
                pq.add(fileBuffer);
            }
        }
        try {
            while (pq.size() > 0) {
                FileBuffer fileBuffer = pq.poll();
                String line = fileBuffer.pop();
                fileBufferWriter.write(line);
                fileBufferWriter.newLine();
                if (fileBuffer.empty()) {
                    fileBuffer.fileBufferReader.close();
                } else {
                    pq.add(fileBuffer);
                }
            }
        } finally {
            fileBufferWriter.close();
            for (FileBuffer fileBuffer : pq) {
                fileBuffer.close();
            }
        }
    }

    /**
     * get the real memory of the specific string need in the JVM
     */
    private long getStringSize(String str) {
        return (str.length() * 2) + Configuration.OBJECT_OVERHEAD;
    }
}
