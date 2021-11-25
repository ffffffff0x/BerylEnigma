package ffffffff0x.beryenigma.App.Controller.Encryption.Coding.Brainfuck.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: RyuZUSUNC
 * @create: 2021-11-24 15:23
 **/

public class shortOokEngine extends  TrollScriptEngine {

    /**
     * The default length of a token.
     */
    protected int defaultTokenLength = 2;
    /**
     * The {@link Token} enum contains tokens of Ook!.
     *
     * @author Fabian M.
     */
    protected static enum Token {
        NEXT(".?"),
        PREVIOUS("?."),
        PLUS(".."),
        MINUS("!!"),
        OUTPUT("!."),
        INPUT(".!"),
        BRACKET_LEFT("!?"),
        BRACKET_RIGHT("?!");

        String value;

        /**
         * Constructs a new token.
         *
         * @param value The value of the token.
         */
        Token(String value) {
            this.value = value;
        }

        /**
         * Get the value of the token.
         *
         * @return the value.
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Constructs a new {@link TrollScriptEngine} instance.
     *
     * @param cells The amount of memory cells.
     */
    public shortOokEngine(int cells) {
        super(cells);
    }

    /**
     * Constructs a new {@link TrollScriptEngine} instance.
     *
     * @param cells The amount of memory cells.
     * @param out
     */
    public shortOokEngine(int cells, OutputStream out) {
        super(cells, out);
    }

    /**
     * Constructs a new {@link TrollScriptEngine} instance.
     *
     * @param cells The amount of memory cells.
     * @param out   The printstream of this program.
     * @param in
     */
    public shortOokEngine(int cells, OutputStream out, InputStream in) {
        super(cells, out, in);
    }

    /**
     * Interprets the given string.
     *
     * @param str
     *            The string to interpret.
     * @throws Exception
     */
    @Override
    public byte[] interpret(String str) throws Exception {
        // List with tokens.defaultTokenLenght
        List<Token> tokens = new ArrayList<Token>();
        // It fine that all Ook! tokens are 9 characters long :)
        // So we aren't going to loop through all characters..
        for (; charPointer < str.length(); ) {
            String token = "";
            if (charPointer + defaultTokenLength <= str.length())
                // The string we found.
                token = str.substring(charPointer, charPointer + defaultTokenLength);
            else
                token = str.substring(charPointer, charPointer
                        + (str.length() - charPointer));

            boolean b = false;

            for (Token tokenCheck : Token.values()) {
                if (tokenCheck.getValue().equals(token)) {
                    tokens.add(tokenCheck);
                    charPointer += defaultTokenLength;
                    b = true;
                    break;
                }
            }

            // If the token was invalid, b is false.
            if (!b)
                if (charPointer + defaultTokenLength > str.length())
                    charPointer += (str.length() - charPointer);
                else
                    charPointer++;


        }

        // Loop through all tokens.
        for (int tokenPointer = 0; tokenPointer < tokens.size(); ) {
            Token token = tokens.get(tokenPointer);
//			System.out.println(token);
            switch(token) {
                case NEXT:
                    // increment the data pointer (to point to the next cell
                    // to the
                    // right).
                    dataPointer = (dataPointer == data.length - 1 ? 0 : dataPointer + 1);
                    break;
                case PREVIOUS:
                    // decrement the data pointer (to point to the next cell
                    // to the
                    // left).
                    dataPointer = (dataPointer == 0 ? data.length - 1 : dataPointer - 1);
                    break;
                case PLUS:
                    // increment (increase by one) the byte at the data
                    // pointer.
                    data[dataPointer]++;
                    break;
                case MINUS:
                    // decrement (decrease by one) the byte at the data
                    // pointer.
                    data[dataPointer]--;
                    break;
                case OUTPUT:
                    // Output the byte at the current index in a character.
                    //outWriter.write((char) data[dataPointer]);
                    // Flush the outputstream.
                    //outWriter.flush();
                    byteArrayOutputStream.write(data[dataPointer]);
//				outStringBuilder.append((char) data[dataPointer]);
                    break;
                case INPUT:
                    // accept one byte of input, storing its value in the
                    // byte at the data pointer.
                    data[dataPointer] = (byte) consoleReader.read();
                    break;
                case BRACKET_LEFT:
                    if (data[dataPointer] == 0) {
                        int level = 1;
                        while (level > 0) {
                            tokenPointer++;

                            if (tokens.get(tokenPointer).equals(Token.BRACKET_LEFT))
                                level++;
                            else if (tokens.get(tokenPointer).equals(Token.BRACKET_RIGHT))
                                level--;
                        }
                    }
                    break;
                case BRACKET_RIGHT:
                    if (data[dataPointer] != 0) {
                        int level = 1;
                        while (level > 0) {
                            tokenPointer--;

                            if (tokens.get(tokenPointer).equals(Token.BRACKET_LEFT))
                                level--;
                            else if (tokens.get(tokenPointer).equals(Token.BRACKET_RIGHT))
                                level++;
                        }
                    }
                    break;
            }

            tokenPointer++;
        }
        // Clear all data.
        initate(data.length);
//		return outStringBuilder.toString();
        return byteArrayOutputStream.toByteArray();
    }
}
