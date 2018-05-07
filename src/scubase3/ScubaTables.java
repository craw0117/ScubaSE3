package scubase3;

import javax.swing.JTable;

/**
 * Table construction is separated from the ScubaModel class to simplify the
 * process of updating table settings and methods, all methods in this class are
 * called from the ScubaModel class.
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaTables {

    /**
     * Container class for storing table data, used to reduce duplicate code.
     */
    private static class TableData {

        int[] oxygen;
        int[] depth;
        String[] columns;
        String[][] data;

        /**
         * Default constructor, takes row count and column count and creates the
         * arrays accordingly
         *
         * @param rowCount
         * @param columnCount
         */
        public TableData(int rowCount, int columnCount) {
            oxygen = new int[rowCount];
            depth = new int[columnCount];
            columns = new String[columnCount];
            data = new String[rowCount][columnCount];
        }
    }

    /**
     * Creates a "new" TableData object which contains the column and row
     * headers which are the same regardless of which table type is selected.
     *
     * @param columnCount
     * @param rowCount
     * @param oxygenMinimum
     * @param depthMinimum
     * @param depthMultiplier
     * @return
     */
    private static TableData createTableData(int columnCount, int rowCount, int oxygenMinimum, int depthMinimum, int depthMultiplier) {
        TableData tableData = new TableData(rowCount, columnCount);
        tableData.columns[0] = "Oxygen/Depth";
        for (int i = 0; i < tableData.oxygen.length; i++) {
            tableData.oxygen[i] = oxygenMinimum + i;
            tableData.data[i][0] = tableData.oxygen[i] + Const.UNIT_PERCENT;
        }
        for (int j = 1; j < tableData.depth.length; j++) {
            tableData.depth[j] = depthMinimum + (j * depthMultiplier);
            tableData.columns[j] = tableData.depth[j] + Const.UNIT_METERS;
        }
        return tableData;
    }

    /**
     * Creates the final resultant JTable of each of the create table methods,
     * this method applies styling to the JTable and provides a method for
     * updating styles in one place. There is no need to insert this into the
     * create table methods as it is duplicate code.
     *
     * @param tableData
     * @return
     */
    private static JTable createJTableFromTableData(TableData tableData) {
        JTable table = new JTable(tableData.data, tableData.columns);
        table.setEnabled(false);
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        return table;
    }

    /**
     * Generates the default Partial Pressure information table, this method
     * should only be called once to avoid unnecessary work.
     *
     * @return
     * @see #createPPTable(int columnCount, int rowCount, int oxygenMinimum, int
     * depthMinimum, int depthMultiplier)
     */
    public static JTable createPPTable() {
        return createPPTable(24, 33, 18, 0, 3);
    }

    /**
     * Generates the default Equivalent Air Depth information table, this method
     * should only be called once to avoid unnecessary work.
     *
     * @return
     * @see #createEADTable(int columnCount, int rowCount, int oxygenMinimum,
     * int depthMinimum, int depthMultiplier)
     */
    public static JTable createEADTable() {
        return createEADTable(24, 33, 18, 0, 3);
    }

    /**
     * Creates a Partial Pressure information table using custom parameters, if
     * provided parameters are invalid, null will be returned instead.
     *
     * @param columnCount
     * @param rowCount
     * @param oxygenMinimum
     * @param depthMinimum
     * @param depthMultiplier
     * @return
     */
    public static JTable createPPTable(int columnCount, int rowCount, int oxygenMinimum, int depthMinimum, int depthMultiplier) {
        if (columnCount > 1 && rowCount > 1 && oxygenMinimum >= 0 && depthMinimum >= 0 && depthMultiplier > 0) {
            TableData tableData = createTableData(columnCount, rowCount, oxygenMinimum, depthMinimum, depthMultiplier);
            // Calculate data and insert it into the table
            for (int i = 0; i < tableData.data.length; i++) {
                for (int j = 1; j < tableData.data[i].length; j++) {
                    double result = ScubaCalculations.calculateRawPP(tableData.oxygen[i] / 100.0, tableData.depth[j]);
                    if (result > 1.6) {
                        tableData.data[i][j] = Const.UNSAFE_OUTPUT_VALUE;
                    } else {
                        tableData.data[i][j] = Const.DF_PP.format(result);
                    }
                }
            }
            return createJTableFromTableData(tableData);
        }
        return null;
    }

    /**
     * Creates an Equivalent Air Depth information table using custom
     * parameters, if provided parameters are invalid, null will be returned
     * instead.
     *
     * @param columnCount
     * @param rowCount
     * @param oxygenMinimum
     * @param depthMinimum
     * @param depthMultiplier
     * @return
     */
    public static JTable createEADTable(int columnCount, int rowCount, int oxygenMinimum, int depthMinimum, int depthMultiplier) {
        if (columnCount > 1 && rowCount > 1 && oxygenMinimum >= 0 && depthMinimum >= 0 && depthMultiplier > 0) {
            TableData tableData = createTableData(columnCount, rowCount, oxygenMinimum, depthMinimum, depthMultiplier);
            // Calculate data and insert it into the table
            for (int i = 0; i < tableData.data.length; i++) {
                for (int j = 1; j < tableData.data[i].length; j++) {
                    double partialPressure = ScubaCalculations.calculateRawPP(tableData.oxygen[i] / 100.0, tableData.depth[j]);
                    double result = ScubaCalculations.calculateRawEAD(tableData.oxygen[i] / 100.0, tableData.depth[j]);
                    if (result < 0) {
                        tableData.data[i][j] = Const.DF_DEPTH.format(0);
                    } else if (partialPressure <= 1.6) {
                        tableData.data[i][j] = Const.DF_DEPTH.format(result);
                    } else {
                        tableData.data[i][j] = Const.UNSAFE_OUTPUT_VALUE;
                    }
                }
            }
            return createJTableFromTableData(tableData);
        }
        return null;
    }
}
