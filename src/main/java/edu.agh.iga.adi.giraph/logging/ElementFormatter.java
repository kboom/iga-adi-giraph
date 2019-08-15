package edu.agh.iga.adi.giraph.logging;

import edu.agh.iga.adi.giraph.core.IgaElement;
import org.ojalgo.netio.ASCII;
import org.ojalgo.structure.Access2D;

public class ElementFormatter {

  private static final String LINE_SEPARATOR = System.lineSeparator();

  public static String formatElement(IgaElement element) {
    return LINE_SEPARATOR +
        "--- A ---" + LINE_SEPARATOR +
        formatMatrix(element.ma) +
        "--- B ---" + LINE_SEPARATOR +
        formatMatrix(element.mb) +
        "--- X ---" + LINE_SEPARATOR +
        formatMatrix(element.mx);
  }

  private static String formatMatrix(Access2D<?> matrix) {
    final int tmpRowDim = (int) matrix.countRows();
    final int tmpColDim = (int) matrix.countColumns();

    final String[][] tmpElements = new String[tmpRowDim][tmpColDim];
    final StringBuilder sb = new StringBuilder();
    int tmpWidth = 0;
    String tmpElementString;
    for (int j = 0; j < tmpColDim; j++) {
      for (int i = 0; i < tmpRowDim; i++) {
        tmpElementString = String.valueOf(matrix.get(i, j));
        tmpWidth = Math.max(tmpWidth, tmpElementString.length());
        tmpElements[i][j] = tmpElementString;
      }
    }
    tmpWidth++;

    int tmpPadding;
    for (int i = 0; i < tmpRowDim; i++) {
      for (int j = 0; j < tmpColDim; j++) {
        tmpElementString = tmpElements[i][j];
        tmpPadding = tmpWidth - tmpElementString.length();
        for (int p = 0; p < tmpPadding; p++) {
          sb.append(ASCII.SP);
        }
        sb.append(tmpElementString);
      }
      sb.append(LINE_SEPARATOR);
    }
    return sb.toString();
  }

}
