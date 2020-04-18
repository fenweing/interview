import java.util.List;

/**
 * dag task(uncompleted)
 */
public class DagTask {
    private String name;
    private List<DagTask> dependTasks;
    //relationship

    public void execute() {
        System.out.println(name);
    }
}
