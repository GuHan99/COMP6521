package project1;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * COMP6521 Project 1
 * Date:   2017-02-12
 * Author: Yuanwen Qin 40011473 q_yuanwe@encs.concordia.ca
 *         Chao Wang 25162130 chao_wa@encs.concordia.ca
 *         Haotao Lai 40018061 h_lai@encs.concordia.ca
 *         Anita Rai 40038782 an_rai@encs.concordia.ca
 */
class FileBuffer {

    public BufferedReader fileBufferReader;
    private String cache;

    public FileBuffer(BufferedReader bufferedReader) throws IOException {
        this.fileBufferReader = bufferedReader;
        read();
    }

    /**
     * close the buffered reader
     */
    public void close() throws IOException {
        this.fileBufferReader.close();
    }

    /**
     * whether reaches the end of file or not
     */
    public boolean empty() {
        return this.cache == null;
    }

    /**
     * get the most top line of the file
     */
    public String peek() {
        return this.cache;
    }

    /**
     * move the current line pointer to the next line
     */
    public String pop() throws IOException {
        String result = peek();
        read();
        return result;
    }

    /**
     * read a line data from the file
     */
    private void read() throws IOException {
        this.cache = this.fileBufferReader.readLine();
    }



}
