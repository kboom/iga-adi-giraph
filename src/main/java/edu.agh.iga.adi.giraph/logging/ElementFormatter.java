package edu.agh.iga.adi.giraph.logging;

import edu.agh.iga.adi.giraph.core.IgaElement;
import org.ojalgo.netio.ASCII;
import org.ojalgo.structure.Access2D;

import java.text.DecimalFormat;

public class ElementFormatter {

  private static final String LINE_SEPARATOR = System.lineSeparator();

  private static final DecimalFormat SIMPLIED_FORMAT = new DecimalFormat("#.###");

  public static String formatElement(IgaElement element) {
    return glueElements(
        formatMatrix(element.ma),
        formatMatrix(element.mx),
        formatMatrix(element.mb)
    );
  }

  private static String glueElements(String ma, String mb, String mx) {
    String[] mar = ma.split(LINE_SEPARATOR);
    String[] mbr = mb.split(LINE_SEPARATOR);
    String[] mxr = mx.split(LINE_SEPARATOR);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < mar.length; i++) {
      sb.append(mar[i]);
      sb.append(" | ");
      sb.append(mxr[i]);
      sb.append(" | ");
      sb.append(mbr[i]);
      sb.append(LINE_SEPARATOR);
    }
    return sb.toString();
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
        tmpElementString = SIMPLIED_FORMAT.format(matrix.get(i, j));
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
