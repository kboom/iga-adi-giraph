package edu.agh.iga.adi.giraph.core.logging;

import edu.agh.iga.adi.giraph.core.IgaElement;
import org.ojalgo.netio.ASCII;
import org.ojalgo.structure.Access2D;

import java.text.DecimalFormat;

import static java.lang.Math.max;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ElementFormatter {

  private static final String LINE_SEPARATOR = System.lineSeparator();

  private static final DecimalFormat SIMPLIED_FORMAT = new DecimalFormat("#.###");
  private static final String[] EMPTY_ARRAY = new String[0];
  private static final String MISSING_VALUE_ROW = "x";

  public static String formatElement(IgaElement element) {
    return glueElements(
        ofNullable(element.ma).map(ElementFormatter::formatMatrix).orElse(""),
        ofNullable(element.mx).map(ElementFormatter::formatMatrix).orElse(""),
        ofNullable(element.mb).map(ElementFormatter::formatMatrix).orElse("")
    );
  }

  private static String glueElements(String ma, String mb, String mx) {
    String[] mar = isNotEmpty(ma) ? ma.split(LINE_SEPARATOR) : EMPTY_ARRAY;
    String[] mbr = isNotEmpty(mb) ? mb.split(LINE_SEPARATOR) : EMPTY_ARRAY;
    String[] mxr = isNotEmpty(mx) ? mx.split(LINE_SEPARATOR) : EMPTY_ARRAY;

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < max(mbr.length, max(mxr.length, mar.length)); i++) {
      sb.append(mar.length > i ? mar[i] : MISSING_VALUE_ROW);
      sb.append(" | ");
      sb.append(mxr.length > i ? mxr[i] : MISSING_VALUE_ROW);
      sb.append(" | ");
      sb.append(mbr.length > i ? mbr[i] : MISSING_VALUE_ROW);
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
        tmpWidth = max(tmpWidth, tmpElementString.length());
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
