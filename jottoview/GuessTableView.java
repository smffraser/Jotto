package jottoview;

import jottomodel.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;

public class GuessTableView extends JPanel {
    private JottoModel model;
    public JTable table;

    public GuessTableView(JottoModel model) {
        super(new GridLayout(1,0));
        this.model = model;

        table = new JTable(model.getTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        this.add(table);

        this.model.addView(new IView() {
            public void updateView() {
                // Updating the view includes enabling/disabling components!
                //baseUp.setEnabled(model.getBase() < TriangleModel.MAX_SIDE);
                //baseDn.setEnabled(model.getBase() > 1);
                //heightUp.setEnabled(model.getHeight() < TriangleModel.MAX_SIDE);
                //heightDn.setEnabled(model.getHeight() > 1);
            }

        });

    }

}