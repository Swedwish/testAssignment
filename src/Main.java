import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;




public class Main {
    public static void main(String[] args) {
        Map<String, String> yesterdayData = getYesterdayData();
        Map<String, String> todayData = getTodayData();
        System.out.print(generateReport(yesterdayData, todayData, "Инна Ивановна"));
    }

    public static Map<String, String> getYesterdayData(){       //Sample data
        HashMap<String, String> yesterdayData = new HashMap<>();
        yesterdayData.put("http://example.com/page1", "<html>Page 1</html>");
        yesterdayData.put("http://example.com/page2", "<html>Page 2</html>");
        yesterdayData.put("http://example.com/page3", "<html>Page 3</html>");
        return  yesterdayData;
    }

    public static Map<String, String> getTodayData(){       //Sample data
        HashMap<String, String> todayData = new HashMap<>();
        todayData.put("http://example.com/page1", "<html>Page 1</html>");
        todayData.put("http://example.com/page2", "<html>Updated Page 2</html>");
        todayData.put("http://example.com/page4", "<html>Page 4</html>");
        return  todayData;
    }
    public static String generateReport(final HashMap<String, String> yesterdayData, final HashMap<String, String> todayData){
        return generateReport(yesterdayData, todayData, "");
    }
    public static String generateReport(final Map<String, String> yesterdayData, final Map<String, String> todayData, String name){
        Set<String> deletedPages = new HashSet<>(yesterdayData.keySet());
        Set<String> newPages = new HashSet<>(todayData.keySet());
        Set<String> editedPages = new HashSet<>();

        deletedPages.removeAll(todayData.keySet());
        newPages.removeAll(yesterdayData.keySet());

        for (String url: yesterdayData.keySet()){
            if (todayData.containsKey(url) && !todayData.get(url).equals(yesterdayData.get(url))){
                editedPages.add(url);
            }
        }

        StringBuilder report = new StringBuilder();
        report.append(name.isBlank() ?"Здравствуйте, дорогой секретарь.\n": "Здравствуйте, дорогая " + name + ".\n");

        if (deletedPages.isEmpty() && newPages.isEmpty() && editedPages.isEmpty()) {
            report.append("За последние сутки во вверенных Вам сайтах изменений не было.\n");
            return report.toString();
        } else {
            report.append("За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n");

            if (deletedPages.isEmpty()){
                report.append("\nУдаленных страниц нет.\n");
            } else {
                report.append("\nБыли удалены следующие страницы:\n");
                for (String url: deletedPages){
                    report.append("  - ").append(url).append("\n");
                }
            }
            if (newPages.isEmpty()){
                report.append("\nНовых страниц нет.\n");
            } else {
                report.append("\nПоявились следующие новые страницы:\n");
                for (String url: newPages){
                    report.append("  - ").append(url).append("\n");
                }
            }

            if (editedPages.isEmpty()){
                report.append("\nИзмененных страниц нет.\n");
            } else {
                report.append("\nБыли изменены следующие страницы:\n");
                for (String url: editedPages){
                    report.append("  - ").append(url).append("\n");
                }
            }
        }
        return report.toString();
    }
}