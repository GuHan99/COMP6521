import org.junit.Test;
import project1.Const;
import project1.Employee;
import project1.ExternalSorting;
import project1.QuickSort;

/**
 * Created by ERIC_LAI on 2017-01-19.
 */
public class Test1 {

    @Test
    public void test1() {
        String inputPath = Const.PROJECT_ROOT + "NonRepeatedRadomNumGenerator/t2.txt";
        String outputPath = Const.PROJECT_ROOT + "src/main/resources/";
        ExternalSorting sorting = new ExternalSorting(inputPath, outputPath);
        sorting.start();
    }

    @Test
    public void testQuickSort() {
        Employee[] es = new Employee[5];
        es[0] = new Employee(1111110);
        es[1] = new Employee(1111113);
        es[2] = new Employee(1111111);
        es[3] = new Employee(1111119);
        es[4] = new Employee(1111114);
        QuickSort.sort(es, 0, 4);
        for (Employee e : es) {
            System.out.println(e.getEmpId());
        }
    }
}
