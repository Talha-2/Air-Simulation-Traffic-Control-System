
package ASTC;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

public class Task_Engine extends Thread {

    private String global_time;
    private static PriorityBlockingQueue<Task> tasks_queue;
    private static ArrayList<Integer> id_queue=new ArrayList();
    private int id;
    private Task object;
    private Time obj;
    private static List<String> completedTasks = new ArrayList<>();

    public Task_Engine(Task object, Time time) {
        this.object = object;
        this.obj = time;
        tasks_queue = new PriorityBlockingQueue<>(11, Comparator.comparingInt(Task::getPriority).reversed());
    }

    public void run() {
        collecting_tasks();
        dispatch_task();
    }

    public void collecting_tasks() {
        synchronized (tasks_queue) {
      
            tasks_queue.add(object);
            id_queue.add(generate_id());
           
        }
    }

    public void deleting_task(int id) {
    	tasks_queue.remove(id);
    }

    public void dispatch_task() {
        synchronized (tasks_queue) {
            System.out.println("Going to dispatch:");
            
            for (Task task : new ArrayList<>(tasks_queue)) {
                String primary_task_label = task.getPrimaryTaskLabel().toLowerCase();
                
                if (task.getTimeMark().equals("0") && (primary_task_label.equals("airplane"))) {
                    Ground_network checker = new Ground_network();
                    Airplane obj = new Airplane(task, checker);
                    obj.start();
                    completedTasks.add(primary_task_label);
               
                    System.out.println("Dispatching " + primary_task_label);
                } else if (task.getTimeMark().equals("0") && primary_task_label.equals("pathfinder")) {
                	Ground_network checker = new Ground_network();
                	 Airplane obj = new Airplane(task, checker);
                    System.out.println("Dispatching task labeled '" + primary_task_label + "'");
                    completedTasks.add(primary_task_label);
                   
                }
            }
        }
    }


    public int generate_id() {
        return ++id;
    }

    public void display_list() {
        synchronized (tasks_queue) {
            System.out.println("Task queue");
            System.out.println();
            for (Task task : tasks_queue) {
                System.out.println(task + " ");
                System.out.println("Primary Task Label: " + task.getPrimaryTaskLabel());
                System.out.println("Secondary Task Label: " + task.getSecondaryTaskLabel() + " ");
                System.out.println("Priority: " + task.getPriority());
                System.out.println("Time Mark: " + task.getTimeMark());
             
                System.out.println("aiplane id :" + task.get_airplane_id() );
            }
        }
    }
}
