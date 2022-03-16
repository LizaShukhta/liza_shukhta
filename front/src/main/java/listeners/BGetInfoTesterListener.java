package listeners;

import connection.DataPreparer;
import dialog_construction.ChartDialog;
import dialog_construction.OneTableDialog;
import dialogs.CDialogNotFrame;
import model.Model;
import model.TesterModel;
import model.TesterTestcaseModel;
import model.TestcaseModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BGetInfoTesterListener implements ActionListener {
    private OneTableDialog parental;
    private CDialogNotFrame infoDialog;

    public BGetInfoTesterListener(OneTableDialog parental){

        this.parental = parental;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        prepareData();
        infoDialog.setVisible(true);
    }

    private void prepareData(){
        infoDialog = new CDialogNotFrame("Востребованность Тестировщиков", 800, 600, true);
        JFreeChart chart = ChartFactory.createBarChart(
                "График востребованности Тестировщиков",
                null,
                "Количествово заявок\\Затраты",
                createDataset());

        chart.setBackgroundPaint(Color.BLACK);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);


        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setVisible(true);
        infoDialog.setContentPane(panel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<Model> models = findTesters();
        for (int i = 0; i < models.size(); i++) {
            TesterModel tester = (TesterModel) models.get(i);
            ArrayList<Model> testcases = findData(tester.getId());
            dataset.addValue( testcases.size(),"Количество", tester.getName());
            dataset.addValue( getSalary(testcases), "Затраты", tester.getName());
        }
        return dataset;
    }

    private ArrayList<Model> findTesters(){
        return DataPreparer.getTesters("", "");
    }

    private ArrayList<Model> findData(int id){
        return DataPreparer.getTestersTestcases("", " tester_id = " + id + " ");
    }

    private double getSalary( ArrayList<Model> models){
        double sum = 0;
        for (int i = 0; i < models.size(); i++) {
            try {
                String id = ((TesterTestcaseModel) models.get(i)).getTestcase_id().split("\\)")[0];
                ArrayList<Model> tmodels = DataPreparer.getTestcases("", "");
                System.out.println(tmodels.size());
                for (int j = 0; j < tmodels.size(); j++) {
                    if(id.equals(((TestcaseModel) tmodels.get(j)).getId() + ""))
                        sum += ((TestcaseModel) tmodels.get(j)).getPrice();
                }
            } catch (Exception ex) {}
        }
        return sum;
    }

}
