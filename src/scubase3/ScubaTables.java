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
     * @return
     */
    private static TableData createTableData(int oxygenMinimum, int oxygenMaximum, int depthMinimum, int depthMaximum) {
        int rowCount = (oxygenMaximum - oxygenMinimum) + 1;
        int columnCount = ((depthMaximum - depthMinimum) / 3) + 2;
        TableData tableData = new TableData(rowCount, columnCount);
        tableData.columns[0] = "Oxygen/Depth";
        for (int i = 0; i < tableData.oxygen.length; i++) {
            tableData.oxygen[i] = oxygenMinimum + i;
            tableData.data[i][0] = tableData.oxygen[i] + Const.UNIT_PERCENT;
        }
        for (int j = 1; j < tableData.depth.length; j++) {
            tableData.depth[j] = depthMinimum + ((j - 1) * 3);
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
        table.getTableHeader().setReorderingAllowed(false);
        table.setEnabled(false);
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setMinWidth(40);
            table.getColumnModel().getColumn(i).setPreferredWidth(40);
            table.getColumnModel().getColumn(i).setMaxWidth(50);
        }
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
        return createPPTable(18, 50, 3, 69);
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
        return createEADTable(18, 50, 3, 69);
    }

    /**
     * Creates a Partial Pressure information table using custom parameters, if
     * provided parameters are invalid, null will be returned instead.
     *
     * @param oxygenMinimum
     * @param oxygenMaximum
     * @param depthMinimum
     * @param depthMaximum
     * @return
     */
    public static JTable createPPTable(int oxygenMinimum, int oxygenMaximum, int depthMinimum, int depthMaximum) {
        TableData tableData = createTableData(oxygenMinimum, oxygenMaximum, depthMinimum, depthMaximum);
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

    /**
     * Creates an Equivalent Air Depth information table using custom
     * parameters, if provided parameters are invalid, null will be returned
     * instead.
     *
     * @param oxygenMinimum
     * @param oxygenMaximum
     * @param depthMinimum
     * @param depthMaximum
     * @return
     */
    public static JTable createEADTable(int oxygenMinimum, int oxygenMaximum, int depthMinimum, int depthMaximum) {
        TableData tableData = createTableData(oxygenMinimum, oxygenMaximum, depthMinimum, depthMaximum);
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
}
