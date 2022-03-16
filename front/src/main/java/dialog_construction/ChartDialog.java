package dialog_construction;

import model.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import validation.Validator;

import java.awt.*;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class ChartDialog {
    private Class<? extends Model> currentClass;
    private String chartName;

    public ChartDialog(Class<? extends Model> currentClass){
        this.currentClass = currentClass;
        this.chartName = getChartName();
    }

    private String getChartName(){
        switch (currentClass.getName()) {
            default:
            case "model.TesttypeModel":     return "Процент базовых видов тестирования";
            case "model.TestcaseModel":      return "Статистика тесткейсов по автоматизации";
            case "model.RequestModel":     return "Процент заявок по годам";
            case "model.TesterModel":     return "Статистика специальностей Тестировщиков";
            case "model.UserModel":         return "Статистика ролей пользователей";
        }
    }

    public PieDataset createDataset(ArrayList<Model> models) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (int i = 0; i < models.size(); i++) {
            switch (currentClass.getName()) {
                default:
                case "model.TesttypeModel":     addOneTesttype(dataset, models.get(i)); break;
                case "model.TestcaseModel":      addOneTestcase(dataset, models.get(i)); break;
                case "model.RequestModel":     addOneRequest(dataset, models.get(i)); break;
                case "model.TesterModel":     addOneTester(dataset, models.get(i)); break;
                case "model.UserModel":         addOneUser(dataset, models.get(i), models.size()); break;
            }
        }
        List list = dataset.getKeys();
        for (int i = 0; i < list.size(); i++) {
            double number = (double) dataset.getValue((String)list.get(i));
            double newValue = ((double)number) / models.size() * 100;
            dataset.setValue(list.get(i) + " - " + String.format("%.2f", newValue) + "%", number);
            dataset.remove((String)list.get(i));
        }
        return dataset;
    }

    private void addOneTesttype(DefaultPieDataset dataset, Model model){
        TesttypeModel testtype = (TesttypeModel) model;
        try {
            if(Validator.getIdFromKey(testtype.getParent_testtype_id()) == 0)
                dataset.setValue("Секция", dataset.getValue("Секция").intValue() + 1);
            else
                dataset.setValue("Подкотегория", dataset.getValue("Подкотегория").intValue() + 1);
        } catch (Exception e){
            if(Validator.getIdFromKey(testtype.getParent_testtype_id()) == 0)
                dataset.setValue("Секция", 1);
            else
                dataset.setValue("Подкотегория", 1);
        }
    }

    private void addOneTestcase(DefaultPieDataset dataset, Model model){
        TestcaseModel testcase = (TestcaseModel) model;
        String group = testcase.getAurimatization();
        try {
            dataset.getValue(group);
            dataset.setValue(group, dataset.getValue(group).intValue() + 1);
        } catch (Exception e){
            dataset.setValue(group, 1);
        }
    }

    private void addOneRequest(DefaultPieDataset dataset, Model model){
        char[] yearChar = new char[4];
        ((RequestModel) model).getDate().getChars(0, 4, yearChar, 0);
        String year = String.valueOf(yearChar);
        try {
            dataset.getValue(year);
            dataset.setValue(year, dataset.getValue(year).intValue() + 1);
        } catch (Exception e){
            dataset.setValue(year, 1);
        }
    }

    private void addOneTester(DefaultPieDataset dataset, Model model){
        TesterModel tester = (TesterModel) model;
        try {
            dataset.getValue(tester.getSpeciality());
            dataset.setValue(tester.getSpeciality(), dataset.getValue(tester.getSpeciality()).intValue() + 1);
        } catch (Exception e){
            dataset.setValue(tester.getSpeciality(), 1);
        }
    }

    private void addOneUser(DefaultPieDataset dataset, Model model, int quantity){
        UserModel user = (UserModel) model;
        String role = user.getRole().equals("Пользователь") ? "Пользователь" : (user.getRole().equals("Тестер") ? "Тестер": "Руководитель");
        try {
            dataset.getValue(role);
            int value = dataset.getValue(role).intValue() + 1;
            dataset.setValue(role, value);
        } catch (Exception e){
            dataset.setValue(role, 1);
        }
    }

    public JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(chartName, dataset,true,true, false);

        chart.setBackgroundPaint(new GradientPaint(
                new Point(0, 0),
                new Color(20, 20, 20),
                new Point(400, 200),
                Color.DARK_GRAY)
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setInteriorGap(0.10);
        plot.setOutlineVisible(false);

        plot.setBaseSectionOutlinePaint(Color.BLACK);
        plot.setSectionOutlinesVisible(true);
        plot.setBaseSectionOutlineStroke(new BasicStroke(3.0f));

        plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
        plot.setLabelLinkPaint(Color.BLACK);
        plot.setLabelLinkStroke(new BasicStroke(2.0f));
        plot.setLabelOutlineStroke(null);
        plot.setLabelPaint(Color.BLACK);
        plot.setLabelBackgroundPaint(null);

        return chart;
    }

}