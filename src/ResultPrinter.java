import java.util.LinkedList;
import java.util.ListIterator;

public class ResultPrinter {


    public void printResult(String algorithmName, LinkedList<Integer> result) {
        StringBuilder stringBuilder = new StringBuilder();
        ListIterator iterator = result.listIterator();
        stringBuilder.append("[" + algorithmName + "]: ");

        int i = 0;
        while (!result.isEmpty()) {
            stringBuilder.append(result.pollLast());
            if (!result.isEmpty()) stringBuilder.append(("->"));
            i++;
        }


        stringBuilder.append("\t" + i + " steps");
        System.out.println(stringBuilder.toString());
    }

}
