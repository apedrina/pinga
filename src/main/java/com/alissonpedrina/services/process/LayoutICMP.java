package com.alissonpedrina.services.process;

public class LayoutICMP {

    private final String TWO_OR_MORE_SPACE = "\\s{2}+";
    private final String TWO_SPACE = "\\s{2}";
    private final String EMPTY = "";
    private final String SPACE_AT_BEGIN = "^\\s";
    private final String SPACE_AT_END = "$\\s";

    public void process() {
        /**
         while (true) {
         String txt = scanner.nextLine();
         int header = request.layout != null ? Integer.valueOf(request.layout.header) : 0;
         if (lineNumber == header) {
         String[] lineSplit = txt.split(TWO_OR_MORE_SPACE);
         for (String column : lineSplit) {
         if (!EMPTY.equals(column)) {
         columns.add(column.replaceAll(SPACE_AT_BEGIN, EMPTY).replaceAll(SPACE_AT_END, EMPTY));

         }
         }

         } else {
         String[] lineSplit = txt.split(TWO_OR_MORE_SPACE);
         Line line = new Line();
         List<String> lineValues = new ArrayList<>();

         for (int y = 0; y < lineSplit.length; y++) {
         if (!EMPTY.equals(lineSplit[y])) {
         lineValues.add(lineSplit[y].replaceAll(TWO_SPACE, EMPTY));

         }
         }
         line.setValues(lineValues);
         lines.add(line);

         }
         outputTxt.append(txt + "\n");
         lineNumber += 1;

         }
         **/
    }

}
