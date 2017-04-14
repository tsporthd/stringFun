import java.util.ArrayList;
import java.util.List;

/*
 * First simple parser before I looked at the sorted option and realized I would need parent/child
 * to sort correctly
 *
 * @author lpresswood, @date 4/14/17 7:01 AM
 */
class StringFun {

    public static final char OPEN_PAREN = '(', CLOSE_PAREN = ')', COMMA = ',', SPACE = ' ';
    public static final String EMPTY_STRING = ""; // Could use String Utils

    @SuppressWarnings("empty")
    List<ParsedData> parseString(String inputString) {

        List<ParsedData> parsedString = new ArrayList<>();
        StringBuilder prefix = new StringBuilder();
        String curWord;
        for (int i = 1; i < inputString.length(); ++i) {

            if (inputString.charAt(i) == OPEN_PAREN) {
                prefix.append("-");
            } else if (inputString.charAt(i) == COMMA || inputString.charAt(i) == SPACE) {
                //ignore
            } else if (inputString.charAt(i) == CLOSE_PAREN) {
                if (prefix.length() > 0)
                    prefix = new StringBuilder(prefix.substring(1));
            } else {
                curWord = "";
                while (i < inputString.length()) {
                    if (Character.isAlphabetic(inputString.charAt(i))) {
                        curWord += inputString.charAt(i);
                        ++i;
                    } else {
                        parsedString.add(new ParsedData(prefix.toString(), curWord));
                        --i;
                        break;
                    }
                }
            }
        }

        return parsedString;
    }




    static class ParsedData  {
        private String prefix;
        private String data;

        public ParsedData(String prefix, String data) {
            this.prefix = prefix;
            this.data = data;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer(prefix);
            sb.append(data);
            return sb.toString();
        }



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ParsedData that = (ParsedData) o;

            if (!prefix.equals(that.prefix)) return false;
            return data.equals(that.data);
        }

        @Override
        public int hashCode() {
            int result = prefix.hashCode();
            result = 31 * result + data.hashCode();
            return result;
        }
    }

}
